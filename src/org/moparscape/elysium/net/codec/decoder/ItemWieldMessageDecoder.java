package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.ItemWieldMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemWieldMessageDecoder extends AbstractMessageDecoder<ItemWieldMessage> {

    public ItemWieldMessageDecoder() {
        super(ItemWieldMessage.class, 181);
    }

    public ItemWieldMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
