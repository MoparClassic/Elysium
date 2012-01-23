package org.moparscape.elysium.entity.component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Movement extends AbstractComponent {

    private boolean hasMoved = false;

    public Movement() {

    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void resetMoved() {
        this.hasMoved = false;
    }

    public void updatePosition() {

    }
}
