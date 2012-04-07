package org.moparscape.elysium.world;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class Point {

    protected final short x;

    protected final short y;

    public Point(int x, int y) {
        if (x < 0 || x > World.MAX_WIDTH) {
            throw new IllegalArgumentException("Invalid x ordinate: " + x);
        }

        if (y < 0 || y > World.MAX_HEIGHT) {
            throw new IllegalArgumentException("Invalid y ordinate: " + y);
        }

        this.x = (short) x;
        this.y = (short) y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
    }

    public boolean withinRange(Point target, int radius) {
        int xDiff = this.x - target.x;
        int yDiff = this.y - target.y;

        return xDiff <= radius && xDiff > -radius && yDiff <= radius && yDiff > -radius;
    }

    @Override
    public int hashCode() {
        return (x << 16) | y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof Point)) {
            return false;
        }

        Point p = (Point) o;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public String toString() {
        return "Point: " + x + " " + y;
    }
}
