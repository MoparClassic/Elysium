package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.ItemPickupMessage;
import org.moparscape.elysium.task.timed.ItemPickupTask;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemPickupMessageHandler extends MessageHandler<ItemPickupMessage> {

    @Override
    public void handle(Session session, Player player, ItemPickupMessage message) {
        Point itemLoc = message.getLocation();
        Region r = Region.getRegion(itemLoc);
        Item targetItem = r.getItem(message.getItemId(), itemLoc);

        if (targetItem != null) {
            int actionCount = player.incrementActionCount();
            Server.getInstance().submitTimedTask(new ItemPickupTask(player, targetItem, actionCount));
        }
    }
}
