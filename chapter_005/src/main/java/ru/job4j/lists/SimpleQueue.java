package ru.job4j.lists;

/**
 * Simple queue implementation.
 *
 * @param <T> queue element type parameter
 * @author Alexey Aleshin
 * @version $id$
 * @since 10.09.17
 */
public class SimpleQueue<T> {
    /**
     * Store for the queue elements.
     */
    private final SimpleLinkedList<T> store = new SimpleLinkedList<>();

    /**
     * Returns the element from the queue.
     *
     * @return element from queue tail.
     */
    public T poll() {
        T result = this.store.get(0);
        this.store.delete(0);
        return result;
    }

    /**
     * Puts given element into the queue.
     *
     * @param value some element.
     */
    public void push(T value) {
        this.store.add(value);
    }
}
