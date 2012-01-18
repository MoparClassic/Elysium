package org.moparscape.elysium.entity.component;

public final class ItemLoc extends AbstractComponent {
    /**
     * Amount of item (stackables)
     */
    public int amount;
    /**
     * The id of the gameObject
     */
    public int id;
    /**
     * How long the item takes to spawn
     */
    public int respawnTime;
    /**
     * The objects x coord
     */
    public int x;
    /**
     * The objects y coord
     */
    public int y;

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int hashCode() {
        return id | amount | x | y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof ItemLoc)) {
            return false;
        }

        ItemLoc loc = (ItemLoc) o;
        return id == loc.id && amount == loc.amount && x == loc.x && y == loc.y;
    }
}
