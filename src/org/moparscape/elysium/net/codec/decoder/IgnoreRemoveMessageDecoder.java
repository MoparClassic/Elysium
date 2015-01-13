package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.IgnoreRemoveMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class IgnoreRemoveMessageDecoder extends AbstractMessageDecoder<IgnoreRemoveMessage> {

    public IgnoreRemoveMessageDecoder() {
        super(IgnoreRemoveMessage.class, 108);
    }

    public IgnoreRemoveMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
