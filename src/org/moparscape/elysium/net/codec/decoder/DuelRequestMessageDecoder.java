package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelRequestMessageDecoder extends AbstractMessageDecoder<DuelRequestMessage> {

    public DuelRequestMessageDecoder() {
        super(DuelRequestMessage.class, 222);
    }

    public DuelRequestMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
