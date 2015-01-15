package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Appearance;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.world.Point;

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
    private final AtomicReference<Appearance> appearance = new AtomicReference<Appearance>(new Appearance());
    private final AtomicBoolean appearanceChanged = new AtomicBoolean(true);
    private final AtomicInteger appearanceId = new AtomicInteger(0);
    private final int[][] mobSprites = new int[][]{
            {3, 2, 1},
            {4, -1, 0},
            {5, 6, 7}
    };
    private final AtomicBoolean skulled = new AtomicBoolean(false);
    private final AtomicInteger sprite = new AtomicInteger(1);
    private final AtomicBoolean spriteChanged = new AtomicBoolean(true);
    private final AtomicIntegerArray wornItems = new AtomicIntegerArray(appearance.get().getSprites());
    private volatile Player owner;

    public Sprite(Player owner) {
        this.owner = owner;
    }

    public boolean appearanceChanged() {
        return appearanceChanged.get();
    }

    public Appearance getAppearance() {
        return appearance.get();
    }

    public void setAppearance(Appearance app) {
        appearance.getAndSet(app);
        setAppearanceChanged(true);
    }

    public int getAppearanceId() {
        return appearanceId.get();
    }

    public int getSprite() {
        return sprite.get();
    }

    public void setSprite(int sprite) {
        spriteChanged.getAndSet(true);
        this.sprite.getAndSet(sprite);
    }

    public int[] getSprites() {
        return appearance.get().getSprites();
    }

    public AtomicIntegerArray getWornItems() {
        return wornItems;
    }

    public boolean isSkulled() {
        return skulled.get();
    }

    public void setSkulled(boolean skulled) {
        this.skulled.getAndSet(skulled);
    }

    public void resetSpriteChanged() {
        this.spriteChanged.getAndSet(false);
    }

    public void setAppearanceChanged(boolean changed) {
        this.appearanceChanged.getAndSet(changed);
    }

    public void setWornItem(int index, int itemId) {
        wornItems.getAndSet(index, itemId);
    }

    public boolean spriteChanged() {
        return spriteChanged.get();
    }

    public void updateAppearanceId() {
        if (appearanceChanged.get()) {
            appearanceId.getAndIncrement();
        }
    }

    public void updateSprite(Point newLocation) {
        Point curLoc = this.owner.getLocation();
        try {
            int xIndex = curLoc.getX() - newLocation.getX() + 1;
            int yIndex = curLoc.getY() - newLocation.getY() + 1;
            setSprite(mobSprites[xIndex][yIndex]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
