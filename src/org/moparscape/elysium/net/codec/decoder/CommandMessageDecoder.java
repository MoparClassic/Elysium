package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.CommandMessage;
import org.moparscape.elysium.util.BufferUtil;

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
        String input = BufferUtil.readString(buffer);
        int firstSpace = input.indexOf(" ");
        String command = input;
        String[] args = new String[0];

        if (firstSpace != -1) {
            command = input.substring(0, firstSpace).trim();
            args = input.substring(firstSpace + 1).trim().split(" ");
        }

        return new CommandMessage(command, args);
    }
}
