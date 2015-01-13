package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemGroundItemMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemGroundItemMessageDecoder extends AbstractMessageDecoder<ItemGroundItemMessage> {

    public ItemGroundItemMessageDecoder() {
        super(ItemGroundItemMessage.class, 34);
    }

    public ItemGroundItemMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
