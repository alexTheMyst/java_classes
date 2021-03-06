package ru.job4j.mt.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Simple implementation of array list.
 *
 * @param <E> entity type
 * @author Alexey Aleshin
 * @version $id$
 * @since 31.08.17
 */
@ThreadSafe
public class SimpleArrayList<E> implements Iterable<E> {
    /**
     * Default array size.
     */
    private static final int DEFAULT_SIZE = 10;

    /**
     * Storage.
     */
    @GuardedBy("this")
    private Object[] array;

    /**
     * Max index.
     */
    @GuardedBy("this")
    private int lastUsedElementIndex = 0;

    /**
     * Constructor.
     */
    public SimpleArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    /**
     * Adds an element to the array.
     *
     * @param element some element
     */
    void add(E element) {
        synchronized (this) {
            this.array[lastUsedElementIndex++] = element;
            if (this.array.length - 1 == this.lastUsedElementIndex) {
                int increasedLength = this.array.length + (this.array.length >> 1);
                this.array = Arrays.copyOf(this.array, increasedLength);
            }
        }
    }

    /**
     * Creates iterator.
     *
     * @return new MyArrayListIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    /**
     * Gets an element by index.
     *
     * @param index index of element
     * @return element
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        synchronized (this) {
            return (E) this.array[index];
        }
    }

    /**
     * Simple iterator implementation.
     */
    private class MyArrayListIterator implements Iterator<E> {
        /**
         * Index of last returned element.
         */
        private int lastReturnedElementIndex = 0;

        /**
         * Default constructor.
         */
        MyArrayListIterator() {
        }

        /**
         * Checks that iterator has next element.
         *
         * @return true if iterator has or false otherwise
         */
        @Override
        public boolean hasNext() {
            synchronized (SimpleArrayList.this) {
                return this.lastReturnedElementIndex < lastUsedElementIndex;
            }
        }

        /**
         * Returns next element of the iterator.
         *
         * @return next element
         */
        @SuppressWarnings("unchecked")
        @Override
        public E next() {
            synchronized (SimpleArrayList.this) {
                return (E) array[lastReturnedElementIndex++];
            }
        }
    }
}
