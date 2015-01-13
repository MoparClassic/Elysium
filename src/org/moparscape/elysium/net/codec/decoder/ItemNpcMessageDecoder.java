package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ItemNpcMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemNpcMessageDecoder extends AbstractMessageDecoder<ItemNpcMessage> {

    public ItemNpcMessageDecoder() {
        super(ItemNpcMessage.class, 142);
    }

    public ItemNpcMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
