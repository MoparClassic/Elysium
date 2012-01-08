package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellDoorMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellDoorMessageDecoder extends AbstractMessageDecoder<SpellDoorMessage> {

    public SpellDoorMessageDecoder() {
        super(SpellDoorMessage.class, 67);
    }

    public SpellDoorMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
