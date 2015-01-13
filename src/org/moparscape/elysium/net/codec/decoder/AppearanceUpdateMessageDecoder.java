package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public AppearanceUpdateMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
