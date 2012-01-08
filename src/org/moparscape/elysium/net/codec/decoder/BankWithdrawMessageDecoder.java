package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
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

    public BankWithdrawMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException();
    }
}
