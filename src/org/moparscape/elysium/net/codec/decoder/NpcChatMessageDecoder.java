package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.NpcChatMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class NpcChatMessageDecoder extends AbstractMessageDecoder<NpcChatMessage> {

    public NpcChatMessageDecoder() {
        super(NpcChatMessage.class, 177);
    }

    public NpcChatMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
