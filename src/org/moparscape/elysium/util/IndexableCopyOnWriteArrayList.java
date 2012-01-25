package org.moparscape.elysium.util;

import org.moparscape.elysium.entity.Indexable;

import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Modified version of the JDK's CopyOnWriteArrayList.
 *
 * @author lothy
 */
public final class IndexableCopyOnWriteArrayList<E extends Indexable> implements List<E>, RandomAccess {

    private transient final ReentrantLock lock = new ReentrantLock();

    private transient volatile Object[] array;
    private final transient Object[] arrayMap; // Not 100% sure if this is completely thread-safe
    private transient int mapIndex = 0;

    private final int capacity;

    public IndexableCopyOnWriteArrayList(int capacity) {
        this.capacity = capacity;
        this.array = new Object[0];
        this.arrayMap = new Object[capacity];
    }

    public boolean add(E e) {
        // Reject null values
        if (e == null) {
            return false;
        }

        lock.lock();
        try {
            Object[] elements = array;
            int len = elements.length;

            // If the list is full then return false to indicate failure
            if (len == capacity) {
                return false;
            }

            // First add the entity to the iterable array
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            array = newElements;

            // Now add the entity to the mapping array
            for (; mapIndex < capacity; mapIndex++) {
                if (arrayMap[mapIndex] == null) {
                    arrayMap[mapIndex] = e;
                    e.setIndex(mapIndex);
                    break;
                }
            }

            return true;
        } finally {
            lock.unlock();
        }
    }

    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        array = new Object[0];

        lock.lock();
        try {
            for (int i = 0; i < arrayMap.length; i++) {
                arrayMap[i] = null;
            }

            mapIndex = 0;
        } finally {
            lock.unlock();
        }
    }

    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public List<Iterable<E>> divide(int partitions) {
        Object[] elements = array;
        int len = elements.length;
        int partlen = len / partitions;
        int remaining = len % partitions;

        List<Iterable<E>> iteratorList = new ArrayList<Iterable<E>>();
        if (partitions == 1) {
            iteratorList.add(new COWIterable<E>(elements, 0, len));
            return iteratorList;
        }

        for (int i = 0; i < partitions; i++) {
            int start = i * partlen;
            int sz = start + partlen;
            if (i == (partitions - 1)) {
                iteratorList.add(new COWIterable<E>(elements, start, sz + remaining));
            } else {
                iteratorList.add(new COWIterable<E>(elements, start, sz));
            }
        }

        return iteratorList;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (o == null || !(o instanceof IndexableCopyOnWriteArrayList)) {
            return false;
        }

        IndexableCopyOnWriteArrayList<?> other = (IndexableCopyOnWriteArrayList) o;
        Iterator<?> it = other.iterator();
        Object[] elements = array;
        int len = array.length;

        // If the other list has fewer elements, or any element is not equal to
        // the corresponding element in this list then return false
        for (int i = 0; i < len; i++) {
            if (!it.hasNext() || !elements[i].equals(it.next())) {
                return false;
            }
        }

        // If the other list has more elements then return false
        if (it.hasNext()) {
            return false;
        }

        // Same length list, all elements equal
        return true;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) arrayMap[index];
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        Object[] elements = array;
        int len = elements.length;

        for (int i = 0; i < len; i++) {
            Object o = elements[i];
            hashCode = 31 * hashCode + o.hashCode();
        }

        return hashCode;
    }

    public int indexOf(Object o) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean isEmpty() {
        return array.length == 0;
    }

    public Iterator<E> iterator() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        lock.lock();
        try {
            Object[] elements = array;
            Object old = elements[index];
            int len = elements.length;
            int moved = len - index - 1;

            // First remove the entity from the iterable array
            if (moved == 0) {
                array = Arrays.copyOf(elements, len - 1);
            } else {
                Object[] newElements = new Object[len - 1];
                System.arraycopy(elements, 0, newElements, 0, index);
                System.arraycopy(elements, index + 1, newElements, index, moved);
                array = newElements;
            }

            // Next remove it from the mapping array
            arrayMap[index] = null;

            // Finally, adjust the map index so that it can use lower indexes
            // in the mapping array before higher indexes
            if (index < mapIndex) {
                mapIndex = index;
            }

            return (E) old;
        } finally {
            lock.unlock();
        }
    }

    public boolean remove(Object o) {
        if (o == null) {
            return false;
        }

        lock.lock();
        try {
            Object[] elements = array;
            int len = elements.length;

            if (len != 0) {
                // Copy while searching for element to remove
                // This wins in the normal case of element being present
                int newlen = len - 1;
                Object[] newElements = new Object[newlen];

                for (int i = 0; i < newlen; ++i) {
                    if (o.equals(elements[i])) {
                        // found one;  copy remaining and exit
                        //noinspection ManualArrayCopy
                        for (int k = i + 1; k < len; ++k) {
                            newElements[k - 1] = elements[k];
                        }

                        // Adjust the map index so that lower indexes in the mapping
                        // array can be used before higher indexes
                        if (i < mapIndex) {
                            mapIndex = i;
                        }

                        array = newElements;
                        return true;
                    } else {
                        newElements[i] = elements[i];
                    }
                }

                // special handling for last cell
                if (o.equals(elements[newlen])) {
                    array = newElements;
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    public int size() {
        return array.length;
    }

    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    private static class COWIterator<E extends Indexable> implements Iterator<E> {

        private final Object[] snapshot;

        private final int len;

        private int cursor;

        public COWIterator(Object[] elements, int initialCursor, int len) {
            this.snapshot = elements;
            this.cursor = initialCursor;
            this.len = len;
        }

        public boolean hasNext() {
            return cursor < len;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            return (E) snapshot[cursor++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static class COWIterable<E extends Indexable> implements Iterable<E> {

        private final Object[] snapshot;

        private final int initialCursor;

        private final int len;

        public COWIterable(Object[] elements, int initialCursor, int len) {
            this.snapshot = elements;
            this.initialCursor = initialCursor;
            this.len = len;
        }

        public Iterator<E> iterator() {
            return new COWIterator<E>(snapshot, initialCursor, len);
        }
    }
}
