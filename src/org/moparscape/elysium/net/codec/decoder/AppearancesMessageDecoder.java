package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.AppearancesMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AppearancesMessageDecoder extends AbstractMessageDecoder<AppearancesMessage> {

    public AppearancesMessageDecoder() {
        super(AppearancesMessage.class, 83);
    }

    public AppearancesMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
