package ru.job4j.tracker;

import java.util.List;

/**
 * Implements stub class with users input.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.04.17
 */
public class StubInput extends ConsoleInput {

    /**
     * Action sequence.
     */
    private List<String> actions;

    /**
     * Constructor.
     * @param actions array of actions
     */
    public StubInput(List<String> actions) {
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
        return this.actions.get(actionIndex++);
    }

    /**
     * Emulates users action.
     * @param question text for user
     * @param range menu items numbers
     * @return chosen item id
     */
    @Override
    public int ask(String question, List<Integer> range) {
        return super.ask(this.actions.get(actionIndex++), range);
    }
}
