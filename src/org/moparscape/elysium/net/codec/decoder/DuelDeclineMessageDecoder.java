package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelDeclineMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelDeclineMessageDecoder extends AbstractMessageDecoder<DuelDeclineMessage> {

    public DuelDeclineMessageDecoder() {
        super(DuelDeclineMessage.class, 35);
    }

    public DuelDeclineMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
