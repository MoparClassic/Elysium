package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DuelConfirmAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelConfirmAcceptMessageDecoder extends AbstractMessageDecoder<DuelConfirmAcceptMessage> {

    public DuelConfirmAcceptMessageDecoder() {
        super(DuelConfirmAcceptMessage.class, 87);
    }

    public DuelConfirmAcceptMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
