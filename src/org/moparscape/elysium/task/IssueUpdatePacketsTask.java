package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.entity.component.UpdateProxy;
import org.moparscape.elysium.net.Bitpacker;
import org.moparscape.elysium.net.PacketBuilder;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.util.DataConversions;
import org.moparscape.elysium.util.StatefulEntityCollection;
import org.moparscape.elysium.world.Point;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class IssueUpdatePacketsTask implements Runnable {

    private final Iterable<Session> sessions;

    private final CountDownLatch latch;

    public IssueUpdatePacketsTask(Iterable<Session> sessions, CountDownLatch latch) {
        this.sessions = sessions;
        this.latch = latch;
    }

    public void run() {
        try {
            for (Session s : sessions) {
                Player p = s.getPlayer();
                if (p == null) {
                    System.out.println("Null player");
                    continue;
                }
                if (!p.isLoggedIn()) {
                    System.out.println("Player not logged in");
                    continue;
                }
                UpdateProxy proxy = p.getComponent(UpdateProxy.class);

                updateTimeouts(s, p, proxy);

                updatePlayerPositions(s, p, proxy);
                updateNpcPositions(s, p, proxy);
                updateGameObjects(s, p, proxy);
                updateWallObjects(s, p, proxy);
                updateItems(s, p, proxy);

                updatePlayerAppearances(s, p, proxy);
                updateNpcAppearances(s, p, proxy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();
        }
    }

    private void updateTimeouts(Session s, Player p, UpdateProxy proxy) {

    }

    private void updateNpcPositions(Session s, Player p, UpdateProxy proxy) {
        StatefulEntityCollection<Npc> npcs = proxy.getWatchedNpcs();
        Collection<Npc> newNpcs = npcs.getNewEntities();
        Collection<Npc> knownNpcs = npcs.getKnownEntities();
        Point loc = p.getLocation();
        Bitpacker pb = new Bitpacker();

        pb.setId(77);
        pb.addBits(knownNpcs.size(), 8);
        for (Npc n : knownNpcs) {
            pb.addBits(n.getIndex(), 16);
            if (npcs.isRemoving(n)) {
                pb.addBits(1, 1);
                pb.addBits(1, 1);
                pb.addBits(12, 4);
            } else if (n.hasMoved()) {
                pb.addBits(1, 1);
                pb.addBits(0, 1);
                pb.addBits(n.getSprite(), 3);
            } else if (n.spriteChanged()) {
                pb.addBits(1, 1);
                pb.addBits(1, 1);
                pb.addBits(n.getSprite(), 4);
            } else {
                pb.addBits(0, 1);
            }
        }

        for (Npc n : newNpcs) {
            byte[] offsets = DataConversions.getMobPositionOffsets(n.getLocation(), loc);
            pb.addBits(n.getIndex(), 16);
            pb.addBits(offsets[0], 5);
            pb.addBits(offsets[1], 5);
            pb.addBits(n.getSprite(), 4);
            pb.addBits(n.getId(), 10);
        }

        s.write(pb.toPacket());
    }

    private void updateNpcAppearances(Session s, Player p, UpdateProxy proxy) {
        Queue<ChatMessage> messages = proxy.getNpcMessagesNeedingDisplayed();
        Queue<Npc> hitUpdates = proxy.getNpcHitUpdates();

        int messageCount = messages.size();
        int hitUpdateCount = hitUpdates.size();
        int updateSize = messageCount + hitUpdateCount;

        if (updateSize > 0) {
            PacketBuilder pb = new PacketBuilder();
            pb.setId(190);
            pb.writeShort(updateSize);
            for (ChatMessage cm : messages) {
                pb.writeShort(cm.getSender().getIndex());
                pb.writeByte(1);
                pb.writeShort(cm.getRecipient().getIndex());
                pb.writeByte(cm.getLength());
                pb.writeBytes(cm.getMessage());
            }
            for (Npc n : hitUpdates) {
                pb.writeShort(n.getIndex());
                pb.writeByte(2);
                pb.writeByte(n.getLastDamage());
                pb.writeByte(n.getHits());
                pb.writeByte(n.getMaxHits());
            }

            s.write(pb.toPacket());
        }
    }

    private void updateWallObjects(Session s, Player p, UpdateProxy proxy) {
        StatefulEntityCollection<GameObject> objects = proxy.getWatchedObjects();

        if (objects.changed()) {
            Collection<GameObject> newObjects = objects.getNewEntities();
            Collection<GameObject> knownObjects = objects.getKnownEntities();
            Point loc = p.getLocation();
            PacketBuilder pb = new PacketBuilder();

            pb.setId(95);
            for (GameObject o : knownObjects) {
                if (o.getType() != 1) {
                    continue;
                }

                if (objects.isRemoving(o)) {
                    byte[] offsets = DataConversions.getObjectPositionOffsets(o.getLocation(), loc);
                    pb.writeShort(60000);
                    pb.writeByte(offsets[0]);
                    pb.writeByte(offsets[1]);
                    pb.writeByte(o.getDirection());
                }
            }
            for (GameObject o : newObjects) {
                if (o.getType() != 1) {
                    continue;
                }

                byte[] offsets = DataConversions.getObjectPositionOffsets(o.getLocation(), loc);
                pb.writeShort(o.getId());
                pb.writeByte(offsets[0]);
                pb.writeByte(offsets[1]);
                pb.writeByte(o.getDirection());
            }

            s.write(pb.toPacket());
        }
    }

    private void updateGameObjects(Session s, Player p, UpdateProxy proxy) {
        StatefulEntityCollection<GameObject> objects = proxy.getWatchedObjects();

        if (objects.changed()) {
            Collection<GameObject> newObjects = objects.getNewEntities();
            Collection<GameObject> knownObjects = objects.getKnownEntities();
            Point loc = p.getLocation();
            PacketBuilder pb = new PacketBuilder();

            pb.setId(27);
            for (GameObject o : knownObjects) {
                if (o.getType() != 0) {
                    continue;
                }

                if (objects.isRemoving(o)) {
                    byte[] offsets = DataConversions.getObjectPositionOffsets(o.getLocation(), loc);
                    pb.writeShort(60000);
                    pb.writeByte(offsets[0]);
                    pb.writeByte(offsets[1]);
                    pb.writeByte(o.getDirection());
                }
            }
            for (GameObject o : newObjects) {
                if (o.getType() != 0) {
                    continue;
                }

                byte[] offsets = DataConversions.getObjectPositionOffsets(o.getLocation(), loc);
                pb.writeShort(o.getId());
                pb.writeByte(offsets[0]);
                pb.writeByte(offsets[1]);
                pb.writeByte(o.getDirection());
            }

            s.write(pb.toPacket());
        }
    }

    private void updateItems(Session s, Player p, UpdateProxy proxy) {
        StatefulEntityCollection<Item> items = proxy.getWatchedItems();

        if (items.changed()) {
            Collection<Item> newItems = items.getNewEntities();
            Collection<Item> knownItems = items.getKnownEntities();
            Point loc = p.getLocation();
            PacketBuilder pb = new PacketBuilder();

            pb.setId(109);
            for (Item i : knownItems) {
                if (items.isRemoving(i)) {
                    byte[] offsets = DataConversions.getObjectPositionOffsets(i.getLocation(), loc);
                    pb.writeShort(i.getId() + 32768);
                    pb.writeByte(offsets[0]);
                    pb.writeByte(offsets[1]);
                }
            }
            for (Item i : newItems) {
                byte[] offsets = DataConversions.getObjectPositionOffsets(i.getLocation(), loc);
                pb.writeShort(i.getId());
                pb.writeByte(offsets[0]);
                pb.writeByte(offsets[1]);
            }

            s.write(pb.toPacket());
        }
    }

    private void updatePlayerPositions(Session s, Player player, UpdateProxy proxy) {
        StatefulEntityCollection<Player> players = proxy.getWatchedPlayers();
        Collection<Player> newPlayers = players.getNewEntities();
        Collection<Player> knownPlayers = players.getKnownEntities();
        Point loc = player.getLocation();
        Bitpacker pb = new Bitpacker();

        // Set packet id to 145
        pb.setId(145);
        pb.addBits(loc.getX(), 11);
        pb.addBits(loc.getY(), 13);
        pb.addBits(proxy.getSprite(), 4);
        pb.addBits(knownPlayers.size(), 8);
        for (Player p : knownPlayers) {
            UpdateProxy targetProxy = p.getComponent(UpdateProxy.class);
            pb.addBits(p.getIndex(), 16);
            if (players.isRemoving(p)) {
                pb.addBits(1, 1);
                pb.addBits(1, 1);
                pb.addBits(12, 4);
            } else if (targetProxy.hasMoved()) {
                pb.addBits(1, 1);
                pb.addBits(0, 1);
                pb.addBits(targetProxy.getSprite(), 3);
            } else if (targetProxy.spriteChanged()) {
                pb.addBits(1, 1);
                pb.addBits(1, 1);
                pb.addBits(targetProxy.getSprite(), 4);
            } else {
                pb.addBits(0, 1);
            }
        }
        for (Player p : newPlayers) {
            UpdateProxy targetProxy = p.getComponent(UpdateProxy.class);
            byte[] offsets = DataConversions.getMobPositionOffsets(p.getLocation(), loc);
            pb.addBits(p.getIndex(), 16);
            pb.addBits(offsets[0], 5);
            pb.addBits(offsets[1], 5);
            pb.addBits(targetProxy.getSprite(), 4);
            pb.addBits(0, 1);
        }

        s.write(pb.toPacket());
    }

    private void updatePlayerAppearances(Session s, Player player, UpdateProxy proxy) {
        Queue<Bubble> bubbles = proxy.getBubblesNeedingDisplayed();
        Queue<ChatMessage> chatMessages = proxy.getChatMessagesNeedingDisplayed();
        Queue<Player> playerHitUpdates = proxy.getPlayerHitUpdates();

        Queue<Projectile> projectiles = proxy.getProjectilesNeedingDisplayed();
        List<Player> playerAppearanceUpdates = proxy.getPlayerAppearanceUpdates();


        int updateSize = bubbles.size() + chatMessages.size() + playerHitUpdates.size() +
                projectiles.size() + playerAppearanceUpdates.size();

        if (updateSize > 0) {
            PacketBuilder pb = new PacketBuilder();
            pb.setId(53);
            pb.writeShort(updateSize);

            for (Bubble b : bubbles) {
                pb.writeShort(b.getOwner().getIndex());
                pb.writeByte(0);
                pb.writeShort(b.getItemId());
            }

            for (ChatMessage cm : chatMessages) {
                pb.writeShort(cm.getSender().getIndex());
                pb.writeByte(cm.getRecipient() == null ? 1 : 6);
                pb.writeByte(cm.getLength());
                pb.writeBytes(cm.getMessage());
            }

            for (Player p : playerHitUpdates) {
                UpdateProxy targetProxy = p.getComponent(UpdateProxy.class);
                pb.writeShort(p.getIndex());
                pb.writeByte(2);
                pb.writeByte(targetProxy.getLastDamage());
                pb.writeByte(targetProxy.getHits());
                pb.writeByte(targetProxy.getMaxHits());
            }

            for (Projectile p : projectiles) {
                Indexable victim = p.getTarget();
                pb.writeShort(p.getCaster().getIndex());
                pb.writeByte(victim instanceof Npc ? 3 : 4); // 3: Npc -- 4: Player
                pb.writeShort(p.getType());
                pb.writeShort(victim.getIndex());
            }

            for (Player p : playerAppearanceUpdates) {
                UpdateProxy targetProxy = p.getComponent(UpdateProxy.class);
                pb.writeShort(p.getIndex());
                pb.writeByte(5);
                pb.writeShort(targetProxy.getAppearanceId());
                pb.writeLong(targetProxy.getUsernameHash());

                int[] wornItems = targetProxy.getWornItems();
                pb.writeByte(wornItems.length);
                for (int i : wornItems) {
                    pb.writeByte(i);
                }

                pb.writeByte(targetProxy.getHairColour());
                pb.writeByte(targetProxy.getTopColour());
                pb.writeByte(targetProxy.getTrouserColour());
                pb.writeByte(targetProxy.getSkinColour());
                pb.writeByte(targetProxy.getCombatLevel());
                pb.writeByte(targetProxy.isSkulled() ? 1 : 0);
                pb.writeByte(0); // 3: Admin 2: Mod 1; Pmod 0: None
            }

            s.write(pb.toPacket());
        }
    }
}
