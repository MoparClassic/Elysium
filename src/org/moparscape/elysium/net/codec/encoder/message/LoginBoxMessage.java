package org.moparscape.elysium.net.codec.encoder.message;

import org.moparscape.elysium.entity.Player;
import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginBoxMessage extends AbstractMessage {

    private int daysSinceLogin;

    private int remainingSubscription;

    private byte[] lastIpAddress;

    public LoginBoxMessage(Player player) {
        this.daysSinceLogin = 0;
        this.remainingSubscription = 0;
        this.lastIpAddress = "127.0.0.1".getBytes();
    }

    public int getDaysSinceLogin() {
        return daysSinceLogin;
    }

    public int getRemainingSubscription() {
        return remainingSubscription;
    }

    public byte[] getLastIpAddress() {
        return lastIpAddress;
    }
}
