package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.BankWithdrawMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BankWithdrawMessageDecoder extends AbstractMessageDecoder<BankWithdrawMessage> {

    public BankWithdrawMessageDecoder() {
        super(BankWithdrawMessage.class, 183);
    }

    public BankWithdrawMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
