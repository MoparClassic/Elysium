package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.PublicChatMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PublicChatMessageDecoder extends AbstractMessageDecoder<PublicChatMessage> {

    public PublicChatMessageDecoder() {
        super(PublicChatMessage.class, 145);
    }

    public PublicChatMessage decode(ByteBuf buffer, int length) {
        byte[] message = new byte[length];
        buffer.readBytes(message);
        return new PublicChatMessage(message);
    }
}
