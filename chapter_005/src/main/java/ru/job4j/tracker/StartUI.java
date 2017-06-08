package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * MenuTracker instance.
     */
    private final MenuTracker menuTracker;

    /**
     * Constructor.
     * @param tracker implementation
     * @param input implementation
     */
    protected StartUI(Tracker tracker, Input input) {
        this.tracker = tracker;
        this.input = input;
        this.menuTracker = new MenuTracker(this.input, this.tracker);
        this.menuTracker.fillActions();
    }

    /**
     * Getter.
     * @return MenuTracker instance.
     */
    public MenuTracker getMenuTracker() {
        return this.menuTracker;
    }

    /**
     * Initialize the program.
     */
    protected void init() {
        Map<Integer, UserAction> userActions = this.menuTracker.getActions();
        List<Integer> validMenuNumbers = new ArrayList<>();
        for (Map.Entry<Integer, UserAction> userAction : userActions.entrySet()) {
            validMenuNumbers.add(userAction.getValue().key());
        }
        int key;
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
