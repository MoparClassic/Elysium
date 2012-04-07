package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.Component;
import org.moparscape.elysium.world.Point;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lothy
 */
public class Entity implements Indexable, Locatable {

    private final Map<Class<? extends Component>, Component> components;

    private final AtomicReference<Point> location = new AtomicReference<Point>(new Point(329, 552));

    private final AtomicInteger index = new AtomicInteger(0);

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
        return index.get();
    }

    public void setIndex(int index) {
        this.index.getAndSet(index);
    }

    public Point getLocation() {
        return location.get();
    }

    public void setLocation(Point location) {
        this.location.getAndSet(location);
    }

    @Override
    public String toString() {
        return "Index: " + String.valueOf(getIndex());
    }
}
