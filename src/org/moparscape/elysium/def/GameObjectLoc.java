package org.moparscape.elysium.def;

public class GameObjectLoc {
    /**
     * The direction it faces
     */
    public int direction;
    /**
     * The id of the gameObject
     */
    public int id;
    /**
     * Type of object - 0: Object, 1: WallObject
     */
    public int type;
    /**
     * The objects x coord
     */
    public int x;
    /**
     * The objects y coord
     */
    public int y;

    public GameObjectLoc(int id, int x, int y, int direction, int type) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = type;
    }

    public int getDirection() {
        return direction;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return id | x | y | type | direction;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof GameObjectLoc)) {
            return false;
        }

        GameObjectLoc loc = (GameObjectLoc) o;
        return id == loc.id && x == loc.x && y == loc.y && type == loc.type && direction == loc.direction;
    }
}
