package ru.job4j.lists;

import java.util.Iterator;

/**
 * Simple implementation of linked list.
 *
 * @param <E> entity type
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.09.17
 */
public class MyLinkedList<E> implements Iterable<E> {

    /**
     * Links for first and last elements of the list.
     */
    private Node<E> firstElement, lastElement;

    /**
     * Adds the element to the list.
     *
     * @param element some element
     */
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        if (this.firstElement == null && this.lastElement == null) {
            this.firstElement = newNode;
            this.lastElement = newNode;
        } else {
            this.lastElement.nextElement = newNode;
            this.lastElement = newNode;
        }
    }

    /**
     * Returns the element from index list position.
     *
     * @param index some index of element in list
     * @return element
     */
    public E get(int index) {
        int counter = 0;
        E result;
        if (index == 0) {
            result = this.firstElement.value;
        } else {
            Iterator<E> iterator = new MyLinkedListIterator();
            while (counter < index && iterator.hasNext()) {
                iterator.next();
                counter++;
            }
            result = iterator.next();
        }
        return result;
    }

    /**
     * Returns iterator.
     *
     * @return iterator of type E
     */
    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    /**
     * Represents lists element.
     *
     * @param <E> type
     */
    class Node<E> {
        /**
         * Value.
         */
        private final E value;

        /**
         * Next element link.
         */
        private Node<E> nextElement;

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
     * Iterator for simple linked list implementation.
     */
    class MyLinkedListIterator implements Iterator<E> {
        /**
         * Link to current element.
         */
        private Node<E> element;

        /**
         * Constructor.
         */
        MyLinkedListIterator() {
            this.element = firstElement;
        }

        /**
         * Checks that iterator has the next value.
         *
         * @return true if next will return the value or false otherwise
         */
        @Override
        public boolean hasNext() {
            return this.element != null;
        }

        /**
         * Returns next value.
         *
         * @return next value
         */
        @Override
        public E next() {
            E result = this.element.value;
            this.element = this.element.nextElement;
            return result;
        }
    }
}
