package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.ChatMessage;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class Communication extends AbstractComponent {

    public static final int MAX_FRIENDS = 100;

    public static final int MAX_IGNORES = 50;

    private final Queue<ChatMessage> messagesToDisplay = new ConcurrentLinkedQueue<ChatMessage>();

    private final Queue<ChatMessage> npcMessagesToDisplay = new ConcurrentLinkedQueue<ChatMessage>();

    private final Queue<ChatMessage> messages = new ConcurrentLinkedQueue<ChatMessage>();

    /**
     * This should be fine as a standard linked list as the only time it is
     * modified is when a player explicitly adds another player to the list.
     * This means that only one thread (the thread handling the incoming
     * packet from the player) will modify this list at a time.
     */
    private final Queue<Long> ignores = new LinkedList<Long>();

    /**
     * This should be fine as a standard linked list as the only time it is
     * modified is when a player explicitly adds another player to the list.
     * This means that only one thread (the thread handling the incoming
     * packet from the player) will modify this list at a time.
     */
    private final Queue<Long> friends = new LinkedList<Long>();

    public void addChatMessage(ChatMessage message) {
        messages.offer(message);
    }

    public void informOfChatMessage(ChatMessage message) {
        messagesToDisplay.offer(message); // Use offer, it doesn't block
    }

    public void informOfNpcChatMessage(ChatMessage message) {
        npcMessagesToDisplay.offer(message);
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

    public Queue<Long> getFriendList() {
        return friends;
    }

    public Queue<Long> getIgnoreList() {
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
