package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.BankDepositMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class BankDepositMessageDecoder extends AbstractMessageDecoder<BankDepositMessage> {

    public BankDepositMessageDecoder() {
        super(BankDepositMessage.class, 198);
    }

    public BankDepositMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
