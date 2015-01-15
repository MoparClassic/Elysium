package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.UpdateProxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class UpdatePlayerCollections implements Runnable {

    private final Iterable<Player> players;

    public UpdatePlayerCollections(Iterable<Player> players) {
        this.players = players;
    }

    public void run() {
        try {
            for (Player p : players) {
                UpdateProxy proxy = p.getUpdateProxy();

                proxy.updateEntityLists();
                proxy.clearDisplayLists();
                proxy.clearChatLists();

                proxy.resetSpriteChanged();
                proxy.setAppearanceChanged(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
