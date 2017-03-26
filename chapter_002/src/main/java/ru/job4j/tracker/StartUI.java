package ru.job4j.tracker;

/**
 * Tracker's program start point.
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.03.17
 */
public class StartUI {

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
     * Initialize program.
     */
    private void init() {
        Tracker tracker = new Tracker();

        while (true) {
            printMenu();
            int menuItemNumber = Integer.parseInt(input.ask("Please choose a menu item number: "));
            if (menuItemNumber < 0 || menuItemNumber > 6) {
                System.out.printf("Wrong input. Input should be in range from 0 to %d%n", menu.length - 1);
            } else if (menuItemNumber == 6) {
                System.out.println("Bye!");
                break;
            } else if (menuItemNumber == 0) {
                String itemName = askItemName();
                Item item = new Item(itemName);
                tracker.add(item);
            } else if (menuItemNumber == 1) {
                Item[] items = tracker.findAll();
                System.out.println("Tracker contains items with names: \n");
                printHeader();
                printItems(items);
                System.out.println();
            } else if (menuItemNumber == 2) {
                String idForUpdate = tracker.findById(askItemId()).getId();
                Item updatedItem = new Item(askItemName());
                updatedItem.setId(idForUpdate);
                tracker.update(updatedItem);
            } else if (menuItemNumber == 3) {
                Item itemForDelete = new Item("");
                itemForDelete.setId(askItemId());
                tracker.delete(itemForDelete);
            } else if (menuItemNumber == 4) {
                Item foundItem = tracker.findById(askItemId());
                printHeader();
                printItem(foundItem);
                System.out.println();
            } else if (menuItemNumber == 5) {
                String itemName = askItemName();
                Item[] items = tracker.findByName(itemName);
                printHeader();
                printItems(items);
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
        return input.ask("Please enter item Id: \n");
    }

    /**
     * Prints header for an item text representation.
     */
    private void printHeader() {
        System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
    }

    /**
     * Prints array of items as a table.
     * @param items items to print
     */
    private void printItems(Item[] items) {
        for (Item item : items) {
            printItem(item);
        }
    }

    /**
     * Prints an item as table line.
     * @param item item to print
     */
    private void printItem(Item item) {
        System.out.printf("| %20s\t| %40s\t|\n", item.getId(), item.getName());
    }

    /**
     * Asks user to enter item name and return it as a string.
     * @return item name
     */
    private String askItemName() {
        return input.ask("Please enter item name: \n");
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
