package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.LoginMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class LoginMessageDecoder extends AbstractMessageDecoder<LoginMessage> {
    
    public LoginMessageDecoder() {
        super(LoginMessage.class, 77);
    }
    
    public LoginMessage decode(ChannelBuffer buffer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
