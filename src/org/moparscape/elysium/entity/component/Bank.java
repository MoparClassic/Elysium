package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.Player;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Bank extends Inventory {

    private static final int MAX_BANK_ITEMS = 192;

    public Bank(Player owner) {
        super(owner, MAX_BANK_ITEMS);
    }
}
