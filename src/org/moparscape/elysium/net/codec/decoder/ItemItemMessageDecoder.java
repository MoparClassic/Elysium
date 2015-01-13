package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemItemMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemItemMessageDecoder extends AbstractMessageDecoder<ItemItemMessage> {

    public ItemItemMessageDecoder() {
        super(ItemItemMessage.class, 27);
    }

    public ItemItemMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
