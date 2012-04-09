package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WalkTargetMessage extends AbstractMessage {

    private final int startX;

    private final int startY;

    private final int steps;

    private final byte[] xOffsets;

    private final byte[] yOffsets;

    public WalkTargetMessage(int startX, int startY, int steps, byte[] xOffsets, byte[] yOffsets) {
        this.startX = startX;
        this.startY = startY;
        this.steps = steps;
        this.xOffsets = xOffsets;
        this.yOffsets = yOffsets;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getSteps() {
        return steps;
    }

    public byte[] getXOffsets() {
        return xOffsets;
    }

    public byte[] getYOffsets() {
        return yOffsets;
    }
}
