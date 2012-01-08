package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Entity;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public interface MessageHandler<T extends Message> {

    void handle(Session session, Entity player, T message);
}
