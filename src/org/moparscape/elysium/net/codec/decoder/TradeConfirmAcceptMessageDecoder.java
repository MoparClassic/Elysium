package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public TradeConfirmAcceptMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
