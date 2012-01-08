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
}
