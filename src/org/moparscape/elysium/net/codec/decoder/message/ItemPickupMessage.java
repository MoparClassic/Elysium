package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemPickupMessage extends AbstractMessage {

    private final int itemId;

    private final Point location;

    public ItemPickupMessage(int itemId, int x, int y) {
        this.itemId = itemId;
        this.location = new Point(x, y);
    }

    public int getItemId() {
        return itemId;
    }

    public Point getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Item pickup: " + itemId + " (" + location + ")";
    }
}
