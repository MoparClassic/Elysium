package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.InvalidMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class InvalidMessageDecoder implements MessageDecoder<InvalidMessage> {

    public int getOpcode() {
        throw new IllegalStateException("This message decoder should not be activated");
    }

    public Class<InvalidMessage> getMessageType() {
        return InvalidMessage.class;
    }

    public InvalidMessage decode(ByteBuf buffer, int length) {
        byte[] data = new byte[length];
        buffer.readBytes(data, 0, length);

        return new InvalidMessage();
    }
}
