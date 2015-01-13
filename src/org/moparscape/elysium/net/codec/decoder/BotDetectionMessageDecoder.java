package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.BotDetectionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BotDetectionMessageDecoder extends AbstractMessageDecoder<BotDetectionMessage> {

    public BotDetectionMessageDecoder() {
        super(BotDetectionMessage.class, 69);
    }

    public BotDetectionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
