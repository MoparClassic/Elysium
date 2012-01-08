package org.moparscape.elysium.net.codec.encoder;

import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public abstract class AbstractMessageEncoder<T extends Message> implements MessageEncoder {

    private final Class<T> messageType;

    private final int opcode;

    public AbstractMessageEncoder(Class<T> messageType, int opcode) {
        this.messageType = messageType;
        this.opcode = opcode;
    }

    public Class<T> getMessageType() {
        return messageType;
    }

    public int getOpcode() {
        return opcode;
    }
}
