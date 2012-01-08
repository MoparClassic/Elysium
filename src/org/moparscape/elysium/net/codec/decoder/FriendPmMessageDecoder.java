package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.FriendPmMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class FriendPmMessageDecoder extends AbstractMessageDecoder<FriendPmMessage> {

    public FriendPmMessageDecoder() {
        super(FriendPmMessage.class, 254);
    }

    public FriendPmMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
