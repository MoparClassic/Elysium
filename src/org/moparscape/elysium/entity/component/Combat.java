package org.moparscape.elysium.entity.component;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Combat extends AbstractComponent {

    private AtomicInteger combatStyle = new AtomicInteger(0);

    public int getCombatStyle() {
        return combatStyle.get();
    }

    public void setCombatStyle(int style) {
        this.combatStyle.getAndSet(style);
    }
}
