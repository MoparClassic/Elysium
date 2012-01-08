package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ShopBuyMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ShopBuyMessageDecoder extends AbstractMessageDecoder<ShopBuyMessage> {

    public ShopBuyMessageDecoder() {
        super(ShopBuyMessage.class, 128);
    }

    public ShopBuyMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
