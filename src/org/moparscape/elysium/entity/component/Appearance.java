package org.moparscape.elysium.entity.component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Appearance extends AbstractComponent {

    private int appearanceId = 0;

    private int sprite = 1;

    private boolean appearanceChanged = false;

    private boolean spriteChanged = false;

    private int[] wornItems = new int[12];

    private byte hairColour, topColour, trouserColour, skinColour;

    private boolean skulled = false;

    public Appearance() {

    }

    public int getAppearanceId() {
        return appearanceId;
    }

    public void setAppearanceChanged(boolean changed) {
        this.appearanceChanged = changed;
    }

    public void updateAppearanceId() {
        if (appearanceChanged) {
            appearanceId++;
        }
    }

    public void resetSpriteChanged() {
        spriteChanged = false;
    }

    public int getSprite() {
        return sprite;
    }

    public boolean spriteChanged() {
        return spriteChanged;
    }

    public int[] getWornItems() {
        return wornItems;
    }

    public byte getHairColour() {
        return hairColour;
    }

    public byte getTopColour() {
        return topColour;
    }

    public byte getTrouserColour() {
        return trouserColour;
    }

    public byte getSkinColour() {
        return skinColour;
    }

    public boolean isSkulled() {
        return skulled;
    }
}
