package org.moparscape.elysium;

import org.moparscape.elysium.entity.Npc;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.task.*;
import org.moparscape.elysium.world.World;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameStateUpdater {

    private final CopyOnWriteArrayList<Npc> npcList = World.getInstance().getNpcs();

    private final CopyOnWriteArrayList<Player> playerList = World.getInstance().getPlayers();

    public void updateState() throws Exception {
        updateNpcPositions();
        updatePlayers();
    }

    private void updateNpcPositions() throws Exception {
        Server server = Server.getInstance();

        // TODO: Improve concurrency here. Currently it's sequential.
        CountDownLatch latch = new CountDownLatch(1);
        server.submitTask(new CountdownTaskExecutor(new NpcPositionUpdateTask(npcList), latch));

        // Block until all of the npc position updating is finished
        latch.await();

//        CountDownLatch latch = new CountDownLatch(npcPartitions.size());
//        Server server = Server.getInstance();
//
//        for (Iterable<Npc> n : npcPartitions) {
//            server.submitTask(new CountdownTaskExecutor(new NpcPositionUpdateTask(n), latch));
//        }
//
//        // Block until all of the npc position updating is finished
//        latch.await();
    }

    private void updatePlayers() throws Exception {
        Server server = Server.getInstance();

        // TODO: Improve concurrency for all of these.
        final CountDownLatch playerUpdateLatch = new CountDownLatch(1);
        server.submitTask(new CountdownTaskExecutor(new PlayerUpdateTask(playerList), playerUpdateLatch));
        playerUpdateLatch.await();

        final CountDownLatch revalidateWatchedEntitiesLatch = new CountDownLatch(1);
        server.submitTask(new CountdownTaskExecutor(
                new RevalidateWatchedEntitiesTask(playerList), revalidateWatchedEntitiesLatch));
        revalidateWatchedEntitiesLatch.await();

        final CountDownLatch issueMessagesLatch = new CountDownLatch(1);
        server.submitTask(new CountdownTaskExecutor(new IssueMessagesTask(playerList), issueMessagesLatch));
        issueMessagesLatch.await();

//        for (Iterable<Player> p : playerPartitions) {
//            server.submitTask(new CountdownTaskExecutor(new PlayerUpdateTask(p), latch));
//        }
//
//        // Block until all of the player updating is finished
//        latch.await();
//
//        latch = new CountDownLatch(playerPartitions.size());
//        for (Iterable<Player> p : playerPartitions) {
//            server.submitTask(new CountdownTaskExecutor(new RevalidateWatchedEntitiesTask(p), latch));
//        }
//        latch.await();
//
//        latch = new CountDownLatch(playerPartitions.size());
//        for (Iterable<Player> p : playerPartitions) {
//            server.submitTask(new CountdownTaskExecutor(new IssueMessagesTask(p), latch));
//        }
//        latch.await();
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

        // TODO: Improve concurrency here.
        // Update the player collections, and clear their display lists
        final CountDownLatch updatePlayerCollectionsLatch = new CountDownLatch(1);
        server.submitTask(new CountdownTaskExecutor(
                new UpdatePlayerCollections(playerList), updatePlayerCollectionsLatch));
        updatePlayerCollectionsLatch.await();

//        latch = new CountDownLatch(playerPartitions.size());
//        for (Iterable<Player> p : playerPartitions) {
//            server.submitTask(new CountdownTaskExecutor(new UpdatePlayerCollections(p), latch));
//        }
//        latch.await();

        // TODO: Reset the npcs
//        latch = new CountDownLatch(npcPartitions.size());
//        for (Iterable<Npc> n : npcPartitions) {
//
//        }
//        latch.await();
    }
}
