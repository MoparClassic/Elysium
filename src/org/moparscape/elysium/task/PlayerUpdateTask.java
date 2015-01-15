package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.UpdateProxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PlayerUpdateTask implements Runnable {

    private final Iterable<Player> players;

    public PlayerUpdateTask(Iterable<Player> players) {
        this.players = players;
    }

    public void run() {
        try {
            for (Player p : players) {
                UpdateProxy proxy = p.getUpdateProxy();

                proxy.resetMoved();
                proxy.updatePosition();
                proxy.updateAppearanceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
