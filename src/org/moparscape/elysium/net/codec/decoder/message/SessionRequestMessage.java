package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SessionRequestMessage extends AbstractMessage {

    private final String className;

    private final byte userByte;

    public SessionRequestMessage(String className, byte userByte) {
        this.className = className;
        this.userByte = userByte;
    }

    public String getClassName() {
        return className;
    }

    public byte getUserByte() {
        return userByte;
    }
}
