package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ItemPlayerMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemPlayerMessageDecoder extends AbstractMessageDecoder<ItemPlayerMessage> {

    public ItemPlayerMessageDecoder() {
        super(ItemPlayerMessage.class, 16);
    }

    public ItemPlayerMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
