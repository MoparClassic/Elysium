package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface MessageDecoder<T extends Message> {

    int getOpcode();

    Class<T> getMessageType();

    T decode(ByteBuf buffer, int length);
}
