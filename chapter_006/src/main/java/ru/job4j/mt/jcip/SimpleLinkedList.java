package ru.job4j.mt.jcip;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

/**
 * Simple implementation of linked list.
 *
 * @param <E> entity type
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.09.17
 */
@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {

    /**
     * Links for first and last elements of the list.
     */
    private Node<E> firstElement, lastElement;

    /**
     * Stores size of the list.
     */
    @GuardedBy("this")
    private int size;

    /**
     * Adds the element to the list.
     *
     * @param element some element
     */
    public void add(E element) {
        synchronized (this) {
            Node<E> newNode = new Node<>(element);
            if (this.firstElement == null && this.lastElement == null) {
                this.firstElement = newNode;
                this.lastElement = newNode;
            } else {
                this.lastElement.nextElement = newNode;
                this.lastElement = newNode;
            }
            this.size++;
        }
    }

    /**
     * Returns the element from index list position.
     *
     * @param index some index of element in list
     * @return element
     */
    public E get(int index) {
        synchronized (this) {
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
     * Size getter.
     *
     * @return the size variable
     */
    public int getSize() {
        synchronized (this) {
            return this.size;
        }
    }


    /**
     * Deletes the element with given index.
     *
     * @param index element index
     */
    public void delete(int index) {
        synchronized (this) {
            if (index == 0 && this.size == 1) {
                this.firstElement = null;
                this.lastElement = null;
                this.size--;
            } else if (index < this.size) {
                int counter = 0;
                Node<E> previousEntity = null;
                Node<E> currentEntity = this.firstElement;
                Node<E> nextEntity = currentEntity.nextElement;
                while (counter < this.size - 1 && counter != index) {
                    previousEntity = currentEntity;
                    currentEntity = nextEntity;
                    nextEntity = currentEntity.nextElement;
                    counter++;
                }
                if (previousEntity == null) {
                    this.firstElement = nextEntity;

                }
                if (nextEntity == null) {
                    this.lastElement = previousEntity;
                }
                if (previousEntity != null && nextEntity != null) {
                    previousEntity.nextElement = nextEntity;
                }
                size--;
            }
        }
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
