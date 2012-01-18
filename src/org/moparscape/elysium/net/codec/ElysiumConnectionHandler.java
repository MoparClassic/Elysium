package org.moparscape.elysium.net.codec;

import org.jboss.netty.channel.*;
import org.moparscape.elysium.Server;
import org.moparscape.elysium.net.Session;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumConnectionHandler extends SimpleChannelUpstreamHandler {

    private static final Server server = Server.getInstance();

    @Override
    public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Channel chan = e.getChannel();
        Session session = new Session(chan);
        ctx.setAttachment(session);
        server.registerSession(session);
        System.out.println("Channel connected");
    }

    @Override
    public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
        Session session = (Session) ctx.getAttachment();
        System.out.println("Channel disconnected");
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
        Session session = (Session) ctx.getAttachment();
        session.messageReceived((Message) e.getMessage());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        Session session = (Session) ctx.getAttachment();
    }
}
