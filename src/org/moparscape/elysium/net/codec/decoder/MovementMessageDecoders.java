package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.FollowRequestMessage;
import org.moparscape.elysium.net.codec.decoder.message.WalkMessage;
import org.moparscape.elysium.net.codec.decoder.message.WalkTargetMessage;

/**
 * Created by daniel on 14/01/2015.
 */
public final class MovementMessageDecoders {

    public final class FollowRequestMessageDecoder extends AbstractMessageDecoder<FollowRequestMessage> {

        public FollowRequestMessageDecoder() {
            super(FollowRequestMessage.class, 68);
        }

        public FollowRequestMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class WalkMessageDecoder extends AbstractMessageDecoder<WalkMessage> {

        public WalkMessageDecoder() {
            super(WalkMessage.class, 132);
        }

        public WalkMessage decode(ByteBuf buffer, int length) {
            int startX = buffer.readShort();
            int startY = buffer.readShort();
            int steps = (length - 4) / 2;
            byte[] xOffsets = new byte[steps];
            byte[] yOffsets = new byte[steps];

            for (int i = 0; i < steps; i++) {
                xOffsets[i] = buffer.readByte();
                yOffsets[i] = buffer.readByte();
            }
            return new WalkMessage(startX, startY, steps, xOffsets, yOffsets);
        }
    }

    public final class WalkTargetMessageDecoder extends AbstractMessageDecoder<WalkTargetMessage> {

        public WalkTargetMessageDecoder() {
            super(WalkTargetMessage.class, 246);
        }

        public WalkTargetMessage decode(ByteBuf buffer, int length) {
            int startX = buffer.readShort();
            int startY = buffer.readShort();
            int steps = (length - 4) / 2;
            byte[] xOffsets = new byte[steps];
            byte[] yOffsets = new byte[steps];

            for (int i = 0; i < steps; i++) {
                xOffsets[i] = buffer.readByte();
                yOffsets[i] = buffer.readByte();
            }
            return new WalkTargetMessage(startX, startY, steps, xOffsets, yOffsets);
        }
    }
}
