package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.LoginMessage;
import org.moparscape.elysium.net.codec.encoder.message.LoginBoxMessage;
import org.moparscape.elysium.net.codec.encoder.message.LoginResponseMessage;

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

        // TODO: Actually load a player and such
        session.write(new LoginBoxMessage(null));
        session.write(new LoginResponseMessage(LoginResponseMessage.LoginResponse.SUCCESS));
    }
}
