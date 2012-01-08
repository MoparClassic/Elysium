package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.SessionRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionRequestMessageDecoder extends AbstractMessageDecoder<SessionRequestMessage> {

    public SessionRequestMessageDecoder() {
        super(SessionRequestMessage.class, 32);
    }

    public SessionRequestMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
