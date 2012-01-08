package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ObjectSecondaryActionMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
