package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public AttackStyleMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
