package org.moparscape.elysium.util;

import org.jboss.netty.buffer.ChannelBuffer;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BufferUtil {

    public static String readString(ChannelBuffer buffer, int length) {
        byte[] data = new byte[length];
        buffer.readBytes(data);
        return new String(data, 0, length);
    }
}
