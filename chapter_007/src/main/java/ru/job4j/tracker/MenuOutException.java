package ru.job4j.tracker;

/**
 * Shows that item not in the menu scope.
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.04.17
 */
public class MenuOutException extends RuntimeException {

    /**
     * Adds a message.
     * @param message given message
     */
    public MenuOutException(String message) {
        super(message);
    }
}
