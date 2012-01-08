package org.moparscape.elysium.net.codec;

import org.moparscape.elysium.net.codec.decoder.MessageDecoder;
import org.moparscape.elysium.net.codec.encoder.MessageEncoder;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CodecLookupService {

    private static final List<MessageDecoder<? extends Message>> decoders;

    private static final List<MessageEncoder<? extends Message>> encoders;

    private static final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap;

    private static final Map<Class<? extends Message>, MessageEncoder<? extends Message>> encoderMap;

    // Empty constructor to enforce singleton property
    private CodecLookupService() {
    }

    static {
        ImmutableBindingBuilder bindings = new ImmutableBindingBuilder();
        // Initialise all of the decoders in this block
        try {
//            bindDecoder(AttackNpcMessageDecoder.class);
//            bindDecoder(AttackPlayerMessageDecoder.class);
//            bindDecoder(DummyMessageDecoder.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

        // Initialise all of the encoders in this block
        try {

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

        decoders = bindings.decoderList();
        encoders = bindings.encoderList();
        decoderMap = bindings.messageToDecoderMap();
        encoderMap = bindings.messageToEncoderMap();
    }

    private static void validateOpcode(int opcode) {
        if (opcode < 0 || opcode >= 255) {
            throw new RuntimeException("CodecLookupService invalid opcode: " + opcode);
        }
    }

    public static MessageDecoder<? extends Message> getDecoder(int opcode) {
        validateOpcode(opcode);
        return decoders.get(opcode);
    }

    public static MessageEncoder<? extends Message> getEncoder(int opcode) {
        validateOpcode(opcode);
        return encoders.get(opcode);
    }

    public static MessageDecoder<? extends Message> getDecoder(Class<? extends Message> type) {
        return decoderMap.get(type);
    }

    public static MessageEncoder<? extends Message> getEncoder(Class<? extends Message> type) {
        return encoderMap.get(type);
    }

    private static final class ImmutableBindingBuilder {

        private final List<MessageDecoder<? extends Message>> decoders =
                new ArrayList<MessageDecoder<? extends Message>>(255);

        private final List<MessageEncoder<? extends Message>> encoders =
                new ArrayList<MessageEncoder<? extends Message>>(255);

        private final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap =
                new HashMap<Class<? extends Message>, MessageDecoder<? extends Message>>(100, 0.50f);

        private final Map<Class<? extends Message>, MessageEncoder<? extends Message>> encoderMap =
                new HashMap<Class<? extends Message>, MessageEncoder<? extends Message>>(100, 0.50f);

        public <T extends Message, C extends MessageDecoder<T>> void bindDecoder(Class<C> type)
                throws IllegalAccessException, InstantiationException {
            MessageDecoder<T> decoder = type.newInstance();
            int opcode = decoder.getOpcode();

            validateOpcode(opcode);
            this.decoders.set(opcode, decoder);
            this.decoderMap.put(decoder.getMessageType(), decoder);
        }

        public <T extends Message, C extends MessageEncoder<T>> void bindEncoder(Class<C> type)
                throws IllegalAccessException, InstantiationException {
            MessageEncoder<T> encoder = type.newInstance();
            int opcode = encoder.getOpcode();

            validateOpcode(opcode);
            this.encoders.set(opcode, encoder);
            this.encoderMap.put(encoder.getMessageType(), encoder);
        }

        public Map<Class<? extends Message>, MessageDecoder<? extends Message>> messageToDecoderMap() {
            return Collections.unmodifiableMap(this.decoderMap);
        }

        public Map<Class<? extends Message>, MessageEncoder<? extends Message>> messageToEncoderMap() {
            return Collections.unmodifiableMap(this.encoderMap);
        }

        public List<MessageDecoder<? extends Message>> decoderList() {
            return Collections.unmodifiableList(this.decoders);
        }

        public List<MessageEncoder<? extends Message>> encoderList() {
            return Collections.unmodifiableList(this.encoders);
        }
    }
}
