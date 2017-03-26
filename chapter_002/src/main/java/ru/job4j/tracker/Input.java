package ru.job4j.tracker;

/**
 * Declares common methods for user input.
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.03.17
 */
public interface Input {

    /**
     * Ask a question.
     * @param question text as a String
     * @return user input
     */
    String ask(String question);

}
