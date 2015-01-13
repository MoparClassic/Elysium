package org.moparscape.elysium.util;

import io.netty.buffer.ByteBuf;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BufferUtil {

    public static String readString(ByteBuf buffer) {
        return readString(buffer, buffer.readableBytes());
    }

    public static String readString(ByteBuf buffer, int length) {
        byte[] data = new byte[length];
        buffer.readBytes(data);
        return new String(data, 0, length);
    }
}
