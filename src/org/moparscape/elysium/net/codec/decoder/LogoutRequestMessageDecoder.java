package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.LogoutRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LogoutRequestMessageDecoder extends AbstractMessageDecoder<LogoutRequestMessage> {

    public LogoutRequestMessageDecoder() {
        super(LogoutRequestMessage.class, 129);
    }

    public LogoutRequestMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
