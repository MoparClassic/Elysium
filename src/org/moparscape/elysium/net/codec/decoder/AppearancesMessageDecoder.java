package org.moparscape.elysium.net.codec.decoder;

import org.jboss.netty.buffer.ChannelBuffer;
import org.moparscape.elysium.net.codec.decoder.message.AppearancesMessage;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class AppearancesMessageDecoder extends AbstractMessageDecoder<AppearancesMessage> {

    public AppearancesMessageDecoder() {
        super(AppearancesMessage.class, 83);
    }

    public AppearancesMessage decode(ChannelBuffer buffer, int length) {
        int mobCount = buffer.readShort();
        int[] indicies = new int[mobCount];
        int[] appearanceIds = new int[mobCount];

        for (int i = 0; i < mobCount; i++) {
            indicies[i] = buffer.readShort();
            appearanceIds[i] = buffer.readShort();
        }

        return new AppearancesMessage(indicies, appearanceIds);
    }
}
