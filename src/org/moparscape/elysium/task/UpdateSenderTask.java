package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Player;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class UpdateSenderTask implements Runnable {

    private final Iterable<Player> players;

    private final CountDownLatch latch;

    public UpdateSenderTask(Iterable<Player> players, CountDownLatch latch) {
        this.players = players;
        this.latch = latch;
    }

    public void run() {
        try {
            for (Player p : players) {

            }
        } finally {
            latch.countDown();
        }
    }
}
