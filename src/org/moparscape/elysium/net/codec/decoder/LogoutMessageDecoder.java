package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public LogoutMessage decode(ByteBuf buffer, int length) {
        return new LogoutMessage();
    }
}
