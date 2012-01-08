package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.GameSettingMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class GameSettingMessageDecoder extends AbstractMessageDecoder<GameSettingMessage> {

    public GameSettingMessageDecoder() {
        super(GameSettingMessage.class, 157);
    }

    public GameSettingMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
