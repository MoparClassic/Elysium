package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.LogoutMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LogoutMessageHandler extends MessageHandler<LogoutMessage> {
    @Override
    public void handle(Session session, Player player, LogoutMessage message) {
        // TODO: Verify that permission to logout has been granted before removing them from the world
        // This ensures that they can't cheat by closing their client etc
        session.close();
    }
}
