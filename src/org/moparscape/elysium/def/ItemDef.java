package org.moparscape.elysium.def;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class ItemDef extends EntityDef {

    private final int basePrice;
    private final String command;
    private final int mask;
    private final boolean members;
    private final int sprite;
    private final boolean stackable;
    private final boolean tradable;
    private final boolean wieldable;

    public ItemDef(String name, String description,
                   String command, int basePrice, int sprite,
                   boolean stackable, boolean wieldable,
                   int mask, boolean members, boolean tradable) {
        super(name, description);
        this.command = command;
        this.basePrice = basePrice;
        this.sprite = sprite;
        this.stackable = stackable;
        this.wieldable = wieldable;
        this.mask = mask;
        this.members = members;
        this.tradable = tradable;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public String getCommand() {
        return command;
    }

    public int getPictureMask() {
        return mask;
    }

    public int getSprite() {
        return sprite;
    }

    public boolean isMembers() {
        return members;
    }

    public boolean isStackable() {
        return stackable;
    }

    public boolean isTradable() {
        return tradable;
    }

    public boolean isWieldable() {
        return wieldable;
    }
}
