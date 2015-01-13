package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public WallObjectSecondaryActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
