package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.BankCloseMessage;
import org.moparscape.elysium.net.codec.decoder.message.BankDepositMessage;
import org.moparscape.elysium.net.codec.decoder.message.BankWithdrawMessage;

/**
 * Created by daniel on 14/01/2015.
 */
public final class BankMessageDecoders {

    public final class BankCloseMessageDecoder extends AbstractMessageDecoder<BankCloseMessage> {

        public BankCloseMessageDecoder() {
            super(BankCloseMessage.class, 48);
        }

        public BankCloseMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class BankDepositMessageDecoder extends AbstractMessageDecoder<BankDepositMessage> {

        public BankDepositMessageDecoder() {
            super(BankDepositMessage.class, 198);
        }

        public BankDepositMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class BankWithdrawMessageDecoder extends AbstractMessageDecoder<BankWithdrawMessage> {

        public BankWithdrawMessageDecoder() {
            super(BankWithdrawMessage.class, 183);
        }

        public BankWithdrawMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
