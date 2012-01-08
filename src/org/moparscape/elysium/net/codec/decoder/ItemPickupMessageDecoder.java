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
        throw new UnsupportedOperationException();
    }
}
