package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.NpcLoc;
import org.moparscape.elysium.net.Session;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface EntityFactory {

    Npc newNpc(NpcLoc loc);

    Player newPlayer(Session session);
}
