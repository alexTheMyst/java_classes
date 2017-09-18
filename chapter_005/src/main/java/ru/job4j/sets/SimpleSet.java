package ru.job4j.sets;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
        if (!hasCopy(entry)) {
            this.store[size++] = entry;
        }
        if (this.store.length - 1 == this.size) {
            increaseStoreSize();
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
     * Increases size of the store.
     */
    private void increaseStoreSize() {
        int increasedLength = this.store.length + (this.store.length >> 1);
        this.store = Arrays.copyOf(this.store, increasedLength);
    }

    /**
     * Checks that set already has such element.
     *
     * @param entry some entry
     * @return true if set already has such element or false otherwise
     */
    private boolean hasCopy(E entry) {
        boolean hasCopy = false;
        for (int i = 0; i < this.size; i++) {
            if (this.store[i].equals(entry)) {
                hasCopy = true;
                break;
            }
        }
        return hasCopy;
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
            emptySetCheck();
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
            E result;
            emptySetCheck();
            try {
                result = (E) store[lastReturnedIndex++];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            return result;
        }

        /**
         * Checks that set is empty.
         */
        private void emptySetCheck() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
        }
    }
}
