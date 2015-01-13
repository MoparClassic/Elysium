package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemDropMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemDropMessageDecoder extends AbstractMessageDecoder<ItemDropMessage> {

    public ItemDropMessageDecoder() {
        super(ItemDropMessage.class, 147);
    }

    public ItemDropMessage decode(ByteBuf buffer, int length) {
        int index = buffer.readShort();
        return new ItemDropMessage(index);
    }
}
