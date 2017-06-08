package ru.job4j.tracker;

import java.util.List;

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

    /**
     * Ask a question with additional parameter.
     * @param question text for user
     * @param range menu items numbers
     * @return chosen item id
     */
    int ask(String question, List<Integer> range);

}
