package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.LogoutMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LogoutMessageDecoder extends AbstractMessageDecoder<LogoutMessage> {

    public LogoutMessageDecoder() {
        super(LogoutMessage.class, 39);
    }

    public LogoutMessage decode(ChannelBuffer buffer, int length) {
        return new LogoutMessage();
    }
}
