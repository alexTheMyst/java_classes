package ru.job4j.orders;

/**
 * Parser abstraction.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.12.2017
 */
public interface FileParser {

    /**
     * Performs parse operation.
     */
    void parseFile();
}
