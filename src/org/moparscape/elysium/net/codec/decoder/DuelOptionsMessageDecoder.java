package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.DuelOptionsMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class DuelOptionsMessageDecoder extends AbstractMessageDecoder<DuelOptionsMessage> {

    public DuelOptionsMessageDecoder() {
        super(DuelOptionsMessage.class, 225);
    }

    public DuelOptionsMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
