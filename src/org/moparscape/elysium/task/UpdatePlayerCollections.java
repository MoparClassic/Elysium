package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.UpdateProxy;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class UpdatePlayerCollections implements Runnable {

    private final Iterable<Player> players;

    private final CountDownLatch latch;

    public UpdatePlayerCollections(Iterable<Player> players, CountDownLatch latch) {
        this.players = players;
        this.latch = latch;
    }

    public void run() {
        try {
            for (Player p : players) {
                UpdateProxy proxy = p.getComponent(UpdateProxy.class);

                proxy.updateEntityLists();
                proxy.clearDisplayLists();

                proxy.resetSpriteChanged();
                proxy.setAppearanceChanged(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
