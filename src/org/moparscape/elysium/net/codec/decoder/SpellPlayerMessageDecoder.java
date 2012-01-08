package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellPlayerMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellPlayerMessageDecoder extends AbstractMessageDecoder<SpellPlayerMessage> {

    public SpellPlayerMessageDecoder() {
        super(SpellPlayerMessage.class, 55);
    }

    public SpellPlayerMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
