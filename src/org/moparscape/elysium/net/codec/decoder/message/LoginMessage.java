package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginMessage extends AbstractMessage {

    private final String username;
    private final String password;
    private final int uid;
    private final int version;
    private final int[] sessionKeys;
    private final boolean reconnecting;

    public LoginMessage(String username, String password, int uid,
                        int version, int[] sessionKeys, boolean reconnecting) {
        this.username = username;
        this.password = password;
        this.uid = uid;
        this.version = version;
        this.sessionKeys = sessionKeys;
        this.reconnecting = reconnecting;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getUid() {
        return uid;
    }

    public int getVersion() {
        return version;
    }

    public int[] getSessionKeys() {
        return sessionKeys;
    }

    public boolean isReconnecting() {
        return reconnecting;
    }
}
