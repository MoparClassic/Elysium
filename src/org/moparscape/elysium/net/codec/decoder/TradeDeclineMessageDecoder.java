package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.TradeDeclineMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TradeDeclineMessageDecoder extends AbstractMessageDecoder<TradeDeclineMessage> {

    public TradeDeclineMessageDecoder() {
        super(TradeDeclineMessage.class, 216);
    }

    public TradeDeclineMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
