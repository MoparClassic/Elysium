package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ObjectSecondaryActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ObjectSecondaryActionMessageDecoder extends AbstractMessageDecoder<ObjectSecondaryActionMessage> {

    public ObjectSecondaryActionMessageDecoder() {
        super(ObjectSecondaryActionMessage.class, 40);
    }

    public ObjectSecondaryActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
