package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.PrivacySettingMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class PrivacySettingMessageDecoder extends AbstractMessageDecoder<PrivacySettingMessage> {

    public PrivacySettingMessageDecoder() {
        super(PrivacySettingMessage.class, 176);
    }

    public PrivacySettingMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
