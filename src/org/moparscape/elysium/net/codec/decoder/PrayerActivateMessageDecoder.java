package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public PrayerActivateMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
