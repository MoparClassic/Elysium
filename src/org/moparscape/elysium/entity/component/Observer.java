package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.util.StatefulEntityCollection;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

import java.util.Map;

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

    private final Player owner;

    public Observer(Player owner) {
        this.owner = owner;
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

    public void revalidateWatchedObjects() {
        for (GameObject o : watchedObjects.getKnownEntities()) {
            if (!withinRange(o) || o.isRemoved()) {
                watchedObjects.remove(o);
            }
        }
    }

    public void revalidateWatchedItems() {
        for (Item i : watchedItems.getKnownEntities()) {
            if (!withinRange(i) || i.isRemoved() || !i.isVisibleTo(owner)) {
                watchedItems.remove(i);
            }
        }
    }

    public void revalidateWatchedNpcs() {
        for (Npc n : watchedNpcs.getKnownEntities()) {
            if (!withinRange(n) || n.isRemoved()) {
                watchedNpcs.remove(n);
            }
        }
    }

    public void revalidateWatchedPlayers() {
        for (Player p : watchedPlayers.getKnownEntities()) {
            if (!withinRange(p) || !p.isLoggedIn()) {
                watchedPlayers.remove(p);
            }
        }
    }

    public void updateWatchedObjects() {
        Iterable<GameObject> objects = Region.getViewableObjects(owner.getLocation(), 21);

        for (GameObject go : objects) {
            if (!watchedObjects.contains(go)) {
                watchedObjects.add(go);
            }
        }
    }

    public void updateWatchedItems() {
        Iterable<Item> items = Region.getViewableItems(owner.getLocation(), 16);

        for (Item item : items) {
            if (!watchedItems.contains(item) && item.isVisibleTo(owner)) {
                watchedItems.add(item);
            }
        }
    }

    public void updateWatchedNpcs() {
        Iterable<Npc> npcs = Region.getViewableNpcs(owner.getLocation(), 16);

        for (Npc npc : npcs) {
            if (!watchedNpcs.contains(npc) || watchedNpcs.isRemoving(npc)) {
                watchedNpcs.add(npc);
            }
        }
    }

    public void updateWatchedPlayers() {
        Iterable<Player> players = Region.getViewablePlayers(owner, 16);

        for (Player player : players) {
            if (!watchedPlayers.contains(player) || watchedPlayers.isRemoving(player)) {
                watchedPlayers.add(player);
            }
        }
    }

    private boolean withinRange(Locatable e) {
        Point targetLoc = e.getLocation();
        Point loc = owner.getLocation();
        int xDiff = loc.getX() - targetLoc.getX();
        int yDiff = loc.getY() - targetLoc.getY();
        return xDiff <= 16 && xDiff >= -15 && yDiff <= 16 && yDiff >= -15;
    }
}
