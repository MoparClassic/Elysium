package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.ReportMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class ReportMessageDecoder extends AbstractMessageDecoder<ReportMessage> {

    public ReportMessageDecoder() {
        super(ReportMessage.class, 7);
    }

    public ReportMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
