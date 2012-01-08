package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelConfirmAcceptMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelConfirmAcceptMessageDecoder extends AbstractMessageDecoder<DuelConfirmAcceptMessage> {

    public DuelConfirmAcceptMessageDecoder() {
        super(DuelConfirmAcceptMessage.class, 87);
    }

    public DuelConfirmAcceptMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
