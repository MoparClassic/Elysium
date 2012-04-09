package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemPickupMessage decode(ChannelBuffer buffer, int length) {
        int x = buffer.readShort();
        int y = buffer.readShort();
        int itemId = buffer.readShort();
        return new ItemPickupMessage(itemId, x, y);
    }
}
