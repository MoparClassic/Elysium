package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.WalkTargetMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
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
