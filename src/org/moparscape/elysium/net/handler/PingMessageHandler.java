package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.PingMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PingMessageHandler extends MessageHandler<PingMessage> {
    @Override
    public void handle(Session session, Player player, PingMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
