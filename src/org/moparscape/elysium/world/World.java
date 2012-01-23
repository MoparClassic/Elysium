package org.moparscape.elysium.world;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.external.Shop;
import org.moparscape.elysium.util.IndexableCopyOnWriteArrayList;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

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

    private final IndexableCopyOnWriteArrayList<Player> playerList = new IndexableCopyOnWriteArrayList<Player>(2000);

    private final List<GameObject> gameObjectList = new CopyOnWriteArrayList<GameObject>();

    private final List<Item> itemList = new CopyOnWriteArrayList<Item>();

    private final IndexableCopyOnWriteArrayList<Npc> npcList = new IndexableCopyOnWriteArrayList<Npc>(10000);

    private final List<Shop> shopList = new CopyOnWriteArrayList<Shop>();

    static {
        INSTANCE = new World();
    }

    private World() {

    }

    public static World getInstance() {
        return INSTANCE;
    }

    public static EntityFactory getEntityFactory() {
        return ENTITY_FACTORY;
    }

    public boolean registerGameObject(GameObject go) {
        return false;
    }

    public boolean registerItem(Item item) {
        return false;
    }

    public boolean registerNpc(Npc npc) {
        return npcList.add(npc);
    }

    public boolean registerPlayer(Player p) {
        return playerList.add(p);
    }

    public boolean registerShop(Shop shop) {
        return false;
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
}
