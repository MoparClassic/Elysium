package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Appearance;
import org.moparscape.elysium.entity.Player;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Sprite extends AbstractComponent {

    private static final int MAX_WORN_ITEMS = 12;

    private final AtomicInteger appearanceId = new AtomicInteger(0);

    private final AtomicInteger sprite = new AtomicInteger(1);

    private final AtomicBoolean appearanceChanged = new AtomicBoolean(true);

    private final AtomicBoolean spriteChanged = new AtomicBoolean(true);

    private final AtomicReference<Appearance> appearance = new AtomicReference<Appearance>(new Appearance());

    private final AtomicIntegerArray wornItems = new AtomicIntegerArray(appearance.get().getSprites());

    private final AtomicBoolean skulled = new AtomicBoolean(false);

    private final AtomicReference<Player> owner = new AtomicReference<Player>();

    public Sprite() {
    }

    public void setOwner(Player player) {
        owner.getAndSet(player);
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

    public Appearance getAppearance() {
        return appearance.get();
    }

    public void setAppearance(Appearance app) {
        appearance.getAndSet(app);
        setAppearanceChanged(true);
    }

    public int[] getSprites() {
        return appearance.get().getSprites();
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

    public AtomicIntegerArray getWornItems() {
        return wornItems;
    }

    public void setWornItem(int index, int itemId) {
        if (index < 0 || index >= MAX_WORN_ITEMS) {
            throw new IllegalArgumentException("Invalid worn item index: " + index);
        }
        wornItems.getAndSet(index, itemId);
    }

    public boolean isSkulled() {
        return skulled.get();
    }

    public void setSkulled(boolean skulled) {
        this.skulled.getAndSet(skulled);
    }
}
