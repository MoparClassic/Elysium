package org.moparscape.elysium.net.codec.decoder;

import org.moparscape.elysium.net.codec.Message;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public abstract class AbstractMessageDecoder<T extends Message> implements MessageDecoder {

    private final Class<T> messageType;

    private final int opcode;

    public AbstractMessageDecoder(Class<T> messageType, int opcode) {
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
