package org.moparscape.elysium.net.codec.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.moparscape.elysium.net.codec.encoder.message.LoginResponseMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginResponseMessageEncoder implements MessageEncoder<LoginResponseMessage> {

    public Class<LoginResponseMessage> getMessageType() {
        return LoginResponseMessage.class;
    }

    public ChannelBuffer encode(LoginResponseMessage message) {
        ChannelBuffer buffer = ChannelBuffers.buffer(1);
        buffer.writeByte(message.getResponseCode());

        return buffer;
    }
}
