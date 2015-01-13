package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public PrayerDeactivateMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
