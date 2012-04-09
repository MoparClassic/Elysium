package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.Npc;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class NpcPositionUpdateTask implements Runnable {

    private final Iterable<Npc> npcs;

    public NpcPositionUpdateTask(Iterable<Npc> npcs) {
        this.npcs = npcs;
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
        }
    }
}
