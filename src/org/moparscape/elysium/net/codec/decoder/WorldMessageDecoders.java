package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ObjectPrimaryActionMessage;
import org.moparscape.elysium.net.codec.decoder.message.ObjectSecondaryActionMessage;
import org.moparscape.elysium.net.codec.decoder.message.WallObjectPrimaryActionMessage;
import org.moparscape.elysium.net.codec.decoder.message.WallObjectSecondaryActionMessage;

/**
 * Created by daniel on 14/01/2015.
 */
public final class WorldMessageDecoders {

    public final class ObjectPrimaryActionMessageDecoder extends AbstractMessageDecoder<ObjectPrimaryActionMessage> {

        public ObjectPrimaryActionMessageDecoder() {
            super(ObjectPrimaryActionMessage.class, 51);
        }

        public ObjectPrimaryActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class ObjectSecondaryActionMessageDecoder extends AbstractMessageDecoder<ObjectSecondaryActionMessage> {

        public ObjectSecondaryActionMessageDecoder() {
            super(ObjectSecondaryActionMessage.class, 40);
        }

        public ObjectSecondaryActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class WallObjectPrimaryActionMessageDecoder extends AbstractMessageDecoder<WallObjectPrimaryActionMessage> {

        public WallObjectPrimaryActionMessageDecoder() {
            super(WallObjectPrimaryActionMessage.class, 126);
        }

        public WallObjectPrimaryActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class WallObjectSecondaryActionMessageDecoder extends AbstractMessageDecoder<WallObjectSecondaryActionMessage> {

        public WallObjectSecondaryActionMessageDecoder() {
            super(WallObjectSecondaryActionMessage.class, 235);
        }

        public WallObjectSecondaryActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
