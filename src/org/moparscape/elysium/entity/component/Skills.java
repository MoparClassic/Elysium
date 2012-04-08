package org.moparscape.elysium.entity.component;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Skills extends AbstractComponent {

    private static final int SKILL_COUNT = 18;

    private AtomicIntegerArray curStat = new AtomicIntegerArray(SKILL_COUNT);

    private AtomicIntegerArray maxStat = new AtomicIntegerArray(SKILL_COUNT);

    private AtomicIntegerArray exp = new AtomicIntegerArray(SKILL_COUNT);

    private int combatLevel = 3;

    private int lastDamage = 0;

    private int fatigue;

    public Skills() {
        for (int i = 0; i < SKILL_COUNT; i++) {
            curStat.getAndSet(i, 99);
            maxStat.getAndSet(i, 99);
            exp.getAndSet(i, 14000000);
        }
    }

    public int getLastDamage() {
        return lastDamage;
    }

    public int getHits() {
        return curStat.get(3);
    }

    public int getMaxHits() {
        return maxStat.get(3);
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
        return curStat.get(id);
    }

    public int getMaxStat(int id) {
        return maxStat.get(id);
    }

    public int getExp(int id) {
        return exp.get(id);
    }

    public AtomicIntegerArray getCurStats() {
        return curStat;
    }

    public AtomicIntegerArray getMaxStats() {
        return maxStat;
    }

    public AtomicIntegerArray getExps() {
        return exp;
    }
}
