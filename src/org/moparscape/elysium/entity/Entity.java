package org.moparscape.elysium.entity;

import org.moparscape.elysium.world.Point;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author lothy
 */
public class Entity implements Indexable, Locatable {

    private final AtomicInteger index = new AtomicInteger(0);
    private final AtomicReference<Point> location = new AtomicReference<Point>();

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
