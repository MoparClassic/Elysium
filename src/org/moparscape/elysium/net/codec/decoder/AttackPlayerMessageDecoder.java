package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public AttackPlayerMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
