package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.GameSettingMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameSettingMessageHandler extends MessageHandler<GameSettingMessage> {
    @Override
    public void handle(Session session, Player player, GameSettingMessage message) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
