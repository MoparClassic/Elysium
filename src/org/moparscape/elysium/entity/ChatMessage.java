package org.moparscape.elysium.entity;

import org.moparscape.elysium.util.DataConversions;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ChatMessage {

    private final byte[] message;

    private final Entity recipient;

    private final Entity sender;

    private int hashCode = 0;

    public ChatMessage(Entity sender, byte[] message) {
        this.sender = sender;
        this.message = message;
        this.recipient = null; // Recipient not used for this form
    }

    public ChatMessage(Entity sender, Entity recipient, String message) {
        this.sender = sender;
        this.recipient = recipient;
        this.message = DataConversions.stringToByteArray(message);
    }

    public int getLength() {
        return message.length;
    }

    public byte[] getMessage() {
        return message;
    }

    public Entity getRecipient() {
        return recipient;
    }

    public Entity getSender() {
        return sender;
    }

    @Override
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = Arrays.hashCode(message) | recipient.hashCode() | sender.hashCode();
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof ChatMessage)) {
            return false;
        }

        ChatMessage c = (ChatMessage) o;
        return recipient.equals(c.recipient) && sender.equals(c.sender) && Arrays.equals(message, c.message);
    }
}
