package org.moparscape.elysium.net.codec;

import org.moparscape.elysium.net.codec.decoder.AttackNpcMessageDecoder;
import org.moparscape.elysium.net.codec.decoder.AttackPlayerMessageDecoder;
import org.moparscape.elysium.net.codec.decoder.DummyMessageDecoder;
import org.moparscape.elysium.net.codec.decoder.MessageDecoder;
import org.moparscape.elysium.net.codec.encoder.MessageEncoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CodecLookupService {

    private static final List<MessageDecoder<? extends Message>> decoders =
            new ArrayList<MessageDecoder<? extends Message>>(255);

    private static final List<MessageEncoder<? extends Message>> encoders =
            new ArrayList<MessageEncoder<? extends Message>>(255);

    private static final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap =
            new HashMap<Class<? extends Message>, MessageDecoder<? extends Message>>();

    private static final Map<Class<? extends Message>, MessageEncoder<?>> encoderMap =
            new HashMap<Class<? extends Message>, MessageEncoder<?>>();

    // Empty constructor to enforce singleton property
    private CodecLookupService() {
    }

    public static void init() {
        // Initialise all of the decoders in this block
        try {
            bindDecoder(AttackNpcMessageDecoder.class);
            bindDecoder(AttackPlayerMessageDecoder.class);
            bindDecoder(DummyMessageDecoder.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

        // Initialise all of the encoders in this block
        try {

        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    private static <T extends Message, C extends MessageDecoder<T>> void bindDecoder(Class<C> type)
            throws IllegalAccessException, InstantiationException {
        MessageDecoder<T> decoder = type.newInstance();
        int opcode = decoder.getOpcode();

        validateOpcode(opcode);
        decoders.set(opcode, decoder);
        decoderMap.put(decoder.getMessageType(), decoder);
    }

    private static <T extends Message, C extends MessageEncoder<T>> void bindEncoder(Class<C> type)
            throws IllegalAccessException, InstantiationException {
        MessageEncoder<T> encoder = type.newInstance();
        int opcode = encoder.getOpcode();

        validateOpcode(opcode);
        encoders.set(opcode, encoder);
        encoderMap.put(encoder.getMessageType(), encoder);
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
}
