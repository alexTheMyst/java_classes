package ru.job4j.trees;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Simple binary search tree implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.11.17
 */
public class SimpleBinarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    /**
     * Root node link.
     */
    private Node<E> rootNode;

    /**
     * Default constructor.
     */
    public SimpleBinarySearchTree() {
    }

    /**
     * Adds a child node to given parent node.
     *
     * @param element child node
     * @return true if a node has been added or false otherwise
     */
    public void add(E element) {
        if (this.rootNode == null) {
            this.rootNode = new Node<>(element);
        } else {
            this.rootNode.add(element);
        }
    }

    /**
     * Creates an iterator instance.
     *
     * @return iterator for current tree
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleBinaryTreeIterator<>();
    }

    /**
     * Tree node representation.
     *
     * @param <E> type parameter
     */
    class Node<E extends Comparable<E>> {
        /**
         * Node value.
         */
        E value;
        /**
         * Link to the right child.
         */
        Node<E> rightChildNode;
        /**
         * Link to the left child.
         */
        Node<E> leftChildNode;

        /**
         * Constructor.
         *
         * @param value
         */
        public Node(E value) {
            this.value = value;
        }

        /**
         * Adds value to into the tree.
         *
         * @param value some value
         */
        void add(E value) {
            if (this.value.compareTo(value) < 0) {
                if (this.leftChildNode == null) {
                    this.leftChildNode = new Node<>(value);
                } else {
                    this.leftChildNode.add(value);
                }
            } else {
                if (this.rightChildNode == null) {
                    this.rightChildNode = new Node<>(value);
                } else {
                    this.rightChildNode.add(value);
                }
            }
        }
    }

    /**
     * Simple iterator implementation.
     *
     * @param <E> type parameter
     */
    class SimpleBinaryTreeIterator<E extends Comparable<E>> implements Iterator<E> {
        /**
         * Last returned value.
         */
        private E lastReturned;
        /**
         * Elements to return.
         */
        private Queue<E> elementsToReturn = new LinkedList<>();

        /**
         * Checks that iterator has next element to return.
         *
         * @return true if element exists or false otherwise
         */
        @Override
        public boolean hasNext() {
            return rootNode != null && elementsToReturn.size() > 0;
        }

        /**
         * Gets next value for iterator.
         *
         * @return
         */
        @Override
        public E next() {
            E result;
            if (lastReturned == null && rootNode != null) {
                fillDequeue((Node<E>) rootNode);
                result = getValue();
            } else if (elementsToReturn.size() > 0) {
                result = getValue();
            } else {
                throw new NoSuchElementException();
            }
            return result;
        }

        /**
         * Gets value from queue.
         *
         * @return some value
         */
        private E getValue() {
            E result;
            this.lastReturned = this.elementsToReturn.poll();
            result = this.lastReturned;
            return result;
        }

        /**
         * Fills queue.
         *
         * @param node root node
         */
        private void fillDequeue(Node<E> node) {
            this.elementsToReturn.add(node.value);
            if (node.leftChildNode != null) {
                fillDequeue(node.leftChildNode);
            }
            if (node.rightChildNode != null) {
                fillDequeue(node.rightChildNode);
            }
        }
    }
}
