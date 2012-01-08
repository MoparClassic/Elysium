package org.moparscape.elysium.net.codec.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface MessageEncoder<T extends Message> {

    int getOpcode();

    Class<T> getMessageType();

    ChannelBuffer encode(T message);
}
