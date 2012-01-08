package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.MenuActionMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class MenuActionMessageDecoder extends AbstractMessageDecoder<MenuActionMessage> {

    public MenuActionMessageDecoder() {
        super(MenuActionMessage.class, 154);
    }

    public MenuActionMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
