package org.moparscape.elysium.net.codec;

import org.moparscape.elysium.net.codec.decoder.*;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class CodecLookupService {

    private static final List<MessageDecoder<? extends Message>> decoders;

    private static final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap;

    // Empty constructor to enforce singleton property
    private CodecLookupService() {
    }

    static {
        ImmutableBindingBuilder bindings = new ImmutableBindingBuilder();
        // Initialise all of the decoders in this block
        try {
            bindings.bindDecoder(PlayerMiscMessageDecoders.AppearancesMessageDecoder.class);
            bindings.bindDecoder(PlayerMiscMessageDecoders.AppearanceUpdateMessageDecoder.class);
            bindings.bindDecoder(CombatMessageDecoders.AttackNpcMessageDecoder.class);
            bindings.bindDecoder(CombatMessageDecoders.AttackPlayerMessageDecoder.class);
            bindings.bindDecoder(CombatMessageDecoders.AttackStyleMessageDecoder.class);
            bindings.bindDecoder(BankMessageDecoders.BankCloseMessageDecoder.class);
            bindings.bindDecoder(BankMessageDecoders.BankDepositMessageDecoder.class);
            bindings.bindDecoder(BankMessageDecoders.BankWithdrawMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.BotDetectionMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.CommandMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelAcceptMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelConfirmAcceptMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelDeclineMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelInformationMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelOptionsMessageDecoder.class);
            bindings.bindDecoder(DuelMessageDecoders.DuelRequestMessageDecoder.class);
            //bindings.bindDecoder(MiscMessageDecoders.DummyMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.ExceptionMessageDecoder.class);
            bindings.bindDecoder(MovementMessageDecoders.FollowRequestMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.FriendAddMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.FriendPmMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.FriendRemoveMessageDecoder.class);
            bindings.bindDecoder(PlayerMiscMessageDecoders.GameSettingMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.IgnoreAddMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.IgnoreRemoveMessageDecoder.class);
            bindings.bindDecoder(PlayerMiscMessageDecoders.InventoryActionMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemDoorMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemDropMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemGameObjectMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemGroundItemMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemItemMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemNpcMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemPickupMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemPlayerMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemUnwieldMessageDecoder.class);
            bindings.bindDecoder(ItemMessageDecoders.ItemWieldMessageDecoder.class);
            bindings.bindDecoder(SessionMessageDecoders.LoginMessageDecoder.class);
            bindings.bindDecoder(SessionMessageDecoders.LogoutMessageDecoder.class);
            bindings.bindDecoder(SessionMessageDecoders.LogoutRequestMessageDecoder.class);
            bindings.bindDecoder(PlayerMiscMessageDecoders.MenuActionMessageDecoder.class);
            bindings.bindDecoder(NpcMessageDecoders.NpcActionMessageDecoder.class);
            bindings.bindDecoder(NpcMessageDecoders.NpcChatMessageDecoder.class);
            bindings.bindDecoder(WorldMessageDecoders.ObjectPrimaryActionMessageDecoder.class);
            bindings.bindDecoder(WorldMessageDecoders.ObjectSecondaryActionMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.PingMessageDecoder.class);
            bindings.bindDecoder(CombatMessageDecoders.PrayerActivateMessageDecoder.class);
            bindings.bindDecoder(CombatMessageDecoders.PrayerDeactivateMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.PrivacySettingMessageDecoder.class);
            bindings.bindDecoder(CommunicationMessageDecoders.PublicChatMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.ReportMessageDecoder.class);
            bindings.bindDecoder(SessionMessageDecoders.SessionRequestMessageDecoder.class);
            bindings.bindDecoder(ShopMessageDecoders.ShopBuyMessageDecoder.class);
            bindings.bindDecoder(ShopMessageDecoders.ShopCloseMessageDecoder.class);
            bindings.bindDecoder(ShopMessageDecoders.ShopSellMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.SleepwordMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellDoorMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellGameObjectMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellGroundItemMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellGroundMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellInvItemMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellNpcMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellPlayerMessageDecoder.class);
            bindings.bindDecoder(SpellMessageDecoders.SpellSelfMessageDecoder.class);
            bindings.bindDecoder(TradeMessageDecoders.TradeAcceptMessageDecoder.class);
            bindings.bindDecoder(TradeMessageDecoders.TradeConfirmAcceptMessageDecoder.class);
            bindings.bindDecoder(TradeMessageDecoders.TradeDeclineMessageDecoder.class);
            bindings.bindDecoder(TradeMessageDecoders.TradeInformationMessageDecoder.class);
            bindings.bindDecoder(TradeMessageDecoders.TradeRequestMessageDecoder.class);
            bindings.bindDecoder(MiscMessageDecoders.TrapMessageDecoder.class);
            bindings.bindDecoder(MovementMessageDecoders.WalkMessageDecoder.class);
            bindings.bindDecoder(MovementMessageDecoders.WalkTargetMessageDecoder.class);
            bindings.bindDecoder(WorldMessageDecoders.WallObjectPrimaryActionMessageDecoder.class);
            bindings.bindDecoder(WorldMessageDecoders.WallObjectSecondaryActionMessageDecoder.class);
        } catch (Exception e) {
            System.out.println("Failed to initialise decoders");
            System.out.println(e);
            throw new ExceptionInInitializerError(e);
        }

        decoders = bindings.decoderList();
        decoderMap = bindings.messageToDecoderMap();
    }

    public static MessageDecoder<? extends Message> getDecoder(int opcode) {
        if (opcode < 0 || opcode >= 255) throw new IllegalArgumentException("opcode");

        return decoders.get(opcode);
    }

    public static MessageDecoder<? extends Message> getDecoder(Class<? extends Message> type) {
        return decoderMap.get(type);
    }

    private static final class ImmutableBindingBuilder {

        private final List<MessageDecoder<? extends Message>> decoders =
                new ArrayList<MessageDecoder<? extends Message>>(255);

        private final Map<Class<? extends Message>, MessageDecoder<? extends Message>> decoderMap =
                new HashMap<Class<? extends Message>, MessageDecoder<? extends Message>>(100, 0.50f);

        public ImmutableBindingBuilder() {
            // Add dummy invalid message handlers to the list.
            // These are replaced for appropriate opcodes.
            MiscMessageDecoders decoderWrapper = new MiscMessageDecoders();
            MiscMessageDecoders.InvalidMessageDecoder d = decoderWrapper.new InvalidMessageDecoder();
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

        public Map<Class<? extends Message>, MessageDecoder<? extends Message>> messageToDecoderMap() {
            return Collections.unmodifiableMap(this.decoderMap);
        }

        public List<MessageDecoder<? extends Message>> decoderList() {
            return Collections.unmodifiableList(this.decoders);
        }
    }
}
