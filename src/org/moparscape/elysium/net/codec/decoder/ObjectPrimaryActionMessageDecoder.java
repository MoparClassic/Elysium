package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ObjectPrimaryActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ObjectPrimaryActionMessageDecoder extends AbstractMessageDecoder<ObjectPrimaryActionMessage> {

    public ObjectPrimaryActionMessageDecoder() {
        super(ObjectPrimaryActionMessage.class, 51);
    }

    public ObjectPrimaryActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
