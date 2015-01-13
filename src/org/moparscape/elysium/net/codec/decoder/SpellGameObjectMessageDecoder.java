package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public SpellGameObjectMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
