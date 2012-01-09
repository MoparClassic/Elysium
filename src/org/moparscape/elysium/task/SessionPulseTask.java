package org.moparscape.elysium.task;

import org.moparscape.elysium.Server;
import org.moparscape.elysium.net.Session;

import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionPulseTask implements Callable<Void> {

    private static Server server = Server.getInstance();

    private final Iterable<Session> sessions;

    public SessionPulseTask(Iterable<Session> sessions) {
        this.sessions = sessions;
    }

    public Void call() {
        for (Session s : sessions) {
            s.pulse();
        }

        return null;
    }
}
