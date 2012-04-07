package org.moparscape.elysium.net.codec.decoder.message;

import org.moparscape.elysium.net.codec.AbstractMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CommandMessage extends AbstractMessage {

    private final String command;

    private final String[] args;

    public CommandMessage(String command, String[] args) {
        this.command = command.toLowerCase();
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public String[] getArguments() {
        return args;
    }

    public boolean hasArguments() {
        return args.length > 0;
    }
}
