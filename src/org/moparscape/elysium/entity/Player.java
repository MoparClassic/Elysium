package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.Component;
import org.moparscape.elysium.entity.component.Credentials;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Player extends Entity {

    private final Session session;

    private final AtomicBoolean loggedIn = new AtomicBoolean(false);

    private final AtomicReference<Region> region = new AtomicReference<Region>();

    private final AtomicInteger actionCount = new AtomicInteger(0);

    public Player(Session session, Map<Class<? extends Component>, Component> components) {
        super(components);
        this.session = session;
        this.setLocation(new Point(329, 552));
    }

    public Session getSession() {
        return session;
    }

    public boolean isLoggedIn() {
        return loggedIn.get();
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn.getAndSet(loggedIn);
    }

    @Override
    public void setLocation(Point location) {
        Region r = Region.getRegion(location);
        Region cur = region.get();

        if (cur != r) {
            if (cur != null) {
                cur.removePlayer(this);
            }

            r.addPlayer(this);
            region.getAndSet(r);
        }

        super.setLocation(location);
    }

    public int incrementActionCount() {
        return actionCount.incrementAndGet();
    }

    public int getActionCount() {
        return actionCount.get();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    @Override
    public String toString() {
        Credentials c = getComponent(Credentials.class);
        return c + " (" + getLocation().getX() + ", " + getLocation().getY() + ")";
    }
}
