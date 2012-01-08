package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SpellGameObjectMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellGameObjectMessageDecoder extends AbstractMessageDecoder<SpellGameObjectMessage> {

    public SpellGameObjectMessageDecoder() {
        super(SpellGameObjectMessage.class, 17);
    }

    public SpellGameObjectMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
