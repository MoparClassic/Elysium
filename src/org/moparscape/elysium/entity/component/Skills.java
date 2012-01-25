package org.moparscape.elysium.entity.component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Skills extends AbstractComponent {

    private int[] curStat = new int[18];

    private int[] maxStat = new int[18];

    private int[] exp = new int[18];

    private int combatLevel = 3;

    private int lastDamage = 0;

    private int fatigue;

    public Skills() {
        Arrays.fill(curStat, 99);
        Arrays.fill(maxStat, 99);
        Arrays.fill(exp, 14000000);
    }

    public int getLastDamage() {
        return lastDamage;
    }

    public int getHits() {
        return curStat[3];
    }

    public int getMaxHits() {
        return maxStat[3];
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getCurStat(int id) {
        return curStat[id];
    }

    public int getMaxStat(int id) {
        return maxStat[id];
    }

    public int getExp(int id) {
        return exp[id];
    }

    public int[] getCurStats() {
        return curStat;
    }

    public int[] getMaxStats() {
        return maxStat;
    }

    public int[] getExps() {
        return exp;
    }
}
