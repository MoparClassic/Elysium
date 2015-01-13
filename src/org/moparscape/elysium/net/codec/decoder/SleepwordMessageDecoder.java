package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.SleepwordMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class SleepwordMessageDecoder extends AbstractMessageDecoder<SleepwordMessage> {

    public SleepwordMessageDecoder() {
        super(SleepwordMessage.class, 200);
    }

    public SleepwordMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
