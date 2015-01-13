package org.moparscape.elysium.entity;

import org.moparscape.elysium.def.ItemDef;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class InvItem {

    private final int id;

    private final AtomicInteger amount = new AtomicInteger();

    private final AtomicBoolean wielded = new AtomicBoolean(false);

    public InvItem(int itemId, int amount) {
        this.id = itemId;
        this.amount.getAndSet(amount);
    }

    public int getItemId() {
        return id;
    }

    public int getAmount() {
        return amount.get();
    }

    public void setAmount(int amount) {
        this.amount.getAndSet(amount);
    }

    public boolean isWielded() {
        return wielded.get();
    }

    public void setWielded(boolean wield) {
        wielded.getAndSet(wield);
    }

    public ItemDef getDef() {
        return DefinitionHandler.getItemDef(id);
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null) {
            return false;
        }

        InvItem i = (InvItem) o;
        return this.id == i.id;
    }

    @Override
    public String toString() {
        return "ID: " + id + " Amount: " + amount;
    }
}
