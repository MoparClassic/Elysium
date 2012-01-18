package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.Component;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Npc extends Entity {

    public Npc(Map<Class<? extends Component>, Component> components) {
        super(components);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "";
    }

    public boolean isRemoved() {
        return false;
    }
}
