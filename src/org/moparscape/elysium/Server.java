package org.moparscape.elysium;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.ElysiumChannelInitializer;
import org.moparscape.elysium.task.CountdownTaskExecutor;
import org.moparscape.elysium.task.IssueUpdatePacketsTask;
import org.moparscape.elysium.task.SessionPulseTask;
import org.moparscape.elysium.task.timed.TimedTask;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Server {

    public static final int TASK_THREADS = 4;
    private static final Object INSTANCE_LOCK = new Object();

    private static volatile Server INSTANCE;

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    private final ScheduledExecutorService taskExecutorService = Executors.newScheduledThreadPool(TASK_THREADS);

    private final ExecutorService dataExecutorService = Executors.newSingleThreadExecutor();

    private final ArrayBlockingQueue<Session> sessions = new ArrayBlockingQueue<>(1500);

    /**
     * We used to use a PriorityBlockingQueue here. But upon inspecting the source code
     * I've learned that it acquires and release a lock EVERY TIME you peek/poll.
     * We're better off just synchronizing externally which is what's done now.
     */
    private final PriorityQueue<TimedTask> taskQueue = new PriorityQueue<>();

    private final GameStateUpdater updater = new GameStateUpdater();

    /**
     * A higher resolution (on most systems) timer that is used to accurately detect
     * when an update should be performed.
     */
    private volatile long highResolutionTimestamp = System.nanoTime() / 1000000;

    /**
     * An epoch timestamp (set using System.currentTimeMillis()) that can be used
     * for storing and comparing database timestamps.
     */
    private volatile long epochTimestamp = System.currentTimeMillis();

    private volatile long lastPulse = 0L;

    private volatile boolean running = true;

    private Server() {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU cores: " + cores);
    }

    private ChannelFuture listen() {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ElysiumChannelInitializer())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true);

            // Bind and start to accept incoming connections.
            return bootstrap.bind(new InetSocketAddress(43594));
        } catch (Exception e) {
            throw new IllegalStateException("netty down");
        }
    }

    private void gameLoop() {
        System.out.println("Game loop started");

        ParentLoop:
        while (true) {
            try {
                while (running) {
                    // Update the cached highResolutionTimestamp, and see if more than 600ms have passed
                    // If 600ms have passed then do another update; otherwise, sleep and try again
                    updateTimestamps();
                    long stampDiff = highResolutionTimestamp - lastPulse;
                    if (stampDiff < 600) {
                        try {
                            Thread.sleep(10); // Surrender this time slice
                        } catch (InterruptedException e) {
                            System.err.println("Error occurred while sleeping between game pulses");
                        }

                        continue;
                    }

                    pulseSessions(); // This function blocks until all sessions have finished
                    processTasks(); // This function blocks until all events have been processed
                    updater.updateState(); // This function blocks until all updating has finished
                    issueUpdatePackets(); // This function blocks until all packets have been sent
                    updater.updateCollections(); // This function blocks until everything is finished

                    // Update the time that the last pulse took place before finishing
                    lastPulse = highResolutionTimestamp;
                }

                // If we reach this point then shutdown has been triggered.
                // Break out of the parent loop so that the application can cleanup and shut down
                break ParentLoop;
            } catch (Exception e) {
                System.out.println("Game loop exception: " + e.getCause());
                e.printStackTrace();
            }
        }

        // TODO: Implement shutdown procedure and cleanup here
        try {
            System.out.println("Game loop stopped. Shutting down.");

            // Block on the shutdown until it's complete for each one.
            workerGroup.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        } catch (Exception e) {
            throw new IllegalStateException("Graceful shutdown failed.");
        }
    }

    private void pulseSessions() throws Exception {
        // TODO: Improve concurrency here. Currently it's sequential.
        CountDownLatch latch = new CountDownLatch(1);
        taskExecutorService.submit(new CountdownTaskExecutor(new SessionPulseTask(sessions), latch));
        latch.await();

//        List<Iterable<Session>> sessionPartitions = sessions.divide(TASK_THREADS);
//        CountDownLatch latch = new CountDownLatch(sessionPartitions.size());
//
//        for (Iterable<Session> s : sessionPartitions) {
//            taskExecutorService.execute(new CountdownTaskExecutor(new SessionPulseTask(s), latch));
//        }
//
//        // Block until session pulsing (and associated updating) has finished
//        latch.await();
    }

    private void processTasks() throws Exception {
        long time = getHighResolutionTimestamp();
        List<TimedTask> taskList = new ArrayList<>(taskQueue.size());
        TimedTask task = null;

        synchronized (taskQueue) {
            while ((task = taskQueue.peek()) != null && task.getExecutionTime() <= time) {
                // This task is due to run -- remove from the priority queue
                // and add it to the list of tasks to execute
                task = taskQueue.poll();
                taskList.add(task);
            }
        }

        // Create a countdown latch for the number of tasks to be executed,
        // and then execute each of the tasks that were obtained from the queue.
        CountDownLatch latch = new CountDownLatch(taskList.size());
        for (Runnable r : taskList) {
            taskExecutorService.execute(new CountdownTaskExecutor(r, latch));
        }

        // Wait for all priority queue tasks that have been schedule to run
        // to complete their execution
        latch.await();

        // Any task that needs to be executed again should be re-added
        // to the task priority queue
        synchronized (taskQueue) {
            for (TimedTask t : taskList) {
                if (t.shouldRepeat()) {
                    taskQueue.offer(t);
                }
            }
        }
    }

    private void issueUpdatePackets() throws Exception {
        // TODO: Improve concurrency here. Currently it's sequential.
        CountDownLatch latch = new CountDownLatch(1);
        taskExecutorService.submit(new CountdownTaskExecutor(new IssueUpdatePacketsTask(sessions), latch));
        latch.await();

//        List<Iterable<Session>> sessionPartitions = sessions.divide(TASK_THREADS);
//        CountDownLatch latch = new CountDownLatch(sessionPartitions.size());
//
//        for (Iterable<Session> s : sessionPartitions) {
//            taskExecutorService.execute(new CountdownTaskExecutor(new IssueUpdatePacketsTask(s), latch));
//        }
//
//        latch.await();
    }

    private void updateTimestamps() {
        highResolutionTimestamp = System.nanoTime() / 1000000;
        epochTimestamp = System.currentTimeMillis();
    }

    public long getHighResolutionTimestamp() {
        return highResolutionTimestamp;
    }

    public long getEpochTimestamp() {
        return epochTimestamp;
    }

    public void registerSession(Session session) {
        sessions.add(session);
    }

    public void unregisterSession(Session session) {
        System.out.println("Session removed: " + sessions.remove(session));
    }

    public Future<?> submitTask(Runnable r) {
        return taskExecutorService.submit(r);
    }

    public void submitTimedTask(TimedTask task) {
        synchronized (taskQueue) {
            taskQueue.offer(task);
        }
    }

    public void shutdown() {
        running = false;
    }

    public static Server getInstance() {
        Server result = INSTANCE;
        if (result == null) {
            synchronized (INSTANCE_LOCK) {
                result = INSTANCE;
                if (result == null) {
                    INSTANCE = result = new Server();
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
        ChannelFuture cf = server.listen();

        System.out.println("Server has started. :)");

        // Enter the game loop, and stay there until shutdown
        server.gameLoop();

        System.out.println("Shutdown complete.");
    }
}
