package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ItemGameObjectMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemGameObjectMessageDecoder extends AbstractMessageDecoder<ItemGameObjectMessage> {

    public ItemGameObjectMessageDecoder() {
        super(ItemGameObjectMessage.class, 94);
    }

    public ItemGameObjectMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
