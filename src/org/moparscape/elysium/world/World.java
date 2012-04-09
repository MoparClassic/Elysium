package org.moparscape.elysium.world;

import org.moparscape.elysium.entity.DefaultEntityFactory;
import org.moparscape.elysium.entity.EntityFactory;
import org.moparscape.elysium.entity.Npc;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.util.IndexableCopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class World {

    private static final World INSTANCE;

    /**
     * The maximum width of the map
     */
    public static final int MAX_WIDTH = 944;
    /**
     * The maximum height of the map (944 squares per level)
     */
    public static final int MAX_HEIGHT = 3776;

    private static final EntityFactory ENTITY_FACTORY = new DefaultEntityFactory();

    private final TileValue[][] tileType = new TileValue[MAX_WIDTH][MAX_HEIGHT];

    private final TileValue outsideWorld = new TileValue();

    private final IndexableCopyOnWriteArrayList<Player> playerList = new IndexableCopyOnWriteArrayList<Player>(2000);

    private final IndexableCopyOnWriteArrayList<Npc> npcList = new IndexableCopyOnWriteArrayList<Npc>(10000);

    static {
        INSTANCE = new World();
    }

    private World() {
        this.outsideWorld.mapValue = Byte.MAX_VALUE;
        this.outsideWorld.objectValue = Byte.MAX_VALUE;
    }

    public static World getInstance() {
        return INSTANCE;
    }

    public static EntityFactory getEntityFactory() {
        return ENTITY_FACTORY;
    }

    public boolean registerPlayer(Player p) {
        return playerList.add(p);
    }

    public boolean unregisterPlayer(Player p) {
        if (playerList.remove(p)) {
            Region r = Region.getRegion(p.getLocation());
            r.removePlayer(p);
            return true;
        }

        return false;
    }

    public boolean registerNpc(Npc npc) {
        return npcList.add(npc);
    }

    public IndexableCopyOnWriteArrayList<Npc> getNpcs() {
        return npcList;
    }

    public IndexableCopyOnWriteArrayList<Player> getPlayers() {
        return playerList;
    }

    /**
     * Are the given coords within the world boundaries
     */
    public boolean withinWorld(int x, int y) {
        return x >= 0 && x < MAX_WIDTH && y >= 0 && y < MAX_HEIGHT;
    }

    /**
     * Gets the tile value as point x, y
     */
    public TileValue getTileValue(int x, int y) {
        if (!withinWorld(x, y)) {
            return outsideWorld;
        }
        TileValue t = tileType[x][y];
        if (t == null) {
            t = new TileValue();
            tileType[x][y] = t;
        }
        return t;
    }
}
