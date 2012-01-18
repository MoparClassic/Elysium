package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Locatable;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Movement extends AbstractComponent implements Locatable {

    private Point location;

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }
}
