package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;

/**
 * Created by daniel on 14/01/2015.
 */
public final class CombatMessageDecoders {

    public final class AttackNpcMessageDecoder extends AbstractMessageDecoder<AttackNpcMessage> {

        public AttackNpcMessageDecoder() {
            super(AttackNpcMessage.class, 73);
        }

        public AttackNpcMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class AttackPlayerMessageDecoder extends AbstractMessageDecoder<AttackPlayerMessage> {

        public AttackPlayerMessageDecoder() {
            super(AttackPlayerMessage.class, 57);
        }

        public AttackPlayerMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class AttackStyleMessageDecoder extends AbstractMessageDecoder<AttackStyleMessage> {

        public AttackStyleMessageDecoder() {
            super(AttackStyleMessage.class, 42);
        }

        public AttackStyleMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class PrayerActivateMessageDecoder extends AbstractMessageDecoder<PrayerActivateMessage> {

        public PrayerActivateMessageDecoder() {
            super(PrayerActivateMessage.class, 56);
        }

        public PrayerActivateMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class PrayerDeactivateMessageDecoder extends AbstractMessageDecoder<PrayerDeactivateMessage> {

        public PrayerDeactivateMessageDecoder() {
            super(PrayerDeactivateMessage.class, 248);
        }

        public PrayerDeactivateMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
