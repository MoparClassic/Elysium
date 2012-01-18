package org.moparscape.elysium.external;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class ItemDef {

    public String name;

    public String description;

    /**
     * The base price of the object
     */
    public int basePrice;
    /**
     * The command of the object
     */
    public String command;
    /**
     * PictureMask
     */
    public int mask;
    /**
     * Is this item a member's item?
     */
    public boolean members;
    /**
     * The sprite id
     */
    public int sprite;
    /**
     * Whether the item is stackable or not
     */
    public boolean stackable;
    /**
     * Is this item tradeable?
     */
    public boolean trade;
    /**
     * Whether the item is wieldable or not
     */
    public int wieldable;
}
