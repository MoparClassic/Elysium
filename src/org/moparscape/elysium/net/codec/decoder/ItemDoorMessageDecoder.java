package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemDoorMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
