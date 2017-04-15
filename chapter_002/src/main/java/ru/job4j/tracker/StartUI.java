package ru.job4j.tracker;

/**
 * Tracker's program start point.
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.03.17
 */
public class StartUI {

    /**
     * Exit constant.
     */
    static final int EXIT = 6;

    /**
     * Any implementation of user's input.
     */
    private Input input;

    /**
     * Tracker instance.
     */
    private Tracker tracker;

    /**
     * Constructor.
     * @param tracker implementation
     * @param input implementation
     */
    protected StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Initialize the program.
     */
    protected void init() {
        MenuTracker menuTracker = new MenuTracker(this.input, this.tracker);
        menuTracker.fillActions();
        UserAction[] userActions = menuTracker.getActions();
        int[] validMenuNumbers = new int[userActions.length];
        for (int index = 0; index < userActions.length; index++) {
            validMenuNumbers[index] = userActions[index].key();
        }
        int key = EXIT;
        do {
            menuTracker.showMenu();
            key = input.ask("Please choose a menu item number: ", validMenuNumbers);
            menuTracker.runAction(key);
        } while (key != EXIT);
    }

    /**
     * Starts the program.
     * @param args program parameters as a string array
     */
    public static void main(String[] args) {
        new StartUI(new Tracker(), new ValidateInput()).init();
    }
}
