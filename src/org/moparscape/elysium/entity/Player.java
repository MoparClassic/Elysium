package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.Component;
import org.moparscape.elysium.net.Session;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Player extends Entity {

    private final Session session;

    private AtomicBoolean loggedIn = new AtomicBoolean(false);

    public Player(Session session, Map<Class<? extends Component>, Component> components) {
        super(components);
        this.session = session;
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
    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public String toString() {
        return "Player";
    }
}
