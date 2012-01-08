package org.moparscape.elysium;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.moparscape.elysium.net.codec.ElysiumPipelineFactory;
import org.moparscape.elysium.net.codec.Message;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Server {

    private static final Server INSTANCE;

    private volatile List<Message> messageList = new LinkedList<Message>();

    private final Lock messageListLock = new ReentrantLock();

    private final ExecutorService nettyBossService = Executors.newFixedThreadPool(1);

    private final ExecutorService nettyWorkerService = Executors.newFixedThreadPool(2);

    private final ScheduledExecutorService taskExecutorService = Executors.newScheduledThreadPool(4);

    private final ExecutorService dataExecutorService = Executors.newFixedThreadPool(2);

    private final ServerBootstrap bootstrap;

    private volatile long timestamp = System.nanoTime() / 1000000;

    private volatile long lastPulse = 0L;

    private volatile boolean running = false;

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
        while (running) {
            // Update the cached timestamp, and see if more than 600ms have passed
            // If 600ms have passed then do another update; otherwise, sleep and try again
            timestamp = (System.nanoTime() / 1000000);
            if (timestamp - lastPulse < 600) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.err.println("Error occurred while sleeping between game pulses");
                }

                continue;
            }

            // Update the time that the last pulse took place before finishing
            lastPulse = timestamp;
        }


    }

    private void parsePackets() {
        List<Message> messages = getMessages();
        for (Message m : messages) {

        }
    }

    public <T extends Message> void addMessage(T message) {
        messageListLock.lock();
        try {
            messageList.add(message);
        } finally {
            messageListLock.unlock();
        }
    }

    private List<Message> getMessages() {
        List<Message> newPackets = new LinkedList<Message>();
        List<Message> curPackets = null;

        // Get the messageList lock and then replace the current list with the new empty one
        messageListLock.lock();
        try {
            curPackets = messageList;
            messageList = newPackets;
            return curPackets;
        } finally {
            messageListLock.unlock();
        }
    }

    public long getTimestamp() {
        return timestamp;
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
