package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Npc;

import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class NpcPositionUpdateTask implements Runnable {

    private final Iterable<Npc> npcs;

    private final CountDownLatch latch;

    public NpcPositionUpdateTask(Iterable<Npc> npcs, CountDownLatch latch) {
        this.npcs = npcs;
        this.latch = latch;
    }

    public void run() {
        try {
            for (Npc n : npcs) {
                n.resetMoved();
                n.updatePosition();
                // TODO: Is updating the npc's appearance id necessary here?
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }
}
