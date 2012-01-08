package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.FriendAddMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class FriendAddMessageDecoder extends AbstractMessageDecoder<FriendAddMessage> {

    public FriendAddMessageDecoder() {
        super(FriendAddMessage.class, 168);
    }

    public FriendAddMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
