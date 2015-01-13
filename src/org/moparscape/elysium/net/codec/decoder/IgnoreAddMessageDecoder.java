package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.IgnoreAddMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class IgnoreAddMessageDecoder extends AbstractMessageDecoder<IgnoreAddMessage> {

    public IgnoreAddMessageDecoder() {
        super(IgnoreAddMessage.class, 25);
    }

    public IgnoreAddMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
