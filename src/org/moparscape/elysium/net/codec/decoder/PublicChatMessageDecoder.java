package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public PublicChatMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
