package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.PublicChatMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PublicChatMessageHandler extends MessageHandler<PublicChatMessage> {
    @Override
    public void handle(Session session, Player player, PublicChatMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
