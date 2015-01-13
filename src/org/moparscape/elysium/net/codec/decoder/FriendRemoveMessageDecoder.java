package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.FriendRemoveMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class FriendRemoveMessageDecoder extends AbstractMessageDecoder<FriendRemoveMessage> {

    public FriendRemoveMessageDecoder() {
        super(FriendRemoveMessage.class, 52);
    }

    public FriendRemoveMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
