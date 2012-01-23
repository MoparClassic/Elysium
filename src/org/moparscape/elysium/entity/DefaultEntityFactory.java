package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.*;
import org.moparscape.elysium.net.Session;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DefaultEntityFactory implements EntityFactory {

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
        Map<Class<? extends Component>, Component> components =
                new HashMap<Class<? extends Component>, Component>(30, 0.4f);

        components.put(Appearance.class, new Appearance());
        components.put(Combat.class, new Combat());
        components.put(Communication.class, new Communication());
        components.put(Credentials.class, new Credentials());
        components.put(Movement.class, new Movement());
        components.put(Skills.class, new Skills());
        components.put(UpdateProxy.class, new UpdateProxy());

        Observer observer = new Observer();
        components.put(Observer.class, observer);

        for (Component c : components.values()) {
            c.resolveDependencies(components);
        }

        Player player = new Player(session, components);
        observer.setOwner(player);

        return player;
    }
}
