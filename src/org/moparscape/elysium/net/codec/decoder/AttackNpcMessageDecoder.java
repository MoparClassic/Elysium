package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public AttackNpcMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
