package org.moparscape.elysium.net.codec.decoder;

import io.netty.buffer.ByteBuf;
import org.moparscape.elysium.net.codec.decoder.message.*;
import org.moparscape.elysium.util.BufferUtil;

/**
 * Created by daniel on 14/01/2015.
 */
public final class MiscMessageDecoders {

    public final class BotDetectionMessageDecoder extends AbstractMessageDecoder<BotDetectionMessage> {

        public BotDetectionMessageDecoder() {
            super(BotDetectionMessage.class, 69);
        }

        public BotDetectionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class CommandMessageDecoder extends AbstractMessageDecoder<CommandMessage> {

        public CommandMessageDecoder() {
            super(CommandMessage.class, 90);
        }

        public CommandMessage decode(ByteBuf buffer, int length) {
            String input = BufferUtil.readString(buffer);
            int firstSpace = input.indexOf(" ");
            String command = input;
            String[] args = new String[0];

            if (firstSpace != -1) {
                command = input.substring(0, firstSpace).trim();
                args = input.substring(firstSpace + 1).trim().split(" ");
            }

            return new CommandMessage(command, args);
        }
    }

    public final class DummyMessageDecoder extends AbstractMessageDecoder<DummyMessage> {

        public DummyMessageDecoder() {
            super(DummyMessage.class, 0);
        }

        public DummyMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class ExceptionMessageDecoder extends AbstractMessageDecoder<ExceptionMessage> {

        public ExceptionMessageDecoder() {
            super(ExceptionMessage.class, 156);
        }

        public ExceptionMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class InvalidMessageDecoder implements MessageDecoder<InvalidMessage> {

        public int getOpcode() {
            throw new IllegalStateException("This message decoder should not be activated");
        }

        public Class<InvalidMessage> getMessageType() {
            return InvalidMessage.class;
        }

        public InvalidMessage decode(ByteBuf buffer, int length) {
            byte[] data = new byte[length];
            buffer.readBytes(data, 0, length);

            return new InvalidMessage();
        }
    }

    public final class PingMessageDecoder extends AbstractMessageDecoder<PingMessage> {

        public PingMessageDecoder() {
            super(PingMessage.class, 5);
        }

        public PingMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }

    public final class ReportMessageDecoder extends AbstractMessageDecoder<ReportMessage> {

        public ReportMessageDecoder() {
            super(ReportMessage.class, 7);
        }

        public ReportMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class SleepwordMessageDecoder extends AbstractMessageDecoder<SleepwordMessage> {

        public SleepwordMessageDecoder() {
            super(SleepwordMessage.class, 200);
        }

        public SleepwordMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException();
        }
    }

    public final class TrapMessageDecoder extends AbstractMessageDecoder<TrapMessage> {

        public TrapMessageDecoder() {
            super(TrapMessage.class, 3);
        }

        public TrapMessage decode(ByteBuf buffer, int length) {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
