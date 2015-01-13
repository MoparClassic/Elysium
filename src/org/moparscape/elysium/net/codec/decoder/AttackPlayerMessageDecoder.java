package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.AttackPlayerMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AttackPlayerMessageDecoder extends AbstractMessageDecoder<AttackPlayerMessage> {

    public AttackPlayerMessageDecoder() {
        super(AttackPlayerMessage.class, 57);
    }

    public AttackPlayerMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
