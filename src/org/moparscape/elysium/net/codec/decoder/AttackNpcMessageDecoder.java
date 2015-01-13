package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.AttackNpcMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AttackNpcMessageDecoder extends AbstractMessageDecoder<AttackNpcMessage> {

    public AttackNpcMessageDecoder() {
        super(AttackNpcMessage.class, 73);
    }

    public AttackNpcMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
