package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.AttackStyleMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AttackStyleMessageDecoder extends AbstractMessageDecoder<AttackStyleMessage> {

    public AttackStyleMessageDecoder() {
        super(AttackStyleMessage.class, 42);
    }

    public AttackStyleMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
