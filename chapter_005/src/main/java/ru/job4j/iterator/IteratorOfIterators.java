package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class contains method which returns iterator for nested iterators.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.07.17
 */
public class IteratorOfIterators {

    /**
     * Convert nested iterators into the iterator.
     *
     * @param iterators iterator of iterators
     * @return iterator of integers
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> iterators) {
        return new SomeIntegerIterator(iterators);
    }

    /**
     * Implements iterator.
     */
    private class SomeIntegerIterator implements Iterator<Integer> {

        /**
         * Given iterator.
         */
        private final Iterator<Iterator<Integer>> iterator;

        /**
         * Nested iterator.
         */
        private Iterator<Integer> intIterator;

        /**
         * Constructor.
         *
         * @param externalIterator iterator of iterators
         */
        SomeIntegerIterator(Iterator<Iterator<Integer>> externalIterator) {
            this.iterator = externalIterator;
        }

        /**
         * Checks that iterator has a next element.
         *
         * @return true if the next element exists or false otherwise
         */
        @Override
        public boolean hasNext() {
            if (this.intIterator == null) {
                this.intIterator = this.iterator.next();
            }
            return iterator.hasNext() || intIterator.hasNext();
        }

        /**
         * Gets the next element.
         *
         * @return the next element
         */
        @Override
        public Integer next() {
            Integer result;
            if (intIterator.hasNext()) {
                result = intIterator.next();
            } else if (iterator.hasNext()) {
                intIterator = iterator.next();
                result = intIterator.next();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }
    }
}
