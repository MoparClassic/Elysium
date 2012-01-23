package org.moparscape.elysium.entity;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Projectile {

    private final Indexable caster;

    private final Indexable target;

    /**
     * The type of projectile.
     * 1) Magic projectile
     * 2) Range projectile
     */
    private final ProjectileType type;

    public Projectile(Entity caster, Entity target, ProjectileType type) {
        this.caster = caster;
        this.target = target;
        this.type = type;
    }

    public Indexable getCaster() {
        return caster;
    }

    public Indexable getTarget() {
        return target;
    }

    public int getType() {
        return type.getType();
    }

    @Override
    public int hashCode() {
        return 31 * (caster.hashCode() | target.hashCode() | type.getType());
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

    public static enum ProjectileType {

        MAGIC(1),
        RANGE(2);

        private final int type;

        ProjectileType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }
}
