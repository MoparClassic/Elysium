package org.moparscape.elysium.entity.component;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Combat extends AbstractComponent {

    private int combatStyle;

    public int getCombatStyle() {
        return combatStyle;
    }

    public void setCombatStyle(int style) {
        this.combatStyle = style;
    }
}
