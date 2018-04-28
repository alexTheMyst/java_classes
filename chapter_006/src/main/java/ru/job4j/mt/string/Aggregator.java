package ru.job4j.mt.string;

/**
 * Aggregator interface.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 28.04.2018
 */
public interface Aggregator {
    /**
     * Adds an integer to aggregator.
     *
     * @param someInt integer
     */
    void addInt(int someInt);

    /**
     * Gets all added integers as a string.
     *
     * @return string
     */
    String getCurrentString();
}
