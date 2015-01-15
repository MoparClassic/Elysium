package org.moparscape.elysium.entity;

/**
 * Created by daniel on 15/01/2015.
 */
public interface Heartbeat {

    long getScheduledPulseTime();

    void setScheduledPulseTime(long time);

    boolean isCancelled();

    void pulse();
}
