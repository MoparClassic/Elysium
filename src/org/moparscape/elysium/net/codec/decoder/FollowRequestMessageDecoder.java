package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.FollowRequestMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class FollowRequestMessageDecoder extends AbstractMessageDecoder<FollowRequestMessage> {

    public FollowRequestMessageDecoder() {
        super(FollowRequestMessage.class, 68);
    }

    public FollowRequestMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
