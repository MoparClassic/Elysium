package org.moparscape.elysium.entity.component;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Skills extends AbstractComponent {

    private static final int SKILL_COUNT = 18;
    private int combatLevel = 3;
    private AtomicIntegerArray curStat = new AtomicIntegerArray(SKILL_COUNT);
    private AtomicIntegerArray exp = new AtomicIntegerArray(SKILL_COUNT);
    private int fatigue;
    private int lastDamage = 0;
    private AtomicIntegerArray maxStat = new AtomicIntegerArray(SKILL_COUNT);

    public Skills() {
        for (int i = 0; i < SKILL_COUNT; i++) {
            curStat.getAndSet(i, 99);
            maxStat.getAndSet(i, 99);
            exp.getAndSet(i, 14000000);
        }
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public int getCurStat(int id) {
        return curStat.get(id);
    }

    public AtomicIntegerArray getCurStats() {
        return curStat;
    }

    public int getExp(int id) {
        return exp.get(id);
    }

    public AtomicIntegerArray getExps() {
        return exp;
    }

    public int getFatigue() {
        return fatigue;
    }

    public void setFatigue(int fatigue) {
        this.fatigue = fatigue;
    }

    public int getHits() {
        return curStat.get(3);
    }

    public int getLastDamage() {
        return lastDamage;
    }

    public int getMaxHits() {
        return maxStat.get(3);
    }

    public int getMaxStat(int id) {
        return maxStat.get(id);
    }

    public AtomicIntegerArray getMaxStats() {
        return maxStat;
    }
}
