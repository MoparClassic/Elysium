package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelInformationMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelInformationMessageDecoder extends AbstractMessageDecoder<DuelInformationMessage> {

    public DuelInformationMessageDecoder() {
        super(DuelInformationMessage.class, 123);
    }

    public DuelInformationMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
