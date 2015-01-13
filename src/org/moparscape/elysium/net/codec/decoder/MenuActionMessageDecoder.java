package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public MenuActionMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
