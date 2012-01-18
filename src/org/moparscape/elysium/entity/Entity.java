package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.Component;
import org.moparscape.elysium.world.Point;

import java.util.Collections;
import java.util.Map;

/**
 * @author lothy
 */
public class Entity implements Indexable, Locatable {

    private final Map<Class<? extends Component>, Component> components;

    private Point location;

    private int index;

    private Entity() {
        throw new RuntimeException("Default constructor not supported.");
    }

    public Entity(Map<Class<? extends Component>, Component> components) {
        this.components = Collections.unmodifiableMap(components);
    }

    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Index: " + String.valueOf(index);
    }
}
