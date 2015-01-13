package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public SpellSelfMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
