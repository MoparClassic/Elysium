package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public LogoutRequestMessage decode(ByteBuf buffer, int length) {
        return new LogoutRequestMessage();
    }
}
