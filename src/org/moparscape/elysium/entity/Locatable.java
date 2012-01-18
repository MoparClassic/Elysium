package org.moparscape.elysium.entity;

import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface Locatable {

    Point getLocation();

    void setLocation(Point location);
}
