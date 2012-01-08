package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface MessageDecoder<T extends Message> {

    int getOpcode();

    Class<T> getMessageType();

    T decode(ChannelBuffer buffer, int length);
}
