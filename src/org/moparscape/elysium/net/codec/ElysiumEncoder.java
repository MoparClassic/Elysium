package org.moparscape.elysium.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.moparscape.elysium.net.codec.encoder.MessageEncoder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumEncoder extends OneToOneEncoder {

    @SuppressWarnings("unchecked")
    @Override
    public Object encode(ChannelHandlerContext ctx, Channel channel, Object message) {
        if (message instanceof Message) {
            Message msg = (Message) message;

            Class<? extends Message> clazz = msg.getClass();
            MessageEncoder<Message> encoder = (MessageEncoder<Message>) CodecLookupService.getEncoder(clazz);
            if (encoder == null) {
                throw new RuntimeException("Unknown message type: " + clazz);
            }

            ChannelBuffer opcodeBuffer = ChannelBuffers.buffer(1);
            opcodeBuffer.writeByte(encoder.getOpcode());

            return ChannelBuffers.wrappedBuffer(opcodeBuffer, encoder.encode(msg));
        }

        return message;
    }
}
