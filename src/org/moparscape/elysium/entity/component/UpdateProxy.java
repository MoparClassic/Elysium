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

    private Sprite sprite;

    private Communication communication;

    private Credentials credentials;

    private Movement movement;

    private Observer observer;

    private Skills skills;

    public void resolveDependencies(Map<Class<? extends Component>, Component> components) {
        this.sprite = Sprite.class.cast(components.get(Sprite.class));
        this.communication = Communication.class.cast(components.get(Communication.class));
        this.credentials = Credentials.class.cast(components.get(Credentials.class));
        this.movement = Movement.class.cast(components.get(Movement.class));
        this.observer = Observer.class.cast(components.get(Observer.class));
        this.skills = Skills.class.cast(components.get(Skills.class));
    }

    public void updateEntityLists() {
        observer.updateEntityLists();
    }

    public void clearDisplayLists() {
        observer.clearDisplayLists();
    }

    public void revalidateWatchedEntities() {
        observer.revalidateWatchedEntities();
    }

    public void updateWatchedEntities() {
        observer.updateWatchedEntities();
    }

    public StatefulEntityCollection<GameObject> getWatchedObjects() {
        return observer.getWatchedObjects();
    }

    public StatefulEntityCollection<Item> getWatchedItems() {
        return observer.getWatchedItems();
    }

    public StatefulEntityCollection<Npc> getWatchedNpcs() {
        return observer.getWatchedNpcs();
    }

    public StatefulEntityCollection<Player> getWatchedPlayers() {
        return observer.getWatchedPlayers();
    }

    public Queue<Projectile> getProjectilesNeedingDisplayed() {
        return observer.getProjectilesNeedingDisplayed();
    }

    public Queue<Bubble> getBubblesNeedingDisplayed() {
        return observer.getBubblesNeedingDisplayed();
    }

    public Queue<Npc> getNpcHitUpdates() {
        return observer.getNpcHitUpdates();
    }

    public Queue<Player> getPlayerHitUpdates() {
        return observer.getPlayerHitUpdates();
    }

    public List<Player> getPlayerAppearanceUpdates() {
        return observer.getPlayerAppearanceUpdates();
    }

    public boolean spriteChanged() {
        return sprite.spriteChanged();
    }

    public void resetSpriteChanged() {
        sprite.resetSpriteChanged();
    }

    public void setAppearanceChanged(boolean changed) {
        sprite.setAppearanceChanged(changed);
    }

    public void updateAppearanceId() {
        sprite.updateAppearanceId();
    }

    public int getAppearanceId() {
        return sprite.getAppearanceId();
    }

    public Appearance getAppearance() {
        return sprite.getAppearance();
    }

    public int getSprite() {
        return sprite.getSprite();
    }

    public AtomicIntegerArray getWornItems() {
        return sprite.getWornItems();
    }

    public boolean isSkulled() {
        return sprite.isSkulled();
    }

    public boolean hasMoved() {
        return movement.hasMoved();
    }

    public void resetMoved() {
        movement.resetMoved();
    }

    public void updatePosition() {
        movement.updatePosition();
    }

    public ChatMessage getNextChatMessage() {
        return communication.getNextChatMessage();
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

    public Queue<ChatMessage> getChatMessagesNeedingDisplayed() {
        return communication.getChatMessagesNeedingDisplayed();
    }

    public Queue<ChatMessage> getNpcMessagesNeedingDisplayed() {
        return communication.getNpcMessagesNeedingDisplayed();
    }

    public long getUsernameHash() {
        return credentials.getUsernameHash();
    }

    public int getLastDamage() {
        return skills.getLastDamage();
    }

    public int getHits() {
        return skills.getHits();
    }

    public int getMaxHits() {
        return skills.getMaxHits();
    }

    public int getCombatLevel() {
        return skills.getCombatLevel();
    }
}
