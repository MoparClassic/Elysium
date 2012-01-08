package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.TradeRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TradeRequestMessageDecoder extends AbstractMessageDecoder<TradeRequestMessage> {

    public TradeRequestMessageDecoder() {
        super(TradeRequestMessage.class, 166);
    }

    public TradeRequestMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
