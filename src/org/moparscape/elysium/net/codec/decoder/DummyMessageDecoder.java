package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public DummyMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
