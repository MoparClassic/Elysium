package org.moparscape.elysium.net.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.SessionRequestMessage;
import org.moparscape.elysium.util.Formulae;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionRequestMessageHandler extends MessageHandler<SessionRequestMessage> {
    @Override
    public void handle(Session session, Player player, SessionRequestMessage message) {
        System.out.printf("SessionRequest: %s %d\n", message.getClassName(), message.getUserByte());

        long serverKey = Formulae.generateSessionKey(message.getUserByte());
        ByteBuf buffer = Unpooled.buffer(8);
        buffer.writeLong(serverKey);
        session.write(buffer);
    }
}
