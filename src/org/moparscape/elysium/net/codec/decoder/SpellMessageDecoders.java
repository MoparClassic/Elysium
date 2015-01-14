package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;

/**
 * Created by daniel on 14/01/2015.
 */
public final class SpellMessageDecoders {

    public final class SpellDoorMessageDecoder extends AbstractMessageDecoder<SpellDoorMessage> {

        public SpellDoorMessageDecoder() {
            super(SpellDoorMessage.class, 67);
        }

        public SpellDoorMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellGameObjectMessageDecoder extends AbstractMessageDecoder<SpellGameObjectMessage> {

        public SpellGameObjectMessageDecoder() {
            super(SpellGameObjectMessage.class, 17);
        }

        public SpellGameObjectMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellGroundItemMessageDecoder extends AbstractMessageDecoder<SpellGroundItemMessage> {

        public SpellGroundItemMessageDecoder() {
            super(SpellGroundItemMessage.class, 104);
        }

        public SpellGroundItemMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellGroundMessageDecoder extends AbstractMessageDecoder<SpellGroundMessage> {

        public SpellGroundMessageDecoder() {
            super(SpellGroundMessage.class, 232);
        }

        public SpellGroundMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellInvItemMessageDecoder extends AbstractMessageDecoder<SpellInvItemMessage> {

        public SpellInvItemMessageDecoder() {
            super(SpellInvItemMessage.class, 49);
        }

        public SpellInvItemMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellNpcMessageDecoder extends AbstractMessageDecoder<SpellNpcMessage> {

        public SpellNpcMessageDecoder() {
            super(SpellNpcMessage.class, 71);
        }

        public SpellNpcMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellPlayerMessageDecoder extends AbstractMessageDecoder<SpellPlayerMessage> {

        public SpellPlayerMessageDecoder() {
            super(SpellPlayerMessage.class, 55);
        }

        public SpellPlayerMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SpellSelfMessageDecoder extends AbstractMessageDecoder<SpellSelfMessage> {

        public SpellSelfMessageDecoder() {
            super(SpellSelfMessage.class, 206);
        }

        public SpellSelfMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
