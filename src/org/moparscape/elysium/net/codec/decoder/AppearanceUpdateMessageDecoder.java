package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.AppearanceUpdateMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AppearanceUpdateMessageDecoder extends AbstractMessageDecoder<AppearanceUpdateMessage> {

    public AppearanceUpdateMessageDecoder() {
        super(AppearanceUpdateMessage.class, 218);
    }

    public AppearanceUpdateMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
