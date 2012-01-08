package org.moparscape.elysium.net;

import org.jboss.netty.channel.Channel;
import org.moparscape.elysium.entity.Entity;
import org.moparscape.elysium.net.codec.Message;
import org.moparscape.elysium.net.handler.MessageHandler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Session {

    private final Channel channel;

    private Entity player;

    private Queue<Message> messageQueue = new ConcurrentLinkedQueue<Message>();

    private boolean removing = false;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public void setPlayer(Entity e) {
        this.player = e;
    }

    public boolean pulse() {
        if (removing) {
            return false;
        }

        Message message;
        int processed = 0;
        while ((message = messageQueue.poll()) != null && processed++ < 200) {
            MessageHandler<Message> handler; // TODO: Finish
        }
        return false;
    }

    public <T extends Message> void messageReceived(T message) {
        messageQueue.offer(message);
    }
}
