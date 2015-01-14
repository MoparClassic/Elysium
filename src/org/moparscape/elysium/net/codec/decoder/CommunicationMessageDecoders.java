package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;

/**
 * Created by daniel on 14/01/2015.
 */
public final class CommunicationMessageDecoders {

    public final class FriendAddMessageDecoder extends AbstractMessageDecoder<FriendAddMessage> {

        public FriendAddMessageDecoder() {
            super(FriendAddMessage.class, 168);
        }

        public FriendAddMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class FriendPmMessageDecoder extends AbstractMessageDecoder<FriendPmMessage> {

        public FriendPmMessageDecoder() {
            super(FriendPmMessage.class, 254);
        }

        public FriendPmMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class FriendRemoveMessageDecoder extends AbstractMessageDecoder<FriendRemoveMessage> {

        public FriendRemoveMessageDecoder() {
            super(FriendRemoveMessage.class, 52);
        }

        public FriendRemoveMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class IgnoreAddMessageDecoder extends AbstractMessageDecoder<IgnoreAddMessage> {

        public IgnoreAddMessageDecoder() {
            super(IgnoreAddMessage.class, 25);
        }

        public IgnoreAddMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class IgnoreRemoveMessageDecoder extends AbstractMessageDecoder<IgnoreRemoveMessage> {

        public IgnoreRemoveMessageDecoder() {
            super(IgnoreRemoveMessage.class, 108);
        }

        public IgnoreRemoveMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class PrivacySettingMessageDecoder extends AbstractMessageDecoder<PrivacySettingMessage> {

        public PrivacySettingMessageDecoder() {
            super(PrivacySettingMessage.class, 176);
        }

        public PrivacySettingMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class PublicChatMessageDecoder extends AbstractMessageDecoder<PublicChatMessage> {

        public PublicChatMessageDecoder() {
            super(PublicChatMessage.class, 145);
        }

        public PublicChatMessage decode(ByteBuf buffer, int length) {
            byte[] message = new byte[length];
            buffer.readBytes(message);
            return new PublicChatMessage(message);
        }
    }
}
