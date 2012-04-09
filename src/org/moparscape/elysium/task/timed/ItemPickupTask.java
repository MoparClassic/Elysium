package org.moparscape.elysium.task.timed;

import org.moparscape.elysium.entity.InvItem;
import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.Inventory;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemPickupTask extends AbstractTimedTask {

    private final Player owner;

    private final Item item;

    private final int expectedActionCount;

    private volatile boolean finished = false;

    public ItemPickupTask(Player owner, Item item, int actionCount) {
        super(0, 0);

        this.owner = owner;
        this.item = item;
        this.expectedActionCount = actionCount;
    }

    public void run() {
        // If somebody has beaten this player to the item
        // (or the item has been removed for another reason)
        // then it's time to finish
        if (item.isRemoved()) {
            finished = true;
            return;
        }

        // If the player has made a new action, cancel this task
        int acount = owner.getActionCount();
        if (acount != expectedActionCount) {
            System.out.println("New action started -- stopping item pickup");
            finished = true;
            return;
        }

        Point ownerLoc = owner.getLocation();
        Point itemLoc = item.getLocation();

        // If the owner of this event is on the square that
        // has the item on it, attempt to take the item
        if (ownerLoc.equals(itemLoc)) {
            InvItem invitem = new InvItem(item.getId(), item.getAmount());
            Inventory invent = owner.getComponent(Inventory.class);

            // TODO: Run scripts here for any special cases (such as wine of zamorak)
            // These scripts should have the ability to stop the rest of this process

            // If the inventory can't hold the item due to a lack
            // of space, mark this task as finished and return
            if (!invent.canHold(invitem)) {
                finished = true;
                return;
            }

            // We've arrived at the item, and we have space in our
            // inventory - attempt to acquire it.
            // If successful, add the item. If unsuccessful due to
            // the item already being claimed, better luck next time! :)
            boolean acquired = item.remove();
            if (acquired) {
                invent.add(invitem);
                Packets.sendSound(owner, "takeobject");
            }

            finished = true;
            return;
        }
    }

    public boolean shouldRepeat() {
        return !finished;
    }
}
