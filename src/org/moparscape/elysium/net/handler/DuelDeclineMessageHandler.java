package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.DuelDeclineMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelDeclineMessageHandler extends MessageHandler<DuelDeclineMessage> {

    public void handle(Session session, Player player, DuelDeclineMessage message) {

    }
}
