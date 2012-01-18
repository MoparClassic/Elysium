package org.moparscape.elysium.net.codec;

import org.moparscape.elysium.net.codec.decoder.*;
import org.moparscape.elysium.net.codec.encoder.LoginResponseMessageEncoder;
import org.moparscape.elysium.net.codec.encoder.MessageEncoder;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CodecLookupService {

    private static final List<MessageDecoder<? extends Message>> decoders;

    private static final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap;

    private static final Map<Class<? extends Message>, MessageEncoder<? extends Message>> encoderMap;

    // Empty constructor to enforce singleton property
    private CodecLookupService() {
    }

    static {
        ImmutableBindingBuilder bindings = new ImmutableBindingBuilder();
        // Initialise all of the decoders in this block
        try {
            bindings.bindDecoder(AppearancesMessageDecoder.class);
            bindings.bindDecoder(AppearanceUpdateMessageDecoder.class);
            bindings.bindDecoder(AttackNpcMessageDecoder.class);
            bindings.bindDecoder(AttackPlayerMessageDecoder.class);
            bindings.bindDecoder(AttackStyleMessageDecoder.class);
            bindings.bindDecoder(BankCloseMessageDecoder.class);
            bindings.bindDecoder(BankDepositMessageDecoder.class);
            bindings.bindDecoder(BankWithdrawMessageDecoder.class);
            bindings.bindDecoder(BotDetectionMessageDecoder.class);
            bindings.bindDecoder(CommandMessageDecoder.class);
            bindings.bindDecoder(DuelAcceptMessageDecoder.class);
            bindings.bindDecoder(DuelConfirmAcceptMessageDecoder.class);
            bindings.bindDecoder(DuelDeclineMessageDecoder.class);
            bindings.bindDecoder(DuelInformationMessageDecoder.class);
            bindings.bindDecoder(DuelOptionsMessageDecoder.class);
            bindings.bindDecoder(DuelRequestMessageDecoder.class);
            //bindings.bindDecoder(DummyMessageDecoder.class);
            bindings.bindDecoder(ExceptionMessageDecoder.class);
            bindings.bindDecoder(FollowRequestMessageDecoder.class);
            bindings.bindDecoder(FriendAddMessageDecoder.class);
            bindings.bindDecoder(FriendPmMessageDecoder.class);
            bindings.bindDecoder(FriendRemoveMessageDecoder.class);
            bindings.bindDecoder(GameSettingMessageDecoder.class);
            bindings.bindDecoder(IgnoreAddMessageDecoder.class);
            bindings.bindDecoder(IgnoreRemoveMessageDecoder.class);
            bindings.bindDecoder(InventoryActionMessageDecoder.class);
            bindings.bindDecoder(ItemDoorMessageDecoder.class);
            bindings.bindDecoder(ItemDropMessageDecoder.class);
            bindings.bindDecoder(ItemGameObjectMessageDecoder.class);
            bindings.bindDecoder(ItemGroundItemMessageDecoder.class);
            bindings.bindDecoder(ItemItemMessageDecoder.class);
            bindings.bindDecoder(ItemNpcMessageDecoder.class);
            bindings.bindDecoder(ItemPickupMessageDecoder.class);
            bindings.bindDecoder(ItemPlayerMessageDecoder.class);
            bindings.bindDecoder(ItemUnwieldMessageDecoder.class);
            bindings.bindDecoder(ItemWieldMessageDecoder.class);
            bindings.bindDecoder(LoginMessageDecoder.class);
            bindings.bindDecoder(LogoutMessageDecoder.class);
            bindings.bindDecoder(LogoutRequestMessageDecoder.class);
            bindings.bindDecoder(MenuActionMessageDecoder.class);
            bindings.bindDecoder(NpcActionMessageDecoder.class);
            bindings.bindDecoder(NpcChatMessageDecoder.class);
            bindings.bindDecoder(ObjectPrimaryActionMessageDecoder.class);
            bindings.bindDecoder(ObjectSecondaryActionMessageDecoder.class);
            bindings.bindDecoder(PingMessageDecoder.class);
            bindings.bindDecoder(PrayerActivateMessageDecoder.class);
            bindings.bindDecoder(PrayerDeactivateMessageDecoder.class);
            bindings.bindDecoder(PrivacySettingMessageDecoder.class);
            bindings.bindDecoder(PublicChatMessageDecoder.class);
            bindings.bindDecoder(ReportMessageDecoder.class);
            bindings.bindDecoder(SessionRequestMessageDecoder.class);
            bindings.bindDecoder(ShopBuyMessageDecoder.class);
            bindings.bindDecoder(ShopCloseMessageDecoder.class);
            bindings.bindDecoder(ShopSellMessageDecoder.class);
            bindings.bindDecoder(SleepwordMessageDecoder.class);
            bindings.bindDecoder(SpellDoorMessageDecoder.class);
            bindings.bindDecoder(SpellGameObjectMessageDecoder.class);
            bindings.bindDecoder(SpellGroundItemMessageDecoder.class);
            bindings.bindDecoder(SpellGroundMessageDecoder.class);
            bindings.bindDecoder(SpellInvItemMessageDecoder.class);
            bindings.bindDecoder(SpellNpcMessageDecoder.class);
            bindings.bindDecoder(SpellPlayerMessageDecoder.class);
            bindings.bindDecoder(SpellSelfMessageDecoder.class);
            bindings.bindDecoder(TradeAcceptMessageDecoder.class);
            bindings.bindDecoder(TradeConfirmAcceptMessageDecoder.class);
            bindings.bindDecoder(TradeDeclineMessageDecoder.class);
            bindings.bindDecoder(TradeInformationMessageDecoder.class);
            bindings.bindDecoder(TradeRequestMessageDecoder.class);
            bindings.bindDecoder(TrapMessageDecoder.class);
            bindings.bindDecoder(WalkMessageDecoder.class);
            bindings.bindDecoder(WalkTargetMessageDecoder.class);
            bindings.bindDecoder(WallObjectPrimaryActionMessageDecoder.class);
            bindings.bindDecoder(WallObjectSecondaryActionMessageDecoder.class);
        } catch (Exception e) {
            System.out.println("Failed to initialise decoders");
            System.out.println(e);
            throw new ExceptionInInitializerError(e);
        }

        // Initialise all of the encoders in this block
        try {
            bindings.bindEncoder(LoginResponseMessageEncoder.class);
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

        decoders = bindings.decoderList();
        decoderMap = bindings.messageToDecoderMap();
        encoderMap = bindings.messageToEncoderMap();
    }

    public static MessageDecoder<? extends Message> getDecoder(int opcode) {
        if (opcode < 0 || opcode >= 255) {
            return null;
        }

        return decoders.get(opcode);
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

        private final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap =
                new HashMap<Class<? extends Message>, MessageDecoder<? extends Message>>(100, 0.50f);

        private final Map<Class<? extends Message>, MessageEncoder<? extends Message>> encoderMap =
                new HashMap<Class<? extends Message>, MessageEncoder<? extends Message>>(100, 0.50f);

        public ImmutableBindingBuilder() {
            // Add dummy invalid message handlers to the list.
            // These are replaced for appropriate opcodes.
            InvalidMessageDecoder d = new InvalidMessageDecoder();
            for (int i = 0; i < 255; i++) {
                decoders.add(d);
            }
        }

        public <T extends Message, C extends MessageDecoder<T>> void bindDecoder(Class<C> type)
                throws IllegalAccessException, InstantiationException {
            MessageDecoder<T> decoder = type.newInstance();
            int opcode = decoder.getOpcode();

            if (opcode < 0 || opcode >= 255) {
                return;
            }

            this.decoders.set(opcode, decoder);
            this.decoderMap.put(decoder.getMessageType(), decoder);
        }

        public <T extends Message, C extends MessageEncoder<T>> void bindEncoder(Class<C> type)
                throws IllegalAccessException, InstantiationException {
            MessageEncoder<T> encoder = type.newInstance();
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
    }
}
