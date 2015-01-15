package org.moparscape.elysium.world;

import org.moparscape.elysium.entity.DefaultEntityFactory;
import org.moparscape.elysium.entity.EntityFactory;
import org.moparscape.elysium.entity.Npc;
import org.moparscape.elysium.entity.Player;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class World {

    /**
     * The maximum height of the map (944 squares per level)
     */
    public static final int MAX_HEIGHT = 3776;
    /**
     * The maximum width of the map
     */
    public static final int MAX_WIDTH = 944;
    private static final EntityFactory ENTITY_FACTORY = new DefaultEntityFactory();
    private static final World INSTANCE;
    private final CopyOnWriteArrayList<Npc> npcList = new CopyOnWriteArrayList<Npc>();
    static {
        INSTANCE = new World();
    }

    private final TileValue outsideWorld = new TileValue();
    // TODO: Don't use CopyOnWriteArrayList for the players.
    private final CopyOnWriteArrayList<Player> playerList = new CopyOnWriteArrayList<Player>();
    private final TileValue[][] tileType = new TileValue[MAX_WIDTH][MAX_HEIGHT];

    private World() {
        this.outsideWorld.mapValue = Byte.MAX_VALUE;
        this.outsideWorld.objectValue = Byte.MAX_VALUE;
    }

    public static EntityFactory getEntityFactory() {
        return ENTITY_FACTORY;
    }

    public static World getInstance() {
        return INSTANCE;
    }

    public CopyOnWriteArrayList<Npc> getNpcs() {
        return npcList;
    }

    public CopyOnWriteArrayList<Player> getPlayers() {
        return playerList;
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

    public boolean registerNpc(Npc npc) {
        return npcList.add(npc);
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

    /**
     * Are the given coords within the world boundaries
     */
    public boolean withinWorld(int x, int y) {
        return x >= 0 && x < MAX_WIDTH && y >= 0 && y < MAX_HEIGHT;
    }
}
