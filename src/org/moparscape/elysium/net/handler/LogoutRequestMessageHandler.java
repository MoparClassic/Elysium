package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.LogoutRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LogoutRequestMessageHandler extends MessageHandler<LogoutRequestMessage> {
    @Override
    public void handle(Session session, Player player, LogoutRequestMessage message) {
        // TODO: Determine if the player is allowed to logout, and respond accordingly
        if (true /* The player is allowed to log out */) {
            Packets.sendLogout(player);
        } else {
            Packets.sendCantLogout(player);
        }
    }
}
