package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PublicChatMessage extends AbstractMessage {

    private final byte[] messagePayload;

    public PublicChatMessage(byte[] messagePayload) {
        this.messagePayload = messagePayload;
    }

    public byte[] getMessagePayload() {
        return messagePayload;
    }
}
