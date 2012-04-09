package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.Observer;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.AppearancesMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AppearancesMessageHandler extends MessageHandler<AppearancesMessage> {

    public void handle(Session session, Player player, AppearancesMessage message) {
        Observer o = player.getComponent(Observer.class);
        o.addPlayerAppearanceIds(message.getIndicies(), message.getAppearanceIds());
        System.out.println(message);
    }
}
