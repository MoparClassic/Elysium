package org.moparscape.elysium.entity;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.def.ItemDef;
import org.moparscape.elysium.task.timed.RespawnItemTask;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Item implements Locatable, Heartbeat {

    private final int amount;
    private final int itemId;
    private final Point location;
    private final Player owner;
    private final AtomicBoolean removed = new AtomicBoolean(false);
    private final int secondsUntilRespawn;
    private final long spawned;
    private volatile boolean heartbeatCancelled = false;
    private volatile long pulseTime = 0;

    public Item(int itemId, int amount, Point loc, Player owner) {
        this(itemId, amount, loc, owner, -1);
    }

    public Item(int itemId, int amount, Point loc, Player owner, int secondsUntilRespawn) {
        this.itemId = itemId;
        this.amount = amount;
        this.location = loc;
        this.owner = owner;

        this.spawned = Server.getInstance().getHighResolutionTimestamp();
        this.secondsUntilRespawn = secondsUntilRespawn;
    }

    public int getAmount() {
        return amount;
    }

    public ItemDef getDef() {
        return DefinitionHandler.getItemDef(itemId);
    }

    public int getId() {
        return itemId;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        throw new UnsupportedOperationException("Item is immutable");
    }

    @Override
    public long getScheduledPulseTime() {
        return pulseTime;
    }

    @Override
    public void setScheduledPulseTime(long time) {
        this.pulseTime = time;
    }

    @Override
    public void pulse() {

    }

    @Override
    public boolean isCancelled() {
        return heartbeatCancelled;
    }

    @Override
    public int hashCode() {
        int ownerHash = owner != null ? owner.hashCode() : 0;
        return itemId | amount | location.hashCode() | ownerHash;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || !(o instanceof Item)) return false;

        Item i = (Item) o;
        return this.itemId == i.itemId && this.amount == i.amount &&
                this.owner == i.owner && this.location.equals(i.location);
    }

    @Override
    public String toString() {
        return "ID: " + itemId + " Amount: " + amount +
                "(" + location.getX() + ", " + location.getY() + ")";
    }

    public boolean isRemoved() {
        return removed.get();
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

    public void reinstate() {
        removed.set(false);
    }

    public boolean remove() {
        // Only the first thread to call compareAndSet will succeed.
        // Optimistic concurrency. :)
        boolean success = removed.compareAndSet(false, true);
        if (!success) return false;

        // If this stage of the method has been reached then the item
        // has been claimed by the caller.
        // Determine if it should be respawned or removed altogether
        // from the Region that it is in.
        if (shouldRespawn()) {
            Server server = Server.getInstance();
            server.submitTimedTask(new RespawnItemTask(this, secondsUntilRespawn * 1000));
        } else {
            Region r = Region.getRegion(location);
            r.removeItem(this);
        }

        return true;
    }

    public boolean shouldRespawn() {
        return secondsUntilRespawn >= 0;
    }

    private static enum StateMachine {
        REMOVED,
        VISIBLE
    }
}
