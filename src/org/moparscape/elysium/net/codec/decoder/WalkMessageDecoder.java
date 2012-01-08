package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.WalkMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class WalkMessageDecoder extends AbstractMessageDecoder<WalkMessage> {

    public WalkMessageDecoder() {
        super(WalkMessage.class, 132);
    }

    public WalkMessage decode(ChannelBuffer buffer, int length) {
        int startX = buffer.readShort();
        int startY = buffer.readShort();
        return null;
    }
}
