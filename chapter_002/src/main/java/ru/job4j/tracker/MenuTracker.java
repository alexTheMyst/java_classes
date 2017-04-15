package ru.job4j.tracker;

/**
 * Menu for the tracker.
 * @author Alexey Aleshin
 * @version $id$
 * @since 09.04.17
 */
public class MenuTracker {
    /**
     * Input instance.
     */
    private Input input;
    /**
     * Tracker instance.
     */
    private Tracker tracker;
    /**
     * Array of user action objects.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * Constructor.
     * @param input any input implementation
     * @param tracker tracker object
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Adds user action objects to the menu array.
     */
    void fillActions() {
        this.actions[0] = new AddItem();
        this.actions[1] = new ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = new DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindItemsByName();
        this.actions[6] = new ExitProgramm();
    }

    /**
     * Runs user action with a given key.
     * @param key any given key
     */
    void runAction(int key) {
        this.actions[key].execute(this.input, this.tracker);
    }

    /**
     * Shows menu contents.
     */
    void showMenu() {
        for (UserAction action : actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * @return array of user actions
     */
    public UserAction[] getActions() {
        return actions;
    }
}

/**
 * Contains methods to add item.
 */
class AddItem implements UserAction {

    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 0;
    }

    /**
     * Adds new item object to the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Please enter item name: \n");
        tracker.add(new Item(name));
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Add new Item.");
    }
}

/**
 * Contains methods to show all items.
 */
class ShowAllItems implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 1;
    }

    /**
     * Prints all items from the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        Item[] items = tracker.findAll();
        if (items != null && items.length > 0) {
            System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
            for (Item item : items) {
                System.out.printf("| %20s\t| %40s\t|\n", item.getId(), item.getName());            }
        } else {
            System.out.println("No items found.");
        }
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Show all items.");
    }
}

/**
 * Contains method for edit item object.
 */
class EditItem implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 2;
    }

    /**
     * Updates item in the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Input Item id for edit.");
        String newItemName = input.ask("Enter new item name.");
        Item item = new Item(newItemName);
        item.setId(id);
        tracker.update(item);
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Edit item.");
    }
}

/**
 * Contains method for delete the item object.
 */
class DeleteItem implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 3;
    }

    /**
     * Deletes the item from the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please enter item Id for delete: ");
        Item itemForDelete = new Item("");
        itemForDelete.setId(id);
        tracker.delete(itemForDelete);
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Delete item.");
    }
}

/**
 * Contains method for find item by id.
 */
class FindItemById implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 4;
    }

    /**
     * Searches the item with id in the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Please enter item Id for search: ");
        Item foundItem = tracker.findById(id);
        if (foundItem != null) {
            System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
            System.out.printf("| %20s\t| %40s\t|\n", foundItem.getId(), foundItem.getName());
        } else {
            System.out.println(String.format("Item with id: %s not found.", id));
        }
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Find item by Id.");
    }
}

/**
 * Contains method for find item by id.
 */
class FindItemsByName implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 5;
    }

    /**
     * Searches the item with id in the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        String itemName = input.ask("Please enter item name for search: ");
        Item[] foundItems = tracker.findByName(itemName);
        if (foundItems != null && foundItems.length > 0) {
            System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
            for (Item foundItem : foundItems) {
                System.out.printf("| %20s\t| %40s\t|\n", foundItem.getId(), foundItem.getName());
            }
        } else {
            System.out.println(String.format("Item with name:%s not found.", itemName));
        }
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Find item by name.");
    }
}

/**
 * Contains method for find item by id.
 */
class ExitProgramm implements UserAction {
    /**
     * Generates the key.
     * @return key
     */
    @Override
    public int key() {
        return 6;
    }

    /**
     * Searches the item with id in the tracker.
     * @param input any input implementation
     * @param tracker tracker instance
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println("Bye!");
    }

    /**
     * Creates menu string.
     * @return formatted string.
     */
    @Override
    public String info() {
        return String.format("%s. %s", key(), "Exit.");
    }
}