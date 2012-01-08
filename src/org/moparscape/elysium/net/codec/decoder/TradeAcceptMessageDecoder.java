package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.TradeAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TradeAcceptMessageDecoder extends AbstractMessageDecoder<TradeAcceptMessage> {

    public TradeAcceptMessageDecoder() {
        super(TradeAcceptMessage.class, 211);
    }

    public TradeAcceptMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
