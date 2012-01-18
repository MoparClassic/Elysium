package org.moparscape.elysium.external;

/**
 * The abstract class org.moparscape.elysium.external.EntityDef implements methods for return values which are
 * shared between entities.
 */
public abstract class EntityDef {

    /**
     * The description of the entity
     */
    public String description;
    /**
     * The name of the entity
     */
    public String name;

    /**
     * Returns the description of the entity
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the name of the entity
     */
    public String getName() {
        return name;
    }
}
