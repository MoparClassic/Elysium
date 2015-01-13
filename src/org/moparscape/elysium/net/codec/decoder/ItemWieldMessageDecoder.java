package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemWieldMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemWieldMessageDecoder extends AbstractMessageDecoder<ItemWieldMessage> {

    public ItemWieldMessageDecoder() {
        super(ItemWieldMessage.class, 181);
    }

    public ItemWieldMessage decode(ByteBuf buffer, int length) {
        int itemIndex = buffer.readShort();
        return new ItemWieldMessage(itemIndex);
    }
}
