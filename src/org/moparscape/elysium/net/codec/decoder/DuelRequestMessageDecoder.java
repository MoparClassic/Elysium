package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DuelRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelRequestMessageDecoder extends AbstractMessageDecoder<DuelRequestMessage> {

    public DuelRequestMessageDecoder() {
        super(DuelRequestMessage.class, 222);
    }

    public DuelRequestMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
