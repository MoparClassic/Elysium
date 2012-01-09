package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.net.codec.Message;
import org.moparscape.elysium.net.codec.decoder.message.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class HandlerLookupService {

    private static final Map<Class<? extends Message>, MessageHandler<? extends Message>> handlers;

    static {
        ImmutableBindingBuilder bindings = new ImmutableBindingBuilder();
        try {
            // Bind all Message handlers here
            bindings.bind(AppearancesMessage.class, AppearancesMessageHandler.class);
            bindings.bind(AppearanceUpdateMessage.class, AppearanceUpdateMessageHandler.class);
            bindings.bind(AttackNpcMessage.class, AttackNpcMessageHandler.class);
            bindings.bind(AttackPlayerMessage.class, AttackPlayerMessageHandler.class);
            bindings.bind(AttackStyleMessage.class, AttackStyleMessageHandler.class);
            bindings.bind(BankCloseMessage.class, BankCloseMessageHandler.class);
            bindings.bind(BankDepositMessage.class, BankDepositMessageHandler.class);
            bindings.bind(BankWithdrawMessage.class, BankWithdrawMessageHandler.class);
            bindings.bind(BotDetectionMessage.class, BotDetectionMessageHandler.class);
            bindings.bind(CommandMessage.class, CommandMessageHandler.class);
            bindings.bind(DuelAcceptMessage.class, DuelAcceptMessageHandler.class);
            bindings.bind(DuelConfirmAcceptMessage.class, DuelConfirmAcceptMessageHandler.class);
            bindings.bind(DuelDeclineMessage.class, DuelDeclineMessageHandler.class);
            bindings.bind(DuelInformationMessage.class, DuelInformationMessageHandler.class);
            bindings.bind(DuelOptionsMessage.class, DuelOptionsMessageHandler.class);
            bindings.bind(DuelRequestMessage.class, DuelRequestMessageHandler.class);
            bindings.bind(DummyMessage.class, DummyMessageHandler.class);
            bindings.bind(ExceptionMessage.class, ExceptionMessageHandler.class);
            bindings.bind(FollowRequestMessage.class, FollowRequestMessageHandler.class);
            bindings.bind(FriendAddMessage.class, FriendAddMessageHandler.class);
            bindings.bind(FriendPmMessage.class, FriendPmMessageHandler.class);
            bindings.bind(FriendRemoveMessage.class, FriendRemoveMessageHandler.class);
            bindings.bind(GameSettingMessage.class, GameSettingMessageHandler.class);
            bindings.bind(IgnoreAddMessage.class, IgnoreAddMessageHandler.class);
            bindings.bind(IgnoreRemoveMessage.class, IgnoreRemoveMessageHandler.class);
            bindings.bind(InventoryActionMessage.class, InventoryActionMessageHandler.class);
            bindings.bind(ItemDoorMessage.class, ItemDoorMessageHandler.class);
            bindings.bind(ItemDropMessage.class, ItemDropMessageHandler.class);
            bindings.bind(ItemGameObjectMessage.class, ItemGameObjectMessageHandler.class);
            bindings.bind(ItemGroundItemMessage.class, ItemGroundItemMessageHandler.class);
            bindings.bind(ItemItemMessage.class, ItemItemMessageHandler.class);
            bindings.bind(ItemNpcMessage.class, ItemNpcMessageHandler.class);
            bindings.bind(ItemPickupMessage.class, ItemPickupMessageHandler.class);
            bindings.bind(ItemPlayerMessage.class, ItemPlayerMessageHandler.class);
            bindings.bind(ItemUnwieldMessage.class, ItemUnwieldMessageHandler.class);
            bindings.bind(ItemWieldMessage.class, ItemWieldMessageHandler.class);
            bindings.bind(LoginMessage.class, LoginMessageHandler.class);
            bindings.bind(LogoutMessage.class, LogoutMessageHandler.class);
            bindings.bind(LogoutRequestMessage.class, LogoutRequestMessageHandler.class);
            bindings.bind(MenuActionMessage.class, MenuActionMessageHandler.class);
            bindings.bind(NpcActionMessage.class, NpcActionMessageHandler.class);
            bindings.bind(NpcChatMessage.class, NpcChatMessageHandler.class);
            bindings.bind(ObjectPrimaryActionMessage.class, ObjectPrimaryActionMessageHandler.class);
            bindings.bind(ObjectSecondaryActionMessage.class, ObjectSecondaryActionMessageHandler.class);
            bindings.bind(PingMessage.class, PingMessageHandler.class);
            bindings.bind(PrayerActivateMessage.class, PrayerActivateMessageHandler.class);
            bindings.bind(PrayerDeactivateMessage.class, PrayerDeactivateMessageHandler.class);
            bindings.bind(PrivacySettingMessage.class, PrivacySettingMessageHandler.class);
            bindings.bind(PublicChatMessage.class, PublicChatMessageHandler.class);
            bindings.bind(ReportMessage.class, ReportMessageHandler.class);
            bindings.bind(SessionRequestMessage.class, SessionRequestMessageHandler.class);
            bindings.bind(ShopBuyMessage.class, ShopBuyMessageHandler.class);
            bindings.bind(ShopCloseMessage.class, ShopCloseMessageHandler.class);
            bindings.bind(ShopSellMessage.class, ShopSellMessageHandler.class);
            bindings.bind(SleepwordMessage.class, SleepwordMessageHandler.class);
            bindings.bind(SpellDoorMessage.class, SpellDoorMessageHandler.class);
            bindings.bind(SpellGameObjectMessage.class, SpellGameObjectMessageHandler.class);
            bindings.bind(SpellGroundItemMessage.class, SpellGroundItemMessageHandler.class);
            bindings.bind(SpellGroundMessage.class, SpellGroundMessageHandler.class);
            bindings.bind(SpellInvItemMessage.class, SpellInvItemMessageHandler.class);
            bindings.bind(SpellNpcMessage.class, SpellNpcMessageHandler.class);
            bindings.bind(SpellPlayerMessage.class, SpellPlayerMessageHandler.class);
            bindings.bind(SpellSelfMessage.class, SpellSelfMessageHandler.class);
            bindings.bind(TradeAcceptMessage.class, TradeAcceptMessageHandler.class);
            bindings.bind(TradeConfirmAcceptMessage.class, TradeConfirmAcceptMessageHandler.class);
            bindings.bind(TradeDeclineMessage.class, TradeDeclineMessageHandler.class);
            bindings.bind(TradeInformationMessage.class, TradeInformationMessageHandler.class);
            bindings.bind(TradeRequestMessage.class, TradeRequestMessageHandler.class);
            bindings.bind(TrapMessage.class, TrapMessageHandler.class);
            bindings.bind(WalkMessage.class, WalkMessageHandler.class);
            bindings.bind(WalkTargetMessage.class, WalkTargetMessageHandler.class);
            bindings.bind(WallObjectPrimaryActionMessage.class, WallObjectPrimaryActionMessageHandler.class);
            bindings.bind(WallObjectSecondaryActionMessage.class, WallObjectSecondaryActionMessageHandler.class);

            // Assign an immutable copy to the class' handler map
            handlers = bindings.messageToHandlerMap();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Message> MessageHandler<T> getHandler(Class<T> type) {
        return (MessageHandler<T>) handlers.get(type);
    }

    private static final class ImmutableBindingBuilder {

        private final Map<Class<? extends Message>, MessageHandler<? extends Message>> handlers =
                new HashMap<Class<? extends Message>, MessageHandler<? extends Message>>(100, 0.50f);

        public <T extends Message> void bind(Class<T> type, Class<? extends MessageHandler<T>> handlerType)
                throws IllegalAccessException, InstantiationException {
            MessageHandler<T> handler = handlerType.newInstance();
            handlers.put(type, handler);
        }

        public Map<Class<? extends Message>, MessageHandler<? extends Message>> messageToHandlerMap() {
            return Collections.unmodifiableMap(handlers);
        }
    }
}
