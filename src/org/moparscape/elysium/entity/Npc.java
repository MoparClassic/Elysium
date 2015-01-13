package org.moparscape.elysium.entity;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.def.NpcDef;
import org.moparscape.elysium.entity.component.Movement;
import org.moparscape.elysium.world.Point;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Npc implements Indexable, Locatable {

    private static final Server server = Server.getInstance();

    private Movement movement;

    private NpcDef def;

    private Point location;

    private int id;

    private int appearanceId = 0;

    private boolean appearanceUpdateRequired = false;

    private int combatLevel = 0;

    private int hitpoints;

    private int index;

    private int lastDamage = 0;

    private int sprite = 1;

    private boolean spriteChanged = false;

    private long lastMoved = 0L;

    private boolean hasMoved = false;

    public int getId() {
        return id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void resetMoved() {
        this.hasMoved = false;
    }

    public void updatePosition() {
        long now = server.getHighResolutionTimestamp();
        if (now - lastMoved > 6000) {
            lastMoved = now;
            // Do movement stuff here once it can be implemented
        }
        movement.updatePosition();
    }

    public boolean isAttackable() {
        return def.isAttackable();
    }

    public int getLastDamage() {
        return lastDamage;
    }

    public int getAttack() {
        return def.getAtt();
    }

    public int getDefense() {
        return def.getDef();
    }

    public int getStrength() {
        return def.getStr();
    }

    public int getMaxHits() {
        return def.getHits();
    }

    public int getHits() {
        return hitpoints;
    }

    public void setHits(int hitpoints) {
        if (hitpoints < 0) {
            hitpoints = 0;
        }

        this.hitpoints = hitpoints;
    }

    public int getSprite() {
        return sprite;
    }

    public boolean spriteChanged() {
        return spriteChanged;
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public boolean isRemoved() {
        return false;
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
        return "";
    }
}
