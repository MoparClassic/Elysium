package org.moparscape.elysium.net;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.codec.Message;
import org.moparscape.elysium.net.handler.HandlerLookupService;
import org.moparscape.elysium.net.handler.MessageHandler;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Session {

    private final Channel channel;

    private final AtomicReference<Player> player = new AtomicReference<Player>();

    private final AtomicBoolean removing = new AtomicBoolean(false);

    private final Queue<Message> messageQueue = new ConcurrentLinkedQueue<Message>();

    public Session(Channel channel) {
        this.channel = channel;
    }

    public Player getPlayer() {
        return player.get();
    }

    public void setPlayer(Player player) {
        this.player.getAndSet(player);
    }

    public boolean isRemoving() {
        return removing.get();
    }

    public void setRemoving(boolean removing) {
        this.removing.getAndSet(removing);
    }

    @SuppressWarnings("unchecked")
    public boolean pulse() {
        if (isRemoving()) {
            return false;
        }

        Message message;
        int processed = 0;

        // Process all of the messages that have been received from this player.
        // Process the entire queue up to a maximum of 60 messages.
        while ((message = messageQueue.poll()) != null && processed++ < 60) {
            MessageHandler<Message> handler = (MessageHandler<Message>) HandlerLookupService.getHandler(message.getClass());
            if (handler != null) {
                try {
                    handler.handle(this, getPlayer(), message);
                } catch (Exception e) {
                    System.out.printf("Player Index: %d - Failure during handling of %s\n",
                            getPlayer() == null ? -1 : getPlayer().getIndex(),
                            message.getClass().toString());
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    public <T extends Message> void messageReceived(T message) {
        messageQueue.offer(message);
    }

    public ChannelFuture write(Object o) {
        return channel.write(o);
    }

    public ChannelFuture close() {
        return channel.close();
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
