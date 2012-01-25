package org.moparscape.elysium;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.ElysiumPipelineFactory;
import org.moparscape.elysium.task.IssueUpdatePacketsTask;
import org.moparscape.elysium.task.SessionPulseTask;
import org.moparscape.elysium.util.SplittableCopyOnWriteArrayList;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Server {

    private static final Server INSTANCE;

    public static final int TASK_THREADS = 4;

    private final ExecutorService nettyBossService = Executors.newSingleThreadExecutor();

    private final ExecutorService nettyWorkerService = Executors.newFixedThreadPool(2);

    private final ScheduledExecutorService taskExecutorService = Executors.newScheduledThreadPool(TASK_THREADS);

    private final ExecutorService dataExecutorService = Executors.newSingleThreadExecutor();

    private final SplittableCopyOnWriteArrayList<Session> sessions = new SplittableCopyOnWriteArrayList<Session>(1500);

    private final GameStateUpdater updater = new GameStateUpdater();

    private final ServerBootstrap bootstrap;

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

    static {
        INSTANCE = new Server();
    }

    private Server() {
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU cores: " + cores);

        this.bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(nettyBossService, nettyWorkerService));

        this.bootstrap.setPipelineFactory(new ElysiumPipelineFactory());
        this.bootstrap.bind(new InetSocketAddress(43594));
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
                    if (highResolutionTimestamp - lastPulse < 600) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            System.err.println("Error occurred while sleeping between game pulses");
                        }

                        continue;
                    }

                    pulseSessions(); // This function blocks until all sessions have finished
                    processEvents(); // This function blocks until all events have been processed
                    updater.updateState(); // This function blocks until all updating has finished
                    issueUpdatePackets();
                    updater.updateCollections();

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
    }

    private void pulseSessions() throws Exception {
        List<Iterable<Session>> sessionPartitions = sessions.divide(TASK_THREADS);
        CountDownLatch latch = new CountDownLatch(sessionPartitions.size());

        for (Iterable<Session> s : sessionPartitions) {
            taskExecutorService.execute(new SessionPulseTask(s, latch));
        }

        // Block here until all of the tasks have finished
        latch.await();
    }

    private void processEvents() {

    }

    private void issueUpdatePackets() throws Exception {
        List<Iterable<Session>> sessionPartitions = sessions.divide(TASK_THREADS);
        CountDownLatch latch = new CountDownLatch(sessionPartitions.size());

        for (Iterable<Session> s : sessionPartitions) {
            taskExecutorService.execute(new IssueUpdatePacketsTask(s, latch));
        }

        latch.await();
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
        sessions.remove(session);
    }

    public Future<?> submitTask(Runnable r) {
        return taskExecutorService.submit(r);
    }

    public void shutdown() {
        running = false;
    }

    private void shutdown0() {
        // Shut the executors down so that the program can exit
    }

    public static Server getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Server server = Server.getInstance();
        System.out.println("Server has started. :)");

        // Enter the game loop, and stay there until shutdown
        server.gameLoop();
    }
}
