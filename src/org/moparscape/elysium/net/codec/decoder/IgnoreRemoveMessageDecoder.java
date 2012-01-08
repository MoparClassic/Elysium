package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.IgnoreRemoveMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class IgnoreRemoveMessageDecoder extends AbstractMessageDecoder<IgnoreRemoveMessage> {

    public IgnoreRemoveMessageDecoder() {
        super(IgnoreRemoveMessage.class, 108);
    }

    public IgnoreRemoveMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
