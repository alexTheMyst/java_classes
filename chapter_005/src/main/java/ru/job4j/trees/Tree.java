package ru.job4j.trees;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * SimpleTree implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.10.17
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    /**
     * Link to the tree root.
     */
    private Node<E> treeRoot;

    /**
     * Adds new element in the tree.
     *
     * @param parent parent node
     * @param child child node
     * @return true if child added or false otherwise
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (parent == null && this.treeRoot == null) {
            this.treeRoot = createNode(child);
            result = true;
        } else if (!isValueExists(child)) {
            Node<E> parentNode = findParentNode(parent);
            Node<E> childNode = createNode(child);
            parentNode.children.add(childNode);
            result = true;
        }
        return result;
    }

    /**
     * Search for node value equals given node value.
     *
     * @param value some node
     * @return link to node with the same value
     */
    private Node<E> findParentNode(E value) {
        Node<E> result = new Node<>();
        if (isNodeHasSameValue(this.treeRoot, value)) {
            result = this.treeRoot;
        } else {
            for (Node<E> node : this.treeRoot.children) {
                if (isNodeHasSameValue(node, value)) {
                    result = node;
                }
            }
        }
        return result;
    }

    /**
     * Checks variable existence.
     *
     * @param child a child Node to check
     * @return true if node already exists or false otherwise
     */
    private boolean isValueExists(E child) {
        boolean result = false;
        for (E e : this) {
            if (child.equals(e)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Creates new iterator instance.
     *
     * @return link to iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new SimpleTreeIterator();
    }

    /**
     * Tree node representation.
     *
     * @param <T> type parameter
     */
    class Node<T> {
        List<Node<T>> children;
        T value;

    }

    /**
     * Creates new node instance.
     *
     * @param value node value
     * @return link to the new node
     */
    private Node<E> createNode(E value) {
        Node<E> node = new Node<>();
        node.value = value;
        node.children = new LinkedList<>();
        return node;
    }

    private boolean isNodeHasSameValue(Node<E> node, E value) {
        return node.value.equals(value);
    }


    /**
     * Iterator implementation.
     */
    private class SimpleTreeIterator implements Iterator<E> {

        /**
         * Holds tree values.
         */
        private final List<E> values = new LinkedList<>();

        /**
         * Values list iterator.
         */
        private final Iterator<E> internalIterator = createInternalListIterator();

        /**
         * Checks that iterator has next value.
         *
         * @return true if iterator has new value or false otherwise
         */
        @Override
        public boolean hasNext() {
            return internalIterator.hasNext();
        }

        /**
         * Next iterator element.
         *
         * @return next element
         */
        @Override
        public E next() {
            return internalIterator.next();
        }

        /**
         * Creates iterator.
         *
         * @return iterator for values list
         */
        private Iterator<E> createInternalListIterator() {
            if (treeRoot != null) {
                fillInternalList(treeRoot);
            }
            return this.values.iterator();
        }

        /**
         * Adds tree node to values list.
         *
         * @param node some node
         */
        private void fillInternalList(Node<E> node) {
            this.values.add(node.value);
            node.children.forEach(this::fillInternalList);
        }
    }
}