package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.SessionRequestMessage;
import org.moparscape.elysium.util.BufferUtil;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionRequestMessageDecoder extends AbstractMessageDecoder<SessionRequestMessage> {

    public SessionRequestMessageDecoder() {
        super(SessionRequestMessage.class, 32);
    }

    public SessionRequestMessage decode(ByteBuf buffer, int length) {
        byte userByte = buffer.readByte();
        String className = BufferUtil.readString(buffer);

        return new SessionRequestMessage(className, userByte);
    }
}
