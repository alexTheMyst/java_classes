package ru.job4j.sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple set implementation using linked elements structure.
 *
 * @param <E> element type
 * @author Alexey Aleshin
 * @version $id$
 * @since 22.09.17
 */
public class SimpleLinkedSet<E> implements Iterable<E> {
    /**
     * Link to the first element of list.
     */
    private Node<E> firstNode;
    /**
     * Link to the last element of list.
     */
    private Node<E> lastNode;

    /**
     * Adds given element to list.
     *
     * @param elementValue some element
     */
    public void add(E elementValue) {
        Node<E> newNode = new Node<>(elementValue);
        if (firstNode == null) {
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            if (!isCopy(elementValue)) {
                this.lastNode.next = newNode;
                this.lastNode = newNode;
            }
        }
    }

    /**
     * Checks that given element has a copy in list.
     *
     * @param elementValue some element
     * @return true if entity already exists in list or false otherwise
     */
    private boolean isCopy(E elementValue) {
        boolean result = false;
        Iterator<E> iterator = this.iterator();
        while (iterator.hasNext()) {
            E elementFromList = iterator.next();
            if (elementFromList.equals(elementValue)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Creates and returns iterator.
     *
     * @return iterator instance
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedSetIterator();
    }

    /**
     * Element of list.
     *
     * @param <E> element type
     */
    class Node<E> {
        /**
         * Value.
         */
        private final E value;
        /**
         * Next node link.
         */
        private Node<E> next;

        /**
         * Constructor.
         *
         * @param value some value
         */
        Node(E value) {
            this.value = value;
        }
    }

    /**
     * Simple iterator implementation.
     */
    class SimpleLinkedSetIterator implements Iterator<E> {
        /**
         * Link last returned element of list.
         */
        private Node<E> nodeToReturn = firstNode;

        /**
         * Checks that iterator has next element.
         *
         * @return true if next element exists or false otherwise
         */
        @Override
        public boolean hasNext() {
            return (this.nodeToReturn != null);
        }

        /**
         * Returns next element.
         *
         * @return element
         */
        @Override
        public E next() {
            checkEmptyList();
            if (this.nodeToReturn == null) {
                throw new NoSuchElementException();
            } else {
                E result = this.nodeToReturn.value;
                this.nodeToReturn = this.nodeToReturn.next;
                return result;
            }

        }

        /**
         * Checks is list empty.
         */
        private void checkEmptyList() {
            if (firstNode == null) {
                throw new NoSuchElementException();
            }
        }
    }
}
