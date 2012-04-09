package org.moparscape.elysium.entity.component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Bank extends Inventory {

    private static final int MAX_BANK_ITEMS = 192;

    public Bank() {
        super(MAX_BANK_ITEMS);
    }
}
