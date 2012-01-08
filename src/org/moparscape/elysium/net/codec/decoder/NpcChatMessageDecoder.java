package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public NpcChatMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
