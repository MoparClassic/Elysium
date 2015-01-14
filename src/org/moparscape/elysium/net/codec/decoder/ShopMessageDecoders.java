package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ShopBuyMessage;
import org.moparscape.elysium.net.codec.decoder.message.ShopCloseMessage;
import org.moparscape.elysium.net.codec.decoder.message.ShopSellMessage;

/**
 * Created by daniel on 14/01/2015.
 */
public final class ShopMessageDecoders {

    public final class ShopBuyMessageDecoder extends AbstractMessageDecoder<ShopBuyMessage> {

        public ShopBuyMessageDecoder() {
            super(ShopBuyMessage.class, 128);
        }

        public ShopBuyMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class ShopCloseMessageDecoder extends AbstractMessageDecoder<ShopCloseMessage> {

        public ShopCloseMessageDecoder() {
            super(ShopCloseMessage.class, 253);
        }

        public ShopCloseMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class ShopSellMessageDecoder extends AbstractMessageDecoder<ShopSellMessage> {

        public ShopSellMessageDecoder() {
            super(ShopSellMessage.class, 255);
        }

        public ShopSellMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
