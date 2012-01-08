package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.WallObjectSecondaryActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WallObjectSecondaryActionMessageDecoder extends AbstractMessageDecoder<WallObjectSecondaryActionMessage> {

    public WallObjectSecondaryActionMessageDecoder() {
        super(WallObjectSecondaryActionMessage.class, 235);
    }

    public WallObjectSecondaryActionMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
