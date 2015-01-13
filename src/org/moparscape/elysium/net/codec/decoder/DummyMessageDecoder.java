package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.DummyMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DummyMessageDecoder extends AbstractMessageDecoder<DummyMessage> {

    public DummyMessageDecoder() {
        super(DummyMessage.class, 0);
    }

    public DummyMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
