package ru.job4j.tracker;

/**
 * Tracker's program start point.
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.03.17
 */
public class StartUI {

    /**
     * Add item constant.
     */
    static final int ADD_NEW_ITEM = 0;

    /**
     * Show items constant.
     */
    static final int SHWOW_ALL_ITEMS = 1;

    /**
     * Edit item constant.
     */
    static final int EDIT_ITEM = 2;

    /**
     * Delete item constant.
     */
    static final int DELETE_ITEM = 3;

    /**
     * Find items by id constant.
     */
    static final int FIND_ITEM_BY_ID = 4;

    /**
     * Find item by name constant.
     */
    static final int FIND_ITEMS_BY_NAME = 5;

    /**
     * Exit constant.
     */
    static final int EXIT = 6;

    /**
     * Any implementation of user's input.
     */
    private Input input;

    /**
     * Menu items.
     */
    private String[] menu = {"0. Add new Item.",
            "1. Show all items.",
            "2. Edit item.",
            "3. Delete item.",
            "4. Find item by Id.",
            "5. Find items by name.",
            "6. Exit Program."};;

    /**
     * Constructor.
     * @param input any input implementation
     */
    private StartUI(Input input) {
        this.input = input;
    }

    /**
     * Initialize the program.
     */
    private void init() {
        Tracker tracker = new Tracker();

        while (true) {
            printMenu();
            int userAnswer = Integer.parseInt(this.input.ask("Please choose a menu item number: "));
            if (userAnswer < ADD_NEW_ITEM || userAnswer > EXIT) {
                System.out.printf("Wrong input. Input should be in range from 0 to %d%n", this.menu.length - 1);
            } else if (userAnswer == EXIT) {
                System.out.println("Bye!");
                break;
            } else if (userAnswer == ADD_NEW_ITEM) {
                Item item = new Item(askItemName());
                tracker.add(item);
            } else if (userAnswer == SHWOW_ALL_ITEMS) {
                System.out.println("Tracker contains items with names: \n");
                printItems(tracker.findAll());
                System.out.println();
            } else if (userAnswer == EDIT_ITEM) {
                Item updatedItem = new Item(askItemName());
                updatedItem.setId(tracker.findById(askItemId()).getId());
                tracker.update(updatedItem);
            } else if (userAnswer == DELETE_ITEM) {
                Item itemForDelete = new Item("");
                itemForDelete.setId(askItemId());
                tracker.delete(itemForDelete);
            } else if (userAnswer == FIND_ITEM_BY_ID) {
                printHeader();
                printItem(tracker.findById(askItemId()));
                System.out.println();
            } else if (userAnswer == FIND_ITEMS_BY_NAME) {
                String itemName = askItemName();
                printItems(tracker.findByName(itemName));
            }
        }
    }

    /**
     * Starts the program.
     * @param args program parameters as a string array
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput()).init();
    }

    /**
     * Prints phrase and return user input.
     * @return string entered by user
     */
    private String askItemId() {
        return this.input.ask("Please enter item Id: \n");
    }

    /**
     * Prints header for an item text representation.
     */
    private void printHeader() {
        System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
    }

    /**
     * Prints an item as table line.
     * @param item item to print
     */
    private void printItem(Item item) {
        System.out.printf("| %20s\t| %40s\t|\n", item.getId(), item.getName());
    }

    /**
     * Prints array of items as a table.
     * @param items items to print
     */
    private void printItems(Item[] items) {
        printHeader();
        for (Item item : items) {
            printItem(item);
        }
    }

    /**
     * Asks user to enter item name and return it as a string.
     * @return item name
     */
    private String askItemName() {
        return this.input.ask("Please enter item name: \n");
    }

    /**
     * Prints menu.
     */
    private void printMenu() {
        for (String menuItem : this.menu) {
            System.out.println(menuItem);
        }
    }
}
