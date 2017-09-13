package ru.job4j.lists;

/**
 * List node.
 *
 * @param <T> type parameter
 * @author Alexey Aleshin
 * @version $id$
 * @since 12.09.17
 */
public class Node<T> {
    /**
     * Value store.
     */
    private T value;

    /**
     * Next node link.
     */
    private Node<T> next;

    /**
     * Constructor.
     *
     * @param value new node value
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Value getter.
     *
     * @return value
     */
    public T getValue() {
        return value;
    }

    /**
     * Next node link getter.
     *
     * @return next node link
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Next node link setter.
     *
     * @param next next node link
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
