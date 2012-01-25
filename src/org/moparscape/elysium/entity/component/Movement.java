package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Locatable;
import org.moparscape.elysium.entity.Path;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.TileValue;
import org.moparscape.elysium.world.World;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Movement extends AbstractComponent {

    private static final World world = World.getInstance();

    private boolean hasMoved = false;

    private Locatable owner;

    private volatile Path path;

    private volatile int curWaypoint;

    public Movement() {

    }

    public void setOwner(Locatable owner) {
        this.owner = owner;
    }

    public void setPath(Path path) {
        this.curWaypoint = -1;
        this.path = path;
    }

    /**
     * Updates the point in the path to the next one
     * assuming we are not finished
     */
    public void updatePosition() {
        if (!finishedPath()) {
            setNextPosition();
        }
    }

    /**
     * Resets the path (stops movement)
     */
    protected void resetPath() {
        path = null;
        curWaypoint = -1;
    }

    /**
     * Updates our position to the next in the path
     */
    protected void setNextPosition() {
        Point loc = owner.getLocation();
        int[] newCoords = {-1, -1};
        if (curWaypoint == -1) {
            if (atStart()) {
                curWaypoint = 0;
            } else {
                newCoords = getNextCoords(loc.getX(), path.getStartX(), loc.getY(), path.getStartY());
            }
        }
        if (curWaypoint > -1) {
            if (atWaypoint(curWaypoint)) {
                curWaypoint++;
            }
            if (curWaypoint < path.getPathLength()) {
                newCoords = getNextCoords(loc.getX(), path.getWaypointX(curWaypoint), loc.getY(), path.getWaypointY(curWaypoint));
            } else {
                resetPath();
            }
        }
        if (newCoords[0] > -1 && newCoords[1] > -1) {
            owner.setLocation(new Point(newCoords[0], newCoords[1]));
        }
    }

    private boolean isBlocking(int x, int y, int bit) {
        TileValue t = world.getTileValue(x, y);
        return isBlocking(t.mapValue, (byte) bit) || isBlocking(t.objectValue, (byte) bit);
    }

    private boolean isBlocking(byte val, byte bit) {
        // 0x70, or 112, is 16 OR 32 OR 64. If ANDing this mask with the val != 0 then the tile is blocked
        int mask = 0x70 | bit;
        return (val & mask) != 0;
    }

    /**
     * Gets the next coordinate in the right direction
     */
    protected int[] getNextCoords(int startX, int destX, int startY, int destY) {
        int[] coords = {startX, startY};
        boolean myXBlocked = false, myYBlocked = false, newXBlocked = false, newYBlocked = false;
        if (startX > destX) {
            myXBlocked = isBlocking(startX - 1, startY, 8); // Check right tiles left wall
            coords[0] = startX - 1;
        } else if (startX < destX) {
            myXBlocked = isBlocking(startX + 1, startY, 2); // Check left tiles right wall
            coords[0] = startX + 1;
        }

        if (startY > destY) {
            myYBlocked = isBlocking(startX, startY - 1, 4); // Check top tiles bottom wall
            coords[1] = startY - 1;
        } else if (startY < destY) {
            myYBlocked = isBlocking(startX, startY + 1, 1); // Check bottom tiles top wall
            coords[1] = startY + 1;
        }

        // If both directions are blocked OR we are going straight and the direction is blocked
        if ((myXBlocked && myYBlocked) || (myXBlocked && startY == destY) || (myYBlocked && startX == destX)) {
            return cancelCoords();
        }

        if (coords[0] > startX) {
            newXBlocked = isBlocking(coords[0], coords[1], 2); // Check dest tiles right wall
        } else if (coords[0] < startX) {
            newXBlocked = isBlocking(coords[0], coords[1], 8); // Check dest tiles left wall
        }

        if (coords[1] > startY) {
            newYBlocked = isBlocking(coords[0], coords[1], 1); // Check dest tiles top wall
        } else if (coords[1] < startY) {
            newYBlocked = isBlocking(coords[0], coords[1], 4); // Check dest tiles bottom wall
        }

        // If both directions are blocked OR we are going straight and the direction is blocked
        if ((newXBlocked && newYBlocked) || (newXBlocked && startY == coords[1]) || (myYBlocked && startX == coords[0])) {
            return cancelCoords();
        }

        // If only one direction is blocked, but it blocks both tiles
        if ((myXBlocked && newXBlocked) || (myYBlocked && newYBlocked)) {
            return cancelCoords();
        }

        return coords;
    }

    private int[] cancelCoords() {
        resetPath();
        return new int[]{-1, -1};
    }

    /**
     * Checks if we have reached the end of our path
     */
    public boolean finishedPath() {
        if (path == null) {
            return true;
        }
        if (path.getPathLength() > 0) {
            return atWaypoint(path.getPathLength() - 1);
        } else {
            return atStart();
        }
    }

    protected boolean atStart() {
        Point loc = owner.getLocation();
        return loc.getX() == path.getStartX() && loc.getY() == path.getStartY();
    }

    /**
     * Checks if we are at the given waypoint
     */
    protected boolean atWaypoint(int waypoint) {
        Point loc = owner.getLocation();
        return path.getWaypointX(waypoint) == loc.getX() && path.getWaypointY(waypoint) == loc.getY();
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void resetMoved() {
        this.hasMoved = false;
    }
}
