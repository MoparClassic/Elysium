package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.BankWithdrawMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BankWithdrawMessageHandler extends MessageHandler<BankWithdrawMessage> {

    public void handle(Session session, Player player, BankWithdrawMessage message) {

    }
}
