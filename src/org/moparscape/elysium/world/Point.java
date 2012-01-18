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
        this.x = (short) x;
        this.y = (short) y;
    }

    public final int getX() {
        return x;
    }

    public final int getY() {
        return y;
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
}
