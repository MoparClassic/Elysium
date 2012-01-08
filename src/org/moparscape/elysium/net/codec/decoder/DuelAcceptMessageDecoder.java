package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelAcceptMessageDecoder extends AbstractMessageDecoder<DuelAcceptMessage> {

    public DuelAcceptMessageDecoder() {
        super(DuelAcceptMessage.class, 252);
    }

    public DuelAcceptMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
