package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.TrapMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class TrapMessageDecoder extends AbstractMessageDecoder<TrapMessage> {

    public TrapMessageDecoder() {
        super(TrapMessage.class, 3);
    }

    public TrapMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
