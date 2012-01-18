package org.moparscape.elysium.net.codec.encoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginResponseMessage extends AbstractMessage {

    private final LoginResponse response;

    public LoginResponseMessage(LoginResponse response) {
        this.response = response;
    }

    public int getResponseCode() {
        return response.getResponseCode();
    }

    public static enum LoginResponse {

        UNKNOWN(22),
        SERVER_FULL(10),
        FAILED_PROFILE_DECODE(7),
        CLIENT_UPDATED(4),
        ACCOUNT_DISABLED(6),
        SESSION_REJECTED(5),
        USERNAME_IN_USE(3),
        INVALID_USER_PASS(2),
        SUCCESS(0);

        private final int response;

        LoginResponse(int response) {
            this.response = response;
        }

        public int getResponseCode() {
            return response;
        }
    }
}
