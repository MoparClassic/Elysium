package org.moparscape.elysium.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.replay.ReplayingDecoder;
import org.jboss.netty.handler.codec.replay.VoidEnum;
import org.moparscape.elysium.net.codec.decoder.MessageDecoder;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumDecoder extends ReplayingDecoder<VoidEnum> {

    public Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer, VoidEnum state)
            throws Exception {
        int opcode = buffer.readUnsignedByte();

        MessageDecoder<?> decoder = CodecLookupService.getDecoder(opcode);
        if (decoder == null) {
            throw new IOException("Unknown opcode: " + opcode);
        }

        return decoder.decode(buffer);
    }
}
