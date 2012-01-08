package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.DummyMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DummyMessageHandler extends MessageHandler<DummyMessage> {
    @Override
    public void handle(Session session, Player player, DummyMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
