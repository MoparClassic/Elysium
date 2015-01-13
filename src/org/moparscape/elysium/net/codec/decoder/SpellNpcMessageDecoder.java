package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public SpellNpcMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
