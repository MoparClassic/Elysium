package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.ItemLoc;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Item implements Locatable {

    private final ItemLoc loc;

    private Point location;

    public Item(ItemLoc loc) {
        this.loc = loc;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isRemoved() {
        return false;
    }

    public boolean isVisibleTo(Entity e) {
        return false;
    }
}
