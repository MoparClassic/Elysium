package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.InventoryActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class InventoryActionMessageDecoder extends AbstractMessageDecoder<InventoryActionMessage> {

    public InventoryActionMessageDecoder() {
        super(InventoryActionMessage.class, 89);
    }

    public InventoryActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
