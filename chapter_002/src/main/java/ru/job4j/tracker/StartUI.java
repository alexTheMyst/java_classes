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
        MenuTracker menuTracker = new MenuTracker(input, tracker);
        menuTracker.fillActions();
        int key = EXIT;
        do {
            menuTracker.showMenu();
            key = Integer.parseInt(input.ask("Please choose a menu item number: "));
            menuTracker.runAction(key);
        } while (key != EXIT);
    }

    /**
     * Starts the program.
     * @param args program parameters as a string array
     */
    public static void main(String[] args) {
        new StartUI(new Tracker(), new ConsoleInput()).init();
    }

}
