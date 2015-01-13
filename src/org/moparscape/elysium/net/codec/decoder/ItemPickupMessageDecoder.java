package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemPickupMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemPickupMessageDecoder extends AbstractMessageDecoder<ItemPickupMessage> {

    public ItemPickupMessageDecoder() {
        super(ItemPickupMessage.class, 245);
    }

    public ItemPickupMessage decode(ByteBuf buffer, int length) {
        int x = buffer.readShort();
        int y = buffer.readShort();
        int itemId = buffer.readShort();
        return new ItemPickupMessage(itemId, x, y);
    }
}
