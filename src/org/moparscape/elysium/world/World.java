package org.moparscape.elysium.world;

import org.moparscape.elysium.entity.Entity;

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

    private final List<Entity> playerList = new CopyOnWriteArrayList<Entity>();

    static {
        INSTANCE = new World();
    }

    private World() {

    }

    public static World getInstance() {
        return INSTANCE;
    }

    public boolean addPlayer(Entity e) {
        return playerList.add(e);
    }

    /**
     * Are the given coords within the world boundaries
     */
    public boolean withinWorld(int x, int y) {
        return x >= 0 && x < MAX_WIDTH && y >= 0 && y < MAX_HEIGHT;
    }
}
