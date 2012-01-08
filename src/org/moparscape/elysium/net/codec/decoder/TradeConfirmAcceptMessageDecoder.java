package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.TradeConfirmAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TradeConfirmAcceptMessageDecoder extends AbstractMessageDecoder<TradeConfirmAcceptMessage> {

    public TradeConfirmAcceptMessageDecoder() {
        super(TradeConfirmAcceptMessage.class, 53);
    }

    public TradeConfirmAcceptMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
