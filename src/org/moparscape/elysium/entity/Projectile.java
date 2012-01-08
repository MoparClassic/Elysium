package org.moparscape.elysium.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Projectile {

    private final Entity caster;

    private final Entity target;

    /**
     * The type of projectile.
     * 1) Magic projectile
     * 2) Range projectile
     */
    private final int type;

    public Projectile(Entity caster, Entity target, int type) {
        this.caster = caster;
        this.target = target;
        this.type = type;
    }

    public Entity getCaster() {
        return caster;
    }

    public Entity getTarget() {
        return target;
    }

    public int getType() {
        return type;
    }

    @Override
    public int hashCode() {
        return 31 * (caster.hashCode() | target.hashCode() | type);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof Projectile)) {
            return false;
        }

        Projectile p = (Projectile) o;
        return caster.equals(p.caster) && target.equals(p.target) && type == p.type;
    }
}
