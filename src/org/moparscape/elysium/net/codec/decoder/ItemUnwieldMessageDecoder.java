package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public ItemUnwieldMessage decode(ByteBuf buffer, int length) {
        int itemIndex = buffer.readShort();
        return new ItemUnwieldMessage(itemIndex);
    }
}
