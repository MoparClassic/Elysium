package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public ReportMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
