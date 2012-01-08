package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ItemDropMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
