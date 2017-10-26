package ru.job4j.trees;

/**
 * Simple tree interface.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.10.17
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Adds new element in the tree.
     *
     * @param parent parent node
     * @param child child node
     * @return true if child added or false otherwise
     */
    boolean add(E parent, E child);
}
