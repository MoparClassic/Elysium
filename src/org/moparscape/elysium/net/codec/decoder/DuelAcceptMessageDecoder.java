package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DuelAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelAcceptMessageDecoder extends AbstractMessageDecoder<DuelAcceptMessage> {

    public DuelAcceptMessageDecoder() {
        super(DuelAcceptMessage.class, 252);
    }

    public DuelAcceptMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
