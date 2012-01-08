package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemNpcMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
