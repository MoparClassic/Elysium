package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.SpellInvItemMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SpellInvItemMessageDecoder extends AbstractMessageDecoder<SpellInvItemMessage> {

    public SpellInvItemMessageDecoder() {
        super(SpellInvItemMessage.class, 49);
    }

    public SpellInvItemMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
