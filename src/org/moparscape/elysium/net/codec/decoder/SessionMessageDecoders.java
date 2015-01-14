package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.LoginMessage;
import org.moparscape.elysium.net.codec.decoder.message.LogoutMessage;
import org.moparscape.elysium.net.codec.decoder.message.LogoutRequestMessage;
import org.moparscape.elysium.net.codec.decoder.message.SessionRequestMessage;
import org.moparscape.elysium.util.BufferUtil;
import org.moparscape.elysium.util.DataConversions;

/**
 * Created by daniel on 14/01/2015.
 */
public final class SessionMessageDecoders {

    public final class LoginMessageDecoder extends AbstractMessageDecoder<LoginMessage> {

        public LoginMessageDecoder() {
            super(LoginMessage.class, 0);
        }

        public LoginMessage decode(ByteBuf buffer, int length) {
            boolean reconnecting = buffer.readByte() == 1;
            int version = buffer.readShort();
            int loginPacketSize = buffer.readByte();

            byte[] encrypted = new byte[loginPacketSize];
            buffer.readBytes(encrypted);
            ByteBuf loginPacket = DataConversions.decryptRSA(encrypted);

            // Now that we've got the decrypted login packet, parse its payload
            int[] sessionKeys = new int[4];
            for (int i = 0; i < sessionKeys.length; i++) {
                sessionKeys[i] = loginPacket.readInt();
            }

            int uid = loginPacket.readInt();
            String username = BufferUtil.readString(loginPacket, 20).trim();
            loginPacket.skipBytes(1);
            String password = BufferUtil.readString(loginPacket, 20).trim();
//        loginPacket.skipBytes(1);

            return new LoginMessage(username, password, uid, version, sessionKeys, reconnecting);
        }
    }

    public final class LogoutMessageDecoder extends AbstractMessageDecoder<LogoutMessage> {

        public LogoutMessageDecoder() {
            super(LogoutMessage.class, 39);
        }

        public LogoutMessage decode(ByteBuf buffer, int length) {
            return new LogoutMessage();
        }
    }

    public final class LogoutRequestMessageDecoder extends AbstractMessageDecoder<LogoutRequestMessage> {

        public LogoutRequestMessageDecoder() {
            super(LogoutRequestMessage.class, 129);
        }

        public LogoutRequestMessage decode(ByteBuf buffer, int length) {
            return new LogoutRequestMessage();
        }
    }

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
}
