package org.moparscape.elysium.net.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import org.moparscape.elysium.Server;
import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.Packets;
import org.moparscape.elysium.net.Session;
import org.moparscape.elysium.net.codec.decoder.message.LogoutRequestMessage;
import org.moparscape.elysium.world.World;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LogoutRequestMessageHandler extends MessageHandler<LogoutRequestMessage> {
    @Override
    public void handle(final Session session, Player player, LogoutRequestMessage message) {
        // TODO: Determine if the player is allowed to logout, and respond accordingly
        if (true /* The player is allowed to log out */) {
            player.setLoggedIn(false);
            World.getInstance().unregisterPlayer(player);
            ChannelFuture future = Packets.sendLogout(player);
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    Server.getInstance().unregisterSession(session);
                    session.close();
                }
            });
        } else {
            Packets.sendCantLogout(player);
        }
    }
}
