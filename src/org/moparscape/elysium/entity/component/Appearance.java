package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.util.DataConversions;
import org.moparscape.elysium.util.Formulae;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Appearance extends AbstractComponent {

    private int appearanceId = 0;

    private int sprite = 1;

    private boolean appearanceChanged = true;

    private boolean spriteChanged = true;

    private int[] wornItems = new int[12];

    private byte hairColour = 2;
    private byte topColour = 8;
    private byte trouserColour = 14;
    private byte skinColour = 0;

    private int head = 1;
    private int body = 2;

    private boolean skulled = false;

    private Player owner;

    public Appearance() {
        this.wornItems = getSprites();
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public int getAppearanceId() {
        return appearanceId;
    }

    public boolean appearanceChanged() {
        return appearanceChanged;
    }

    public void setAppearanceChanged(boolean changed) {
        this.appearanceChanged = changed;
    }

    public void updateAppearanceId() {
        if (appearanceChanged) {
            appearanceId++;
        }
    }

    public int[] getSprites() {
        return new int[]{head, body, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
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

    public boolean isValid() {
        if (!DataConversions.inArray(Formulae.headSprites, head) || !DataConversions.inArray(Formulae.bodySprites, body)) {
            return false;
        }
        if (hairColour < 0 || topColour < 0 || trouserColour < 0 || skinColour < 0) {
            return false;
        }
        if (hairColour > 9 || topColour > 14 || trouserColour > 14 || skinColour > 4) {
            return false;
        }
        return true;
    }
}
