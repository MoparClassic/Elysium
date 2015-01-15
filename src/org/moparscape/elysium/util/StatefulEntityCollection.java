package org.moparscape.elysium.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 *
 * @author lothy
 */
public final class StatefulEntityCollection<T> {

    private Set<T> entitiesToRemove = new HashSet<T>();
    private Set<T> knownEntities = new HashSet<T>();
    private Set<T> newEntities = new HashSet<T>();

    // We need to keep these in the order they logged in, currently it doesn't seem to?

    public void add(T entity) {
        newEntities.add(entity);
    }

    public void add(Collection<T> entities) {
        newEntities.addAll(entities);
    }

    public boolean changed() {
        return !entitiesToRemove.isEmpty() || !newEntities.isEmpty();
    }

    public boolean contains(T entity) {
        return newEntities.contains(entity) || knownEntities.contains(entity);
    }

    public Collection<T> getAllEntities() {
        Set<T> temp = new HashSet<T>();
        temp.addAll(newEntities);
        temp.addAll(knownEntities);
        return temp;
    }

    public Collection<T> getKnownEntities() {
        return knownEntities;
    }

    public Collection<T> getNewEntities() {
        return newEntities;
    }

    public Collection<T> getRemovingEntities() {
        return entitiesToRemove;
    }

    public boolean isRemoving(T entity) {
        return entitiesToRemove.contains(entity);
    }

    public void remove(T entity) {
        entitiesToRemove.add(entity);
    }

    public void update() {
        knownEntities.removeAll(entitiesToRemove);
        knownEntities.addAll(newEntities);
        newEntities.clear();
        entitiesToRemove.clear();
    }
}
