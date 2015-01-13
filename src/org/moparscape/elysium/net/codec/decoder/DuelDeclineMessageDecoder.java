package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public DuelDeclineMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
