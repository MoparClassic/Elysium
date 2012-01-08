package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ShopSellMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ShopSellMessageDecoder extends AbstractMessageDecoder<ShopSellMessage> {

    public ShopSellMessageDecoder() {
        super(ShopSellMessage.class, 255);
    }

    public ShopSellMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
