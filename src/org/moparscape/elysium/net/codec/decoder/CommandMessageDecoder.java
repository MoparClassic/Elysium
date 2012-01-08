package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.CommandMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CommandMessageDecoder extends AbstractMessageDecoder<CommandMessage> {

    public CommandMessageDecoder() {
        super(CommandMessage.class, 90);
    }

    public CommandMessage decode(ChannelBuffer buffer, int length) {
        throw new UnsupportedOperationException();
    }
}
