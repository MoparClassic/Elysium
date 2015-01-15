package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Path;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.Movement;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.WalkMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WalkMessageHandler extends MessageHandler<WalkMessage> {

    @Override
    public void handle(Session session, Player player, WalkMessage message) {
        player.incrementActionCount();

        Movement movement = player.getMovement();
        Path path = new Path(message);
        movement.setPath(path);
    }
}
