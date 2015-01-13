package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public TradeAcceptMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
