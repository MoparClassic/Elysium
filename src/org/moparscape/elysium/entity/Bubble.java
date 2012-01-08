package org.moparscape.elysium.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Bubble {

    private final Entity owner;

    private final int itemId;

    public Bubble(Entity owner, int itemId) {
        this.itemId = itemId;
        this.owner = owner;
    }

    public Entity getOwner() {
        return owner;
    }

    public int getItemId() {
        return itemId;
    }

    @Override
    public int hashCode() {
        return owner.hashCode() | (itemId * 31);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof Bubble)) {
            return false;
        }

        Bubble b = (Bubble) o;
        return owner.equals(b.owner) && itemId == b.itemId;
    }
}
