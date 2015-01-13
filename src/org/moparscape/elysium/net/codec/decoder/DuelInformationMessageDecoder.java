package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DuelInformationMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelInformationMessageDecoder extends AbstractMessageDecoder<DuelInformationMessage> {

    public DuelInformationMessageDecoder() {
        super(DuelInformationMessage.class, 123);
    }

    public DuelInformationMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
