package org.moparscape.elysium.net.handler;

import org.moparscape.elysium.net.codec.Message;
import org.moparscape.elysium.net.codec.decoder.message.AppearancesMessage;

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
            bindings.bind(AppearancesMessage.class, null);

            handlers = bindings.messageToHandlerMap();
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
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
