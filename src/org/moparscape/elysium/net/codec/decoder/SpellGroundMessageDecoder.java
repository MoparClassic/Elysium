package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellGroundMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellGroundMessageDecoder extends AbstractMessageDecoder<SpellGroundMessage> {

    public SpellGroundMessageDecoder() {
        super(SpellGroundMessage.class, 232);
    }

    public SpellGroundMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
