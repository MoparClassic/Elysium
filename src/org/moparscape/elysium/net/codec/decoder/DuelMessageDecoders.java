package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;

/**
 * Created by daniel on 14/01/2015.
 */
public final class DuelMessageDecoders {

    public final class DuelAcceptMessageDecoder extends AbstractMessageDecoder<DuelAcceptMessage> {

        public DuelAcceptMessageDecoder() {
            super(DuelAcceptMessage.class, 252);
        }

        public DuelAcceptMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuelConfirmAcceptMessageDecoder extends AbstractMessageDecoder<DuelConfirmAcceptMessage> {

        public DuelConfirmAcceptMessageDecoder() {
            super(DuelConfirmAcceptMessage.class, 87);
        }

        public DuelConfirmAcceptMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuelDeclineMessageDecoder extends AbstractMessageDecoder<DuelDeclineMessage> {

        public DuelDeclineMessageDecoder() {
            super(DuelDeclineMessage.class, 35);
        }

        public DuelDeclineMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuelInformationMessageDecoder extends AbstractMessageDecoder<DuelInformationMessage> {

        public DuelInformationMessageDecoder() {
            super(DuelInformationMessage.class, 123);
        }

        public DuelInformationMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuelOptionsMessageDecoder extends AbstractMessageDecoder<DuelOptionsMessage> {

        public DuelOptionsMessageDecoder() {
            super(DuelOptionsMessage.class, 225);
        }

        public DuelOptionsMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class DuelRequestMessageDecoder extends AbstractMessageDecoder<DuelRequestMessage> {

        public DuelRequestMessageDecoder() {
            super(DuelRequestMessage.class, 222);
        }

        public DuelRequestMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }
}
