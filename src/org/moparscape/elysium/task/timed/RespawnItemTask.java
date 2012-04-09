package org.moparscape.elysium.task.timed;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.entity.Item;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class RespawnItemTask extends AbstractTimedTask {

    private final Item item;

    public RespawnItemTask(Item item, long respawnTime) {
        super(Server.getInstance().getHighResolutionTimestamp() + respawnTime, 0);

        this.item = item;
    }

    public void run() {
        item.reinstate();
    }
}
