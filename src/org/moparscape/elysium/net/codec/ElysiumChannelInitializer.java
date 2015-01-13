package org.moparscape.elysium.net.codec;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.traffic.ChannelTrafficShapingHandler;

/**
 * Created by daniel on 13/01/2015.
 */
public class ElysiumChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        final int kbps = 25;
        final int bytesPerSecond = kbps * 125;

        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ChannelTrafficShapingHandler(bytesPerSecond, bytesPerSecond));
        pipeline.addLast(new ElysiumDecoder());
        pipeline.addLast(new ElysiumConnectionHandler());
    }
}
