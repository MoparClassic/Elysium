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
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
