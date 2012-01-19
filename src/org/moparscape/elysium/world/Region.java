package org.moparscape.elysium.world;

import org.moparscape.elysium.entity.GameObject;
import org.moparscape.elysium.entity.Item;
import org.moparscape.elysium.entity.Npc;
import org.moparscape.elysium.entity.Player;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Region {

    private static final int REGION_SIZE = 40;

    private static final int LOWER_BOUND = (REGION_SIZE / 2) - 1;

    private static final int HORIZONTAL_REGIONS = (World.MAX_WIDTH / REGION_SIZE) + 1;

    private static final int VERTICAL_REGIONS = (World.MAX_HEIGHT / REGION_SIZE) + 1;

    private static final Region[][] regions = new Region[HORIZONTAL_REGIONS][VERTICAL_REGIONS];

    private final Queue<GameObject> objects = new ConcurrentLinkedQueue<GameObject>();

    private final Queue<Item> items = new ConcurrentLinkedQueue<Item>();

    private final Queue<Npc> npcs = new ConcurrentLinkedQueue<Npc>();

    private final Queue<Player> players = new ConcurrentLinkedQueue<Player>();

    static {
        for (int x = 0; x < HORIZONTAL_REGIONS; x++) {
            for (int y = 0; y < VERTICAL_REGIONS; y++) {
                regions[x][y] = new Region();
            }
        }
    }

    public static Region getRegion(Point p) {
        return getRegion(p.getX(), p.getY());
    }

    private static Region getRegion(int x, int y) {
        int regionX = x / REGION_SIZE;
        int regionY = y / REGION_SIZE;

        return regions[regionX][regionY];
    }

    public static Region[] getViewableRegions(Point p) {
        return getViewableRegions(p.getX(), p.getY());
    }

    private static Region[] getViewableRegions(int x, int y) {
        Region[] neighbours = new Region[4];
        int regionX = x / REGION_SIZE;
        int regionY = y / REGION_SIZE;
        neighbours[0] = regions[regionX][regionY];

        int relX = x % REGION_SIZE;
        int relY = y % REGION_SIZE;

        if (relX <= LOWER_BOUND) {
            if (relY <= LOWER_BOUND) {
                neighbours[1] = regions[regionX - 1][regionY];
                neighbours[2] = regions[regionX - 1][regionY - 1];
                neighbours[3] = regions[regionX][regionY - 1];
            } else {
                neighbours[1] = regions[regionX - 1][regionY];
                neighbours[2] = regions[regionX - 1][regionY + 1];
                neighbours[3] = regions[regionX][regionY + 1];
            }
        } else {
            if (relY <= LOWER_BOUND) {
                neighbours[1] = regions[regionX + 1][regionY];
                neighbours[2] = regions[regionX + 1][regionY - 1];
                neighbours[3] = regions[regionX][regionY - 1];
            } else {
                neighbours[1] = regions[regionX + 1][regionY];
                neighbours[2] = regions[regionX + 1][regionY + 1];
                neighbours[3] = regions[regionX][regionY + 1];
            }
        }

        return neighbours;
    }

    public static Iterable<GameObject> getViewableObjects(Point p, int radius) {
        Region[] regions = getViewableRegions(p);
        List<GameObject> objects = new LinkedList<GameObject>();

        for (Region r : regions) {
            for (GameObject go : r.getObjects()) {
                if (!go.isRemoved() && withinRange(p, go.getLocation(), radius)) {
                    objects.add(go);
                }
            }
        }

        return objects;
    }

    public static Iterable<Item> getViewableItems(Point p, int radius) {
        Region[] regions = getViewableRegions(p);
        List<Item> items = new LinkedList<Item>();

        for (Region r : regions) {
            for (Item i : r.getItems()) {
                if (!i.isRemoved() && withinRange(p, i.getLocation(), radius)) {
                    items.add(i);
                }
            }
        }

        return items;
    }

    public static Iterable<Npc> getViewableNpcs(Point p, int radius) {
        Region[] regions = getViewableRegions(p);
        List<Npc> npcs = new LinkedList<Npc>();

        for (Region r : regions) {
            for (Npc n : r.getNpcs()) {
                if (withinRange(p, n.getLocation(), radius)) {
                    npcs.add(n);
                }
            }
        }

        return npcs;
    }

    public static Iterable<Player> getViewablePlayers(Point p, int radius) {
        Region[] regions = getViewableRegions(p);
        List<Player> players = new LinkedList<Player>();

        for (Region r : regions) {
            for (Player player : r.getPlayers()) {
                if (player.isLoggedIn() && withinRange(p, player.getLocation(), radius)) {
                    players.add(player);
                }
            }
        }

        return players;
    }

    public static Iterable<Player> getViewablePlayers(Player player, int radius) {
        Point loc = player.getLocation();
        Region[] regions = getViewableRegions(loc);
        List<Player> players = new LinkedList<Player>();

        for (Region r : regions) {
            for (Player p : r.getPlayers()) {
                if (p != player && p.isLoggedIn() && withinRange(loc, p.getLocation(), radius)) {
                    players.add(p);
                }
            }
        }

        return players;
    }

    private static boolean withinRange(Point p1, Point p2, int radius) {
        int xDiff = p1.getX() - p2.getX();
        int yDiff = p1.getY() - p2.getY();

        return xDiff <= radius && xDiff > -radius && yDiff <= radius && yDiff > -radius;
    }

    public Iterable<GameObject> getObjects() {
        return Collections.unmodifiableCollection(objects);
    }

    public Iterable<Item> getItems() {
        return Collections.unmodifiableCollection(items);
    }

    public Iterable<Npc> getNpcs() {
        return Collections.unmodifiableCollection(npcs);
    }

    public Iterable<Player> getPlayers() {
        return Collections.unmodifiableCollection(players);
    }

    public void addObject(GameObject go) {
        objects.add(go);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void addNpc(Npc npc) {
        npcs.add(npc);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removeObject(GameObject go) {
        objects.remove(go);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void removeNpc(Npc npc) {
        npcs.remove(npc);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }
}
