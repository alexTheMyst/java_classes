package ru.job4j.sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple set with improved performance.
 *
 * @param <E> element type
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.09.17
 */
public class SimpleSetImproved<E> implements Iterable<E> {
    /**
     * Buckets.
     */
    private SimpleLinkedSet[] store;
    /**
     * Stores set size.
     */
    private int size;

    /**
     * Constructor.
     *
     * @param bucketsQuantity quantity of buckets
     */
    public SimpleSetImproved(int bucketsQuantity) {
        this.store = new SimpleLinkedSet[bucketsQuantity];
    }

    /**
     * Creates new instance of iterator.
     *
     * @return new iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleSetImprovedIterator();
    }

    /**
     * Adds ne element.
     *
     * @param element element to insert
     */
    public void add(E element) {
        int bucketNumber = element.hashCode() % this.store.length;
        if (this.size == 0 && this.store[bucketNumber] == null) {
            initBuckets();
        }
        int internalSetSize = this.store[bucketNumber].getSize();
        this.store[bucketNumber].add(element);
        if (store[bucketNumber].getSize() > internalSetSize) {
            this.size++;
        }
    }

    /**
     * Size getter.
     *
     * @return size
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Fills store with empty buckets.
     */
    private void initBuckets() {
        for (int i = 0; i < this.store.length; i++) {
            this.store[i] = new SimpleLinkedSet();
        }
    }

    /**
     * Iterator implementation.
     *
     * @param <E> return type
     */
    class SimpleSetImprovedIterator<E> implements Iterator<E> {
        /**
         * Stores current bucket number.
         */
        private int currentBucket = 0;
        /**
         * Stores iterator of bucket.
         */
        private Iterator<E> internalIterator;

        /**
         * Checks that iterator has next element.
         *
         * @return true if iterator has next element or false otherwise
         */
        @Override
        public boolean hasNext() {
            return this.currentBucket < store.length || (this.internalIterator != null && this.internalIterator.hasNext());
        }

        /**
         * Returns next element.
         *
         * @return next element
         */
        @Override
        public E next() {
            emptySetCheck();
            emptyInternalIteratorCheck();
            while (!this.internalIterator.hasNext()) {
                if (currentBucket < size) {
                    this.internalIterator = store[currentBucket++].iterator();
                } else {
                    throw new NoSuchElementException();
                }
            }

            return (E) this.internalIterator.next();
        }

        /**
         * Checks if current set is empty.
         */
        private void emptySetCheck() {
            if (size == 0) {
                throw new NoSuchElementException();
            }
        }

        /**
         * Checks if current internal iterator is null and initialize it.
         */
        private void emptyInternalIteratorCheck() {
            if (this.currentBucket < size && this.internalIterator == null) {
                this.internalIterator = store[currentBucket].iterator();
            }
        }
    }
}
