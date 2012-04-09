package org.moparscape.elysium.util;

import org.moparscape.elysium.entity.InvItem;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class ItemByValueComparator implements Comparator<InvItem> {

    public int compare(InvItem one, InvItem two) {
        return one.getDef().getBasePrice() - two.getDef().getBasePrice();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        return false;
    }
}
