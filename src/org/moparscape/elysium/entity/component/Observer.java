package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.util.StatefulEntityCollection;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Observer extends AbstractComponent {

    private final StatefulEntityCollection<GameObject> watchedObjects = new StatefulEntityCollection<GameObject>();

    private final StatefulEntityCollection<Item> watchedItems = new StatefulEntityCollection<Item>();

    private final StatefulEntityCollection<Npc> watchedNpcs = new StatefulEntityCollection<Npc>();

    private final StatefulEntityCollection<Player> watchedPlayers = new StatefulEntityCollection<Player>();

    private final Queue<Projectile> projectiles = new LinkedBlockingQueue<Projectile>();

    private final Queue<Player> playerAppearanceUpdates = new LinkedBlockingQueue<Player>();

    private final Queue<Player> playerHitUpdates = new LinkedBlockingQueue<Player>();

    private final Queue<Npc> npcHitUpdates = new LinkedBlockingQueue<Npc>();

    private final Queue<Bubble> bubbles = new LinkedBlockingQueue<Bubble>();

    private Player owner;

    public void setOwner(Player player) {
        if (owner != null) {
            throw new IllegalStateException("Observer's player is already set");
        }
        this.owner = player;
    }

    @Override
    public void resolveDependencies(Map<Class<? extends Component>, Component> components) {

    }

    public StatefulEntityCollection<GameObject> getWatchedObjects() {
        return watchedObjects;
    }

    public StatefulEntityCollection<Item> getWatchedItems() {
        return watchedItems;
    }

    public StatefulEntityCollection<Npc> getWatchedNpcs() {
        return watchedNpcs;
    }

    public StatefulEntityCollection<Player> getWatchedPlayers() {
        return watchedPlayers;
    }

    public Queue<Projectile> getProjectilesNeedingDisplayed() {
        return projectiles;
    }

    public Queue<Bubble> getBubblesNeedingDisplayed() {
        return bubbles;
    }

    public Queue<Npc> getNpcHitUpdates() {
        return npcHitUpdates;
    }

    public Queue<Player> getPlayerHitUpdates() {
        return playerHitUpdates;
    }

    public Queue<Player> getPlayerAppearanceUpdates() {
        return playerAppearanceUpdates;
    }

    public void revalidateWatchedEntities() {
        revalidateWatchedPlayers();
        revalidateWatchedObjects();
        revalidateWatchedItems();
        revalidateWatchedNpcs();
    }

    public void updateWatchedEntities() {
        updateWatchedPlayers();
        updateWatchedObjects();
        updateWatchedItems();
        updateWatchedNpcs();
    }

    public void updateEntityLists() {
        watchedPlayers.update();
        watchedObjects.update();
        watchedItems.update();
        watchedNpcs.update();
    }

    public void clearDisplayLists() {
        projectiles.clear();
        playerHitUpdates.clear();
        npcHitUpdates.clear();
        bubbles.clear();
    }

    private void revalidateWatchedObjects() {
        Point loc = owner.getLocation();
        for (GameObject o : watchedObjects.getKnownEntities()) {
            if (!loc.withinRange(o.getLocation(), 21) || o.isRemoved()) {
                watchedObjects.remove(o);
            }
        }
    }

    private void revalidateWatchedItems() {
        Point loc = owner.getLocation();
        for (Item i : watchedItems.getKnownEntities()) {
            if (!loc.withinRange(i.getLocation(), 16) || i.isRemoved() || !i.isVisibleTo(owner)) {
                watchedItems.remove(i);
            }
        }
    }

    private void revalidateWatchedNpcs() {
        Point loc = owner.getLocation();
        for (Npc n : watchedNpcs.getKnownEntities()) {
            if (!loc.withinRange(n.getLocation(), 16) || n.isRemoved()) {
                watchedNpcs.remove(n);
            }
        }
    }

    private void revalidateWatchedPlayers() {
        Point loc = owner.getLocation();
        for (Player p : watchedPlayers.getKnownEntities()) {
            if (!loc.withinRange(p.getLocation(), 16) || !p.isLoggedIn()) {
                watchedPlayers.remove(p);
            }
        }
    }

    private void updateWatchedObjects() {
        Iterable<GameObject> objects = Region.getViewableObjects(owner.getLocation(), 21);

        for (GameObject go : objects) {
            if (!watchedObjects.contains(go)) {
                watchedObjects.add(go);
            }
        }
    }

    private void updateWatchedItems() {
        Iterable<Item> items = Region.getViewableItems(owner.getLocation(), 16);

        for (Item item : items) {
            if (!watchedItems.contains(item) && item.isVisibleTo(owner)) {
                watchedItems.add(item);
            }
        }
    }

    private void updateWatchedNpcs() {
        Iterable<Npc> npcs = Region.getViewableNpcs(owner.getLocation(), 16);

        for (Npc npc : npcs) {
            if (!watchedNpcs.contains(npc) || watchedNpcs.isRemoving(npc)) {
                watchedNpcs.add(npc);
            }
        }
    }

    private void updateWatchedPlayers() {
        Iterable<Player> players = Region.getViewablePlayers(owner, 16);

        for (Player player : players) {
            if (!watchedPlayers.contains(player) || watchedPlayers.isRemoving(player)) {
                watchedPlayers.add(player);
            }
        }
    }
}
