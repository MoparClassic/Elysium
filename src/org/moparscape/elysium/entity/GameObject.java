package org.moparscape.elysium.entity;

import org.moparscape.elysium.def.GameObjectLoc;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameObject implements Locatable {

    private final GameObjectLoc loc;
    private int direction;
    private int id;
    private Point location;
    private int type;

    public GameObject(GameObjectLoc loc) {
        this.loc = loc;
    }

    public int getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public boolean isRemoved() {
        return false;
    }
}
