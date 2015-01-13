package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.BankCloseMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BankCloseMessageDecoder extends AbstractMessageDecoder<BankCloseMessage> {

    public BankCloseMessageDecoder() {
        super(BankCloseMessage.class, 48);
    }

    public BankCloseMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
