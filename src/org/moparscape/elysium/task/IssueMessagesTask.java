package org.moparscape.elysium.task;

import org.moparscape.elysium.entity.ChatMessage;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.entity.component.UpdateProxy;
import org.moparscape.elysium.world.Region;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public class IssueMessagesTask implements Runnable {

    public final Iterable<Player> players;

    public IssueMessagesTask(Iterable<Player> players) {
        this.players = players;
    }

    public void run() {
        try {
            for (Player p : players) {
                UpdateProxy proxy = p.getUpdateProxy();
                ChatMessage message = proxy.getNextChatMessage();

                if (message == null || !p.isLoggedIn()) {
                    continue;
                }

                for (Player target : Region.getViewablePlayers(p, 16)) {
                    UpdateProxy targetProxy = target.getUpdateProxy();

                    targetProxy.informOfChatMessage(message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
