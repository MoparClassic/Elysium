package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.util.DataConversions;
import org.moparscape.elysium.util.Formulae;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Appearance extends AbstractComponent {

    private AtomicInteger appearanceId = new AtomicInteger(0);

    private AtomicInteger sprite = new AtomicInteger(1);

    private AtomicBoolean appearanceChanged = new AtomicBoolean(true);

    private AtomicBoolean spriteChanged = new AtomicBoolean(true);

    private int[] wornItems = new int[12];

    private byte hairColour = 2;
    private byte topColour = 8;
    private byte trouserColour = 14;
    private byte skinColour = 0;

    private int head = 1;
    private int body = 2;

    private boolean skulled = false;

    private volatile Player owner;

    public Appearance() {
        this.wornItems = getSprites();
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public int getAppearanceId() {
        return appearanceId.get();
    }

    public boolean appearanceChanged() {
        return appearanceChanged.get();
    }

    public void setAppearanceChanged(boolean changed) {
        this.appearanceChanged.getAndSet(changed);
    }

    public void updateAppearanceId() {
        if (appearanceChanged.get()) {
            appearanceId.getAndIncrement();
        }
    }

    public int[] getSprites() {
        return new int[]{head, body, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    }

    public void resetSpriteChanged() {
        this.spriteChanged.getAndSet(false);
    }

    public int getSprite() {
        return sprite.get();
    }

    public boolean spriteChanged() {
        return spriteChanged.get();
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
