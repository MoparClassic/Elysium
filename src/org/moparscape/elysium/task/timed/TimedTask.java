package org.moparscape.elysium.task.timed;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface TimedTask extends Runnable, Comparable<TimedTask> {

    long getExecutionTime();

    boolean shouldRepeat();

    void setNextRunningTime(long curTimeMillis);
}
