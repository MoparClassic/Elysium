package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public SpellDoorMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
