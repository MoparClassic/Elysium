package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DuelOptionsMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelOptionsMessageDecoder extends AbstractMessageDecoder<DuelOptionsMessage> {

    public DuelOptionsMessageDecoder() {
        super(DuelOptionsMessage.class, 225);
    }

    public DuelOptionsMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
