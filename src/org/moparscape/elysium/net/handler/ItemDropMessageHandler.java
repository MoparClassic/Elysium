package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.ItemDropMessage;
import org.moparscape.elysium.task.timed.ItemDropTask;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ItemDropMessageHandler extends MessageHandler<ItemDropMessage> {

    @Override
    public void handle(Session session, Player player, ItemDropMessage message) {
        int actionCount = player.incrementActionCount();
        Server.getInstance().submitTimedTask(new ItemDropTask(player, message.getIndex(), actionCount));
    }
}
