package ru.job4j.tracker;

/**
 * Common action implementation.
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.04.17
 */
public abstract class BaseAction implements UserAction {

    /**
     * Action name.
     */
    private String name;

    /**
     * Constructor.
     * @param name of action
     */
    public BaseAction(String name) {
        this.name = name;
    }

    /**
     * Gets an instance key.
     * @return some key
     */
    @Override
    public abstract int key();

    /**
     * Executes come action.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Generates String with action info.
     * @return action info as a String
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), this.name);
    }
}
