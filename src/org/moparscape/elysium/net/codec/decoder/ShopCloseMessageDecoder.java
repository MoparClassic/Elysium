package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ShopCloseMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ShopCloseMessageDecoder extends AbstractMessageDecoder<ShopCloseMessage> {

    public ShopCloseMessageDecoder() {
        super(ShopCloseMessage.class, 253);
    }

    public ShopCloseMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
