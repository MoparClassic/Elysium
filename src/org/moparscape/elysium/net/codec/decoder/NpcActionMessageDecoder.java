package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.NpcActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class NpcActionMessageDecoder extends AbstractMessageDecoder<NpcActionMessage> {

    public NpcActionMessageDecoder() {
        super(NpcActionMessage.class, 74);
    }

    public NpcActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
