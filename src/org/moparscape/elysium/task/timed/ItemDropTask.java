package org.moparscape.elysium.task.timed;

import org.moparscape.elysium.entity.InvItem;
import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.Inventory;
import org.moparscape.elysium.entity.component.Movement;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class ItemDropTask extends AbstractTimedTask {

    private final int expectedActionCount;
    private final Inventory inventory;
    private final Movement movement;
    private final Player owner;
    private final int slot;
    private volatile boolean finished = false;

    public ItemDropTask(Player owner, int slot, int actionCount) {
        super(0, 0);

        this.owner = owner;
        this.slot = slot;
        this.expectedActionCount = actionCount;

        this.movement = owner.getMovement();
        this.inventory = owner.getInventory();
    }

    public void run() {
        if (movement.hasMoved()) {
            return;
        }

        // If the player has started performing a new action,
        // cancel this task
        int acount = owner.getActionCount();
        if (acount != expectedActionCount) {
            finished = true;
            return;
        }

        InvItem item = inventory.remove(slot);
        if (item != null) {
            Point loc = owner.getLocation();
            Region region = Region.getRegion(loc);

            region.addItem(new Item(item.getItemId(), item.getAmount(), loc, owner));
            Packets.sendSound(owner, "dropobject");
        }

        finished = true;
        return;
    }

    public boolean shouldRepeat() {
        return !finished;
    }
}
