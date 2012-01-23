package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.ChatMessage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Communication extends AbstractComponent {

    public static final int MAX_FRIENDS = 100;

    public static final int MAX_IGNORES = 50;

    private final Queue<ChatMessage> messagesToDisplay = new LinkedBlockingQueue<ChatMessage>();

    private final Queue<ChatMessage> npcMessagesToDisplay = new LinkedList<ChatMessage>();

    private final Queue<ChatMessage> messages = new LinkedList<ChatMessage>();

    private final List<Long> ignores = new ArrayList<Long>(MAX_IGNORES);

    private final List<Long> friends = new ArrayList<Long>(MAX_FRIENDS);

    public void informOfChatMessage(ChatMessage message) {
        messagesToDisplay.add(message);
    }

    public boolean addFriend(long usernameHash) {
        if (!friends.contains(usernameHash) && friends.size() < MAX_FRIENDS) {
            return friends.add(usernameHash);
        }

        return false;
    }

    public boolean addIgnore(long usernameHash) {
        if (!ignores.contains(usernameHash) && ignores.size() < MAX_IGNORES) {
            return ignores.add(usernameHash);
        }

        return false;
    }

    public void removeFriend(long usernameHash) {
        friends.remove(usernameHash);
    }

    public void removeIgnore(long usernameHash) {
        ignores.remove(usernameHash);
    }

    public boolean isFriendsWith(long usernameHash) {
        return friends.contains(usernameHash);
    }

    public boolean isIgnoring(long usernameHash) {
        return ignores.contains(usernameHash);
    }

    public List<Long> getFriendList() {
        return friends;
    }

    public List<Long> getIgnoreList() {
        return ignores;
    }

    public ChatMessage getNextChatMessage() {
        return messages.poll();
    }

    public Queue<ChatMessage> getChatMessagesNeedingDisplayed() {
        return messagesToDisplay;
    }

    public Queue<ChatMessage> getNpcMessagesNeedingDisplayed() {
        return npcMessagesToDisplay;
    }

    public void clearChatMessagesNeedingDisplayed() {
        messagesToDisplay.clear();
    }

    public void clearNpcMessagesNeedingDisplayed() {
        npcMessagesToDisplay.clear();
    }
}
