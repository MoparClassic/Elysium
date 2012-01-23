package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.ChatMessage;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.UpdateProxy;
import org.moparscape.elysium.world.Region;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PlayerUpdateTask implements Runnable {

    private final Iterable<Player> players;

    private final CountDownLatch latch;

    public PlayerUpdateTask(Iterable<Player> players, CountDownLatch latch) {
        this.players = players;
        this.latch = latch;
    }

    public void run() {
        try {
            for (Player p : players) {
                UpdateProxy proxy = p.getComponent(UpdateProxy.class);

                proxy.resetMoved();
                proxy.updatePosition();
                proxy.updateAppearanceId();
            }

            for (Player p : players) {
                UpdateProxy proxy = p.getComponent(UpdateProxy.class);

                proxy.revalidateWatchedEntities();
                proxy.updateWatchedEntities();

                // Inform other players of this player's chat messages
                ChatMessage message = proxy.getNextChatMessage();
                if (message != null && p.isLoggedIn()) {
                    for (Player recipient : Region.getViewablePlayers(p, 16)) {
                        UpdateProxy recipientProxy = recipient.getComponent(UpdateProxy.class);

                        // TODO: Check for privacy settings AND pmod status to circumvent privacy settings
                        if (!recipientProxy.isFriendsWith(proxy.getUsernameHash())) {
                            continue;
                        }

                        // TODO: Check for pmod status to circumvent privacy settings
                        if (recipientProxy.isIgnoring(proxy.getUsernameHash())) {
                            continue;
                        }

                        recipientProxy.informOfChatMessage(message);
                    }
                }

                // TODO: Update trading/dueling offers here
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
