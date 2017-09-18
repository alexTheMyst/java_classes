package ru.job4j.sets;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Simple set implementation using array.
 *
 * @param <E> set element type
 * @author Alexey Aleshin
 * @version $id$
 * @since 17.09.17
 */
public class SimpleSet<E> implements Iterable<E> {
    /**
     * Default array size.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Element store.
     */
    private Object[] store;

    /**
     * Current amount of stored elements.
     */
    private int size;

    /**
     * Constructor.
     */
    public SimpleSet() {
        this.store = new Object[DEFAULT_SIZE];
        this.size = 0;
    }

    /**
     * Creates an iterator.
     *
     * @return the iterator instance
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleSetIterator();
    }

    /**
     * Adds entity to the set.
     *
     * @param entry some entity
     */
    public void add(E entry) {
        boolean hasCopy = false;
        for (int i = 0; i < this.size; i++) {
            if (this.store[i].equals(entry)) {
                hasCopy = true;
                break;
            }
        }
        if (!hasCopy) {
            this.store[size++] = entry;
        }
        if (this.store.length - 1 == this.size) {
            int increasedLength = this.store.length + (this.store.length >> 1);
            this.store = Arrays.copyOf(this.store, increasedLength);
        }
    }

    /**
     * Returns count of stored elements.
     *
     * @return size value
     */
    public int getSize() {
        return size;
    }

    /**
     * Iterator implementation.
     */
    class SimpleSetIterator implements Iterator<E> {
        /**
         * Index of the last returned element.
         */
        private int lastReturnedIndex = 0;

        /**
         * Checks next elements exists.
         *
         * @return true if next element exists or false otherwise
         */
        @Override
        public boolean hasNext() {
            return this.lastReturnedIndex < size;
        }

        /**
         * Returns next element.
         *
         * @return element
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            return (E) store[lastReturnedIndex++];
        }
    }
}
