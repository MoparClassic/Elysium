package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.NpcActionMessage;
import org.moparscape.elysium.net.codec.decoder.message.NpcChatMessage;

/**
 * Created by daniel on 14/01/2015.
 */
public final class NpcMessageDecoders {

    public final class NpcActionMessageDecoder extends AbstractMessageDecoder<NpcActionMessage> {

        public NpcActionMessageDecoder() {
            super(NpcActionMessage.class, 74);
        }

        public NpcActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class NpcChatMessageDecoder extends AbstractMessageDecoder<NpcChatMessage> {

        public NpcChatMessageDecoder() {
            super(NpcChatMessage.class, 177);
        }

        public NpcChatMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
