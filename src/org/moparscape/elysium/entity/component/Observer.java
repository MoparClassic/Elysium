package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.util.StatefulEntityCollection;
import org.moparscape.elysium.world.Point;

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

    private final Entity owner;

    public Observer(Entity owner) {
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

    }

    public void updateWatchedItems() {

    }

    public void updateWatchedNpcs() {

    }

    public void updateWatchedPlayers() {

    }

    private boolean withinRange(Locatable e) {
        Point targetLoc = e.getLocation();
        Point loc = owner.getLocation();
        int xDiff = loc.getX() - targetLoc.getX();
        int yDiff = loc.getY() - targetLoc.getY();
        return xDiff <= 16 && xDiff >= -15 && yDiff <= 16 && yDiff >= -15;
    }
}
