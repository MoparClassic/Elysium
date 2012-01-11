package org.moparscape.elysium.net;

import org.jboss.netty.channel.Channel;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.codec.Message;
import org.moparscape.elysium.net.handler.HandlerLookupService;
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

    private Player player;

    private Queue<Message> messageQueue = new ConcurrentLinkedQueue<Message>();

    private boolean removing = false;

    public Session(Channel channel) {
        this.channel = channel;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @SuppressWarnings("unchecked")
    public boolean pulse() {
        if (removing) {
            return false;
        }

        Message message;
        int processed = 0;

        // Process all of the messages that have been received from this player.
        // Process the entire queue up to a maximum of 100 messages.
        while ((message = messageQueue.poll()) != null && processed++ < 100) {
            MessageHandler<Message> handler = (MessageHandler<Message>) HandlerLookupService.getHandler(message.getClass());
            if (handler != null) {
                handler.handle(this, player, message);
            }
        }
        return false;
    }

    public <T extends Message> void messageReceived(T message) {
        messageQueue.offer(message);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return o == this;
    }
}
