package org.moparscape.elysium.entity.component;

import org.moparscape.elysium.entity.*;
import org.moparscape.elysium.util.StatefulEntityCollection;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class UpdateProxy extends AbstractComponent {

    private Communication communication;
    private Credentials credentials;
    private Movement movement;
    private Observer observer;
    private Skills skills;
    private Sprite sprite;

    public void clearChatLists() {
        communication.clearChatMessagesNeedingDisplayed();
        communication.clearNpcMessagesNeedingDisplayed();
    }

    public void clearDisplayLists() {
        observer.clearDisplayLists();
    }

    public Appearance getAppearance() {
        return sprite.getAppearance();
    }

    public int getAppearanceId() {
        return sprite.getAppearanceId();
    }

    public Queue<Bubble> getBubblesNeedingDisplayed() {
        return observer.getBubblesNeedingDisplayed();
    }

    public Queue<ChatMessage> getChatMessagesNeedingDisplayed() {
        return communication.getChatMessagesNeedingDisplayed();
    }

    public int getCombatLevel() {
        return skills.getCombatLevel();
    }

    public int getHits() {
        return skills.getHits();
    }

    public int getLastDamage() {
        return skills.getLastDamage();
    }

    public int getMaxHits() {
        return skills.getMaxHits();
    }

    public ChatMessage getNextChatMessage() {
        return communication.getNextChatMessage();
    }

    public Queue<Npc> getNpcHitUpdates() {
        return observer.getNpcHitUpdates();
    }

    public Queue<ChatMessage> getNpcMessagesNeedingDisplayed() {
        return communication.getNpcMessagesNeedingDisplayed();
    }

    public List<Player> getPlayerAppearanceUpdates() {
        return observer.getPlayerAppearanceUpdates();
    }

    public Queue<Player> getPlayerHitUpdates() {
        return observer.getPlayerHitUpdates();
    }

    public Queue<Projectile> getProjectilesNeedingDisplayed() {
        return observer.getProjectilesNeedingDisplayed();
    }

    public int getSprite() {
        return sprite.getSprite();
    }

    public String getUsername() {
        return credentials.getUsername();
    }

    public long getUsernameHash() {
        return credentials.getUsernameHash();
    }

    public StatefulEntityCollection<Item> getWatchedItems() {
        return observer.getWatchedItems();
    }

    public StatefulEntityCollection<Npc> getWatchedNpcs() {
        return observer.getWatchedNpcs();
    }

    public StatefulEntityCollection<GameObject> getWatchedObjects() {
        return observer.getWatchedObjects();
    }

    public StatefulEntityCollection<Player> getWatchedPlayers() {
        return observer.getWatchedPlayers();
    }

    public AtomicIntegerArray getWornItems() {
        return sprite.getWornItems();
    }

    public boolean hasMoved() {
        return movement.hasMoved();
    }

    public void informOfChatMessage(ChatMessage message) {
        communication.informOfChatMessage(message);
    }

    public boolean isFriendsWith(long usernameHash) {
        return communication.isFriendsWith(usernameHash);
    }

    public boolean isIgnoring(long usernameHash) {
        return communication.isIgnoring(usernameHash);
    }

    public boolean isSkulled() {
        return sprite.isSkulled();
    }

    public void resetMoved() {
        movement.resetMoved();
    }

    public void resetSpriteChanged() {
        sprite.resetSpriteChanged();
    }

    public void resolveDependencies(Map<Class<? extends Component>, Component> components) {
        this.sprite = Sprite.class.cast(components.get(Sprite.class));
        this.communication = Communication.class.cast(components.get(Communication.class));
        this.credentials = Credentials.class.cast(components.get(Credentials.class));
        this.movement = Movement.class.cast(components.get(Movement.class));
        this.observer = Observer.class.cast(components.get(Observer.class));
        this.skills = Skills.class.cast(components.get(Skills.class));
    }

    public void revalidateWatchedEntities() {
        observer.revalidateWatchedEntities();
    }

    public void setAppearanceChanged(boolean changed) {
        sprite.setAppearanceChanged(changed);
    }

    public boolean spriteChanged() {
        return sprite.spriteChanged();
    }

    public void updateAppearanceId() {
        sprite.updateAppearanceId();
    }

    public void updateEntityLists() {
        observer.updateEntityLists();
    }

    public void updatePosition() {
        movement.updatePosition();
    }

    public void updateWatchedEntities() {
        observer.updateWatchedEntities();
    }
}
