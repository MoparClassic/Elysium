package org.moparscape.elysium.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface EntityFactory {

    Entity newNpc();

    Entity newPlayer();
}
