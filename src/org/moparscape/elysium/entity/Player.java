package org.moparscape.elysium.entity;

import org.moparscape.elysium.entity.component.*;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.world.Point;
import org.moparscape.elysium.world.Region;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Player extends Entity {

    private final AtomicInteger actionCount = new AtomicInteger(0);
    private final Bank bank = new Bank(this);
    private final Combat combat = new Combat();
    private final Communication communication = new Communication();
    private final Credentials credentials = new Credentials();
    private final Inventory inventory = new Inventory(this);
    private final AtomicBoolean loggedIn = new AtomicBoolean(false);
    private final AtomicReference<Region> region = new AtomicReference<Region>();
    private final Session session;
    private final Skills skills = new Skills();
    private final Sprite sprite = new Sprite(this);
    private final Movement movement = new Movement(this, sprite);
    private final Observer observer = new Observer(this, sprite);
    private final UpdateProxy updateProxy = new UpdateProxy();

    public Player(Session session) {
        this.session = session;
        this.setLocation(new Point(329, 552));
    }

    public int getActionCount() {
        return actionCount.get();
    }

    public Bank getBank() {
        return bank;
    }

    public Combat getCombat() {
        return combat;
    }

    public Communication getCommunication() {
        return communication;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Movement getMovement() {
        return movement;
    }

    public Observer getObserver() {
        return observer;
    }

    public Session getSession() {
        return session;
    }

    public Skills getSkills() {
        return skills;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public UpdateProxy getUpdateProxy() {
        return updateProxy;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

    public int incrementActionCount() {
        return actionCount.incrementAndGet();
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

    @Override
    public String toString() {
        return credentials + " (" + getLocation().getX() + ", " + getLocation().getY() + ")";
    }
}
