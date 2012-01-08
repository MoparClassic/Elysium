package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.PrayerActivateMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PrayerActivateMessageDecoder extends AbstractMessageDecoder<PrayerActivateMessage> {

    public PrayerActivateMessageDecoder() {
        super(PrayerActivateMessage.class, 56);
    }

    public PrayerActivateMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
