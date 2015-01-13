package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemDoorMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemDoorMessageDecoder extends AbstractMessageDecoder<ItemDoorMessage> {

    public ItemDoorMessageDecoder() {
        super(ItemDoorMessage.class, 36);
    }

    public ItemDoorMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
