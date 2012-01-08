package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.PingMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PingMessageDecoder extends AbstractMessageDecoder<PingMessage> {

    public PingMessageDecoder() {
        super(PingMessage.class, 5);
    }

    public PingMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
