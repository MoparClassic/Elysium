package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
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

    public GameSettingMessage decode(ByteBuf buffer, int length) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
