package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ExceptionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ExceptionMessageDecoder extends AbstractMessageDecoder<ExceptionMessage> {

    public ExceptionMessageDecoder() {
        super(ExceptionMessage.class, 156);
    }

    public ExceptionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
