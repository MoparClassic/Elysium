package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;

/**
 * Created by daniel on 14/01/2015.
 */
public final class PlayerMiscMessageDecoders {

    public final class AppearanceUpdateMessageDecoder extends AbstractMessageDecoder<AppearanceUpdateMessage> {

        public AppearanceUpdateMessageDecoder() {
            super(AppearanceUpdateMessage.class, 218);
        }

        public AppearanceUpdateMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class AppearancesMessageDecoder extends AbstractMessageDecoder<AppearancesMessage> {

        public AppearancesMessageDecoder() {
            super(AppearancesMessage.class, 83);
        }

        public AppearancesMessage decode(ByteBuf buffer, int length) {
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

    public final class GameSettingMessageDecoder extends AbstractMessageDecoder<GameSettingMessage> {

        public GameSettingMessageDecoder() {
            super(GameSettingMessage.class, 157);
        }

        public GameSettingMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class InventoryActionMessageDecoder extends AbstractMessageDecoder<InventoryActionMessage> {

        public InventoryActionMessageDecoder() {
            super(InventoryActionMessage.class, 89);
        }

        public InventoryActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class MenuActionMessageDecoder extends AbstractMessageDecoder<MenuActionMessage> {

        public MenuActionMessageDecoder() {
            super(MenuActionMessage.class, 154);
        }

        public MenuActionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
