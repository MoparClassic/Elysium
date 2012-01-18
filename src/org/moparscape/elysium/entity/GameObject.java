package org.moparscape.elysium.entity;

import org.moparscape.elysium.external.GameObjectLoc;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameObject implements Locatable {

    private final GameObjectLoc loc;

    private Point location;

    public GameObject(GameObjectLoc loc) {
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
}
