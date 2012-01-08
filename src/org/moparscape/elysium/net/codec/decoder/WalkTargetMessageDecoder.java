package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.WalkTargetMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WalkTargetMessageDecoder extends AbstractMessageDecoder<WalkTargetMessage> {

    public WalkTargetMessageDecoder() {
        super(WalkTargetMessage.class, 246);
    }

    public WalkTargetMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
