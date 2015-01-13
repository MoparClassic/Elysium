package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public ShopBuyMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
