package org.moparscape.elysium.net.codec;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.moparscape.elysium.net.codec.decoder.MessageDecoder;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ElysiumDecoder extends FrameDecoder {

    public Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer)
            throws Exception {
        buffer.markReaderIndex();

        // If there are not enough bytes to read a length, return
        if (buffer.readableBytes() < 2) {
            return null;
        }

        boolean shortLen = false;
        int length = buffer.readByte();
        if (length >= 160) {
            length = (length - 160) * 256 + buffer.readByte();
            shortLen = true;
        }

        // If the remaining payload has less than length bytes then
        // the packet hasn't fully arrived. Rewind the buffer and return.
        if (length > buffer.readableBytes()) {
            buffer.resetReaderIndex();
            return null;
        }

        // If the length is a short, or the packet has no payload, then we can just
        // read the contents of buffer.
        // However, if the length is a byte then the next byte is actually the last
        // value of the payload, so we need to put it back in its correct position.
        int opcode;
        ChannelBuffer payload;
        if (shortLen || length <= 1) {
            opcode = buffer.readUnsignedByte();
            payload = buffer;
        } else {
            // A length greater than one indicates that there is a payload after
            // the opcode. Fix the order of the payload.
            byte[] data = new byte[length - 1];
            data[length - 2] = buffer.readByte();
            opcode = buffer.readUnsignedByte();
            buffer.readBytes(data, 0, length - 2);
            payload = ChannelBuffers.wrappedBuffer(data);
        }

        System.out.println("OPCODE: " + opcode);
        MessageDecoder<?> decoder = CodecLookupService.getDecoder(opcode);
        if (decoder == null) {
            System.out.println("No decoder for " + opcode);
            throw new IOException("Unknown opcode: " + opcode);
        }

        // Reduce length by 1 so that it is only referring to the payload's length, without the opcode
        --length;

        return decoder.decode(payload, length);
    }

    private void dump(ChannelBuffer buffer, int opcode, int length) {
        buffer.markReaderIndex();

        // Dump the contents as a string of unsigned bytes
        StringBuilder sb = new StringBuilder(2000);
        sb.append("Opcode = ").append(opcode).append(" Len = ").append(length).append("\n");
        sb.append(buffer.readUnsignedByte());
        for (int i = 0; i < length - 1; i++) {
            sb.append(", ").append(buffer.readUnsignedByte());
        }
        System.out.println(sb.toString());

        buffer.resetReaderIndex();
    }
}
