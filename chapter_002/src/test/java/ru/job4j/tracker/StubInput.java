package ru.job4j.tracker;

/**
 * Implements stub class with users input.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.04.17
 */
public class StubInput implements Input {

    /**
     * Action sequence.
     */
    private String[] actions;

    public StubInput(String[] actions) {
        this.actions = actions;
    }

    /**
     * Actions index.
     */
    private int actionIndex = 0;

    /**
     * Emulates users action.
     * @param question text as a String
     * @return
     */
    @Override
    public String ask(String question) {
        System.out.println(question);
        return this.actions[actionIndex++];
    }
}
