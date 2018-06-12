package ru.job4j.tracker;

/**
 * Abstraction for user action.
 * @author Alexey Aleshin
 * @version $id$
 * @since 09.04.17
 */
public interface UserAction {
    /**
     * Generates the key.
     * @return key
     */
    int key();
    /**
     * Executes action.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    void execute(Input input, Tracker tracker);
    /**
     * Creates menu string.
     * @return string.
     */
    String info();
}
