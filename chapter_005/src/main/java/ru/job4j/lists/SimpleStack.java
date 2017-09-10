package ru.job4j.lists;

/**
 * Simple stack implementation.
 *
 * @param <T> type of stack entities
 * @author Alexey Aleshin
 * @version $id$
 * @since 10.09.17
 */
public class SimpleStack<T> {
    /**
     * Store for the stack elements.
     */
    private final MyLinkedList<T> store = new MyLinkedList<>();

    /**
     * Returns the element from the stack.
     *
     * @return first element of the stack.
     */
    public T poll() {
        int elementIndex = this.store.getSize() - 1;
        T result = this.store.get(elementIndex);
        this.store.delete(elementIndex);
        return result;
    }

    /**
     * Puts given element into the stack.
     *
     * @param entity some element.
     */
    public void push(T entity) {
        this.store.add(entity);
    }
}
