package org.moparscape.elysium;

import org.moparscape.elysium.entity.Npc;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.task.NpcPositionUpdateTask;
import org.moparscape.elysium.task.PlayerUpdateTask;
import org.moparscape.elysium.task.UpdatePlayerCollections;
import org.moparscape.elysium.util.IndexableCopyOnWriteArrayList;
import org.moparscape.elysium.world.World;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameStateUpdater {

    private final World world = World.getInstance();

    private final IndexableCopyOnWriteArrayList<Player> playerList = world.getPlayers();

    private final IndexableCopyOnWriteArrayList<Npc> npcList = world.getNpcs();

    private List<Iterable<Player>> playerPartitions;

    private List<Iterable<Npc>> npcPartitions;

    public void updateState() throws Exception {
        playerPartitions = playerList.divide(Server.TASK_THREADS);
        npcPartitions = npcList.divide(Server.TASK_THREADS);

        updateNpcPositions();
        updatePlayers();
    }

    private void updateNpcPositions() throws Exception {
        CountDownLatch latch = new CountDownLatch(npcPartitions.size());
        Server server = Server.getInstance();

        for (Iterable<Npc> n : npcPartitions) {
            server.submitTask(new NpcPositionUpdateTask(n, latch));
        }

        // Block until all of the npc position updating is finished
        latch.await();
    }

    private void updatePlayers() throws Exception {
        CountDownLatch latch = new CountDownLatch(playerPartitions.size());
        Server server = Server.getInstance();

        for (Iterable<Player> p : playerPartitions) {
            server.submitTask(new PlayerUpdateTask(p, latch));
        }

        // Block until all of the player updating is finished
        latch.await();
    }

    public void updateCollections() throws Exception {
        Server server = Server.getInstance();
        CountDownLatch latch;

        // TODO: Unregister any players that have logged out
//        latch = new CountDownLatch(playerPartitions.size());
//        for (Iterable<Player> p : playerPartitions) {
//
//        }
//        latch.await();

        // Update the player collections, and clear their display lists
        latch = new CountDownLatch(playerPartitions.size());
        for (Iterable<Player> p : playerPartitions) {
            server.submitTask(new UpdatePlayerCollections(p, latch));
        }
        latch.await();

        // TODO: Reset the npcs
//        latch = new CountDownLatch(npcPartitions.size());
//        for (Iterable<Npc> n : npcPartitions) {
//
//        }
//        latch.await();
    }
}
