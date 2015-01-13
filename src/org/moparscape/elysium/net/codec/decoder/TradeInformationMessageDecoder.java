package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.TradeInformationMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TradeInformationMessageDecoder extends AbstractMessageDecoder<TradeInformationMessage> {

    public TradeInformationMessageDecoder() {
        super(TradeInformationMessage.class, 70);
    }

    public TradeInformationMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
