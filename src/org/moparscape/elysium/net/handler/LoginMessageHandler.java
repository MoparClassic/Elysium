package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.DefaultEntityFactory;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.LoginMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginMessageHandler extends MessageHandler<LoginMessage> {
    @Override
    public void handle(Session session, Player player, LoginMessage message) {
        System.out.printf("Login message received! %d %d %s %s\n", message.getUid(), message.getVersion(),
                message.getUsername(), message.getPassword());

        Player p = new DefaultEntityFactory().newPlayer(session);
        session.setPlayer(p);

        // TODO: Actually load a player and such

        Packets.sendLoginResponse(p, Packets.LoginResponse.SUCCESS);
        Packets.sendLoginBox(p);
        p.setLoggedIn(true);
    }
}
