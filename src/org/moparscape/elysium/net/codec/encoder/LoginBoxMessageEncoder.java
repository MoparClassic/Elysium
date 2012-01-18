package org.moparscape.elysium.net.codec.encoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.moparscape.elysium.net.codec.encoder.message.LoginBoxMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginBoxMessageEncoder implements MessageEncoder<LoginBoxMessage> {

    public Class<LoginBoxMessage> getMessageType() {
        return LoginBoxMessage.class;
    }

    public ChannelBuffer encode(LoginBoxMessage message) {
        byte[] lastIpAddress = message.getLastIpAddress();
        ChannelBuffer buffer = ChannelBuffers.buffer(5 + lastIpAddress.length);
        buffer.writeByte(248);
        buffer.writeShort(message.getDaysSinceLogin());
        buffer.writeShort(message.getRemainingSubscription());
        buffer.writeBytes(lastIpAddress);

        return buffer;
    }
}
