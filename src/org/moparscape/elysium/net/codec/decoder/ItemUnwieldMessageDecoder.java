package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ItemUnwieldMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemUnwieldMessageDecoder extends AbstractMessageDecoder<ItemUnwieldMessage> {

    public ItemUnwieldMessageDecoder() {
        super(ItemUnwieldMessage.class, 92);
    }

    public ItemUnwieldMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
