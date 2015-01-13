package org.moparscape.elysium.net.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.moparscape.elysium.Server;
import org.moparscape.elysium.net.Session;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumConnectionHandler extends SimpleChannelInboundHandler<Message> {

    private volatile Session session;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        this.session = new Session(ctx.channel());

        Server server = Server.getInstance();
        server.registerSession(session);

        System.out.println("Channel connected");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Session session = this.session;
        System.out.println("Channel disconnected");
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, Message message) {
        session.messageReceived(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        throw new IllegalStateException(cause);
    }
}
