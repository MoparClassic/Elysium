package org.moparscape.elysium.net.codec.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.InvalidMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class InvalidMessageEncoder implements MessageEncoder<InvalidMessage> {

    public Class<InvalidMessage> getMessageType() {
        return InvalidMessage.class;
    }

    public ChannelBuffer encode(InvalidMessage message) {
        throw new UnsupportedOperationException();
    }
}
