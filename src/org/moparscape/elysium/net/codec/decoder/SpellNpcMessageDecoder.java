package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellNpcMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellNpcMessageDecoder extends AbstractMessageDecoder<SpellNpcMessage> {

    public SpellNpcMessageDecoder() {
        super(SpellNpcMessage.class, 71);
    }

    public SpellNpcMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
