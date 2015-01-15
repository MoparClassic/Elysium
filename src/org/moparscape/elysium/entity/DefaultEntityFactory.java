package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.NpcLoc;
import org.moparscape.elysium.net.Session;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DefaultEntityFactory implements EntityFactory {

    private static final DefaultEntityFactory INSTANCE = new DefaultEntityFactory();

    public static DefaultEntityFactory getInstance() {
        return INSTANCE;
    }

    public Npc newNpc(NpcLoc loc) {
//        Map<Class<? extends Component>, Component> components =
//                new HashMap<Class<? extends Component>, Component>();
//
//        components.put(NpcLoc.class, loc);
//        components.put(Health.class, new Health());
//        components.put(Movement.class, new Movement());
//
//        for (Component c : components.values()) {
//            c.resolveDependencies(components);
//        }

        return new Npc();
    }

    public Player newPlayer(Session session) {
        return new Player(session);
    }
}
