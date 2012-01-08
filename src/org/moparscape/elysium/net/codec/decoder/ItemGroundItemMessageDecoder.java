package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemGroundItemMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
