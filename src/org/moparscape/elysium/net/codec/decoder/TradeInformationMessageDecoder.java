package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public TradeInformationMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
