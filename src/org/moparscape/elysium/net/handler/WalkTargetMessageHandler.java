package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Path;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.Movement;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.WalkTargetMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WalkTargetMessageHandler extends MessageHandler<WalkTargetMessage> {

    @Override
    public void handle(Session session, Player player, WalkTargetMessage message) {
        player.incrementActionCount();

        Movement movement = player.getMovement();
        Path path = new Path(message);
        movement.setPath(path);
    }
}
