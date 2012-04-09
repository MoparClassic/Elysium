package org.moparscape.elysium.task;

import org.moparscape.elysium.net.Session;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionPulseTask implements Runnable {

    private final Iterable<Session> sessions;

    public SessionPulseTask(Iterable<Session> sessions) {
        this.sessions = sessions;
    }

    public void run() {
        for (Session s : sessions) {
            s.pulse();
        }
    }
}
