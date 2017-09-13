package ru.job4j.lists;

/**
 * Contains method for lookup loops in linked list.
 *
 * @param <T> linked list payload type
 * @author Alexey Aleshin
 * @version $id$
 * @since 12.09.17
 */
public class LoopFinder<T> {
    /**
     * Link to the first element of linked list.
     */
    private final Node<T> firstElement;

    /**
     * Constructor.
     *
     * @param firstElement first list element
     */
    public LoopFinder(Node<T> firstElement) {
        this.firstElement = firstElement;
    }

    /**
     * Checks that given list has a cycle.
     *
     * @return true if cycle exists or false otherwise
     */
    public boolean hasCycle() {
        boolean result = false;
        Node<T> turtle = this.firstElement;
        Node<T> rabbit = this.firstElement;
        while (rabbit != null && rabbit.getNext() != null) {
            turtle = turtle.getNext();
            rabbit = (rabbit.getNext()).getNext();
            if (rabbit != null && rabbit.equals(turtle)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
