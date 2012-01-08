package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellSelfMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellSelfMessageDecoder extends AbstractMessageDecoder<SpellSelfMessage> {

    public SpellSelfMessageDecoder() {
        super(SpellSelfMessage.class, 206);
    }

    public SpellSelfMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
