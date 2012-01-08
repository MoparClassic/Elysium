package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.PrayerDeactivateMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PrayerDeactivateMessageDecoder extends AbstractMessageDecoder<PrayerDeactivateMessage> {

    public PrayerDeactivateMessageDecoder() {
        super(PrayerDeactivateMessage.class, 248);
    }

    public PrayerDeactivateMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
