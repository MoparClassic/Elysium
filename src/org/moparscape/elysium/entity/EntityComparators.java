package org.moparscape.elysium.entity;

import java.util.Comparator;

/**
 * Created by daniel on 15/01/2015.
 */
public final class EntityComparators {

    public static class HeartbeatComparator implements Comparator<Heartbeat> {

        public int compare(Heartbeat a, Heartbeat b) {
            return Long.compare(a.getScheduledPulseTime(), b.getScheduledPulseTime());
        }
    }
}
