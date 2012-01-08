package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.BankDepositMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BankDepositMessageHandler extends MessageHandler<BankDepositMessage> {

    public void handle(Session session, Player player, BankDepositMessage message) {

    }
}
