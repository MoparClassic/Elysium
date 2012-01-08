package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemItemMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
