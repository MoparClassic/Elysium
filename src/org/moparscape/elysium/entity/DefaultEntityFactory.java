package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DefaultEntityFactory implements EntityFactory {

    public Npc newNpc(NpcLoc loc) {
        Map<Class<? extends Component>, Component> components =
                new HashMap<Class<? extends Component>, Component>();

        components.put(NpcLoc.class, loc);
        components.put(Health.class, new Health());
        components.put(Movement.class, new Movement());

        for (Component c : components.values()) {
            c.resolveDependencies(components);
        }

        return new Npc(components);
    }

    public Player newPlayer() {
        Map<Class<? extends Component>, Component> components =
                new HashMap<Class<? extends Component>, Component>();

        components.put(Appearance.class, new Appearance());
        components.put(Credentials.class, new Credentials());
        components.put(Health.class, new Health());
        components.put(Movement.class, new Movement());
        components.put(Skills.class, new Skills());

        for (Component c : components.values()) {
            c.resolveDependencies(components);
        }

        return new Player(components);
    }
}
