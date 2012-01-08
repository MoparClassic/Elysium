package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public NpcActionMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
