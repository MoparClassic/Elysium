package org.moparscape.elysium.entity;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.def.ItemDef;
import org.moparscape.elysium.task.timed.RespawnItemTask;
import org.moparscape.elysium.util.UnsafeWrapper;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;
import sun.misc.Unsafe;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Item implements Locatable {

    private static final Unsafe unsafe = UnsafeWrapper.getUnsafe();

    private static final long valueOffset;

    private final int itemId;

    private final int amount;

    private final Point location;

    private final Player owner;

    private final long spawned;

    /**
     * The respawn time, in seconds. A negative value indicates
     * that this item should not be respawned, and a zero value
     * will cause immediate respawning of this item.
     */
    private final int respawnTime;

    private volatile int removed;

    static {
        try {
            valueOffset = unsafe.objectFieldOffset(Item.class.getDeclaredField("removed"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new Error(e);
        }
    }

    public Item(int itemId, int amount, Point loc, Player owner) {
        this(itemId, amount, loc, owner, -1);
    }

    public Item(int itemId, int amount, Point loc, Player owner, int respawnTime) {
        this.itemId = itemId;
        this.amount = amount;
        this.location = loc;
        this.owner = owner;

        this.spawned = Server.getInstance().getHighResolutionTimestamp();
        this.respawnTime = respawnTime;
    }

    public int getId() {
        return itemId;
    }

    public int getAmount() {
        return amount;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        throw new UnsupportedOperationException("Item is immutable");
    }

    public boolean shouldRespawn() {
        return respawnTime >= 0;
    }

    public boolean isRemoved() {
        return removed == 1;
    }

    private final boolean compareAndSet(boolean expect, boolean update) {
        int e = expect ? 1 : 0;
        int u = update ? 1 : 0;
        return unsafe.compareAndSwapInt(this, valueOffset, e, u);
    }

    public void reinstate() {
        for (; ; ) {
            boolean current = isRemoved();
            if (compareAndSet(current, false)) {
                return; // Successfully set; break out of the method
            }
        }
    }

    public boolean remove() {
        boolean removed = isRemoved();

        // If it has already been removed, return false
        // to indicate this
        if (removed) {
            return false;
        }

        // Make a single attempt to atomically update the removed
        // status of this item from false to true.
        // If failure occurs (due to it having already been updated)
        // then the item has already been claimed; return false to
        // indicate this.
        if (!compareAndSet(removed, true)) {
            return false;
        }

        // If this stage of the method has been reached then the item
        // has been claimed by the caller.
        // Determine if it should be respawned or removed altogether
        // from the Region that it is in.
        if (shouldRespawn()) {
            Server server = Server.getInstance();
            server.submitTimedTask(new RespawnItemTask(this, respawnTime * 1000));
        } else {
            Region r = Region.getRegion(location);
            r.removeItem(this);
        }

        return true;
    }

    public ItemDef getDef() {
        return DefinitionHandler.getItemDef(itemId);
    }

    public boolean isVisibleTo(Player p) {
        if (owner == null || p.equals(owner)) {
            return true;
        }

        if (!getDef().isTradable()) {
            return false;
        }

        return Server.getInstance().getHighResolutionTimestamp() - spawned > 60000;
    }

    @Override
    public int hashCode() {
        int ownerHash = owner != null ? owner.hashCode() : 0;
        return itemId | amount | location.hashCode() | ownerHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        Item i = (Item) o;
        return this.itemId == i.itemId && this.amount == i.amount &&
                this.owner == i.owner && this.location.equals(i.location);
    }

    @Override
    public String toString() {
        return "ID: " + itemId + " Amount: " + amount +
                "(" + location.getX() + ", " + location.getY() + ")";
    }
}
