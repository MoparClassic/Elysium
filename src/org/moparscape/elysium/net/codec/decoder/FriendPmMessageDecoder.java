package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public FriendPmMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
