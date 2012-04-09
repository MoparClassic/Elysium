package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.CommandMessage;
import org.moparscape.elysium.world.Region;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CommandMessageHandler extends MessageHandler<CommandMessage> {

    public void handle(Session session, Player player, CommandMessage message) {
        System.out.println("Command received: " + message.getCommand());
        String[] args = message.getArguments();

        if (message.hasArguments()) {
            StringBuilder sb = new StringBuilder(200);
            sb.append("Args: ");
            for (String s : args) {
                sb.append(s).append(", ");
            }
            System.out.println(sb.substring(0, sb.length() - 2));
        }

        // TODO: Execute an appropriate command handler
        if (message.getCommand().equals("item")) {
            System.out.println("Adding item to floor");
            Region r = Region.getRegion(player.getLocation());
            r.addItem(new Item(10, 2000, player.getLocation(), null));
            r.addItem(new Item(11, 1, player.getLocation(), null));
            return;
        }

        if (message.getCommand().equals("region")) {
            System.out.println(Region.getRegion(player.getLocation()));
            return;
        }
    }
}
