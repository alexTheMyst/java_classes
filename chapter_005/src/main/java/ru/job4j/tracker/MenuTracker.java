package ru.job4j.tracker;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private Map<Integer, UserAction> actions = new TreeMap<>();

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
        this.actions.put(0, new AddItem("Add new item."));
        this.actions.put(1, new ShowAllItems("Show all items."));
        this.actions.put(2, new EditItem("Edit an item."));
        this.actions.put(3, new DeleteItem("Delete an item."));
        this.actions.put(4, new FindItemById("Find an item by id."));
        this.actions.put(5, new FindItemsByName("Find items by name."));
        this.actions.put(6, new ExitProgram("Exit."));
    }

    /**
     * Runs user action with a given key.
     * @param key any given key
     */
    void runAction(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Shows menu contents.
     */
    void showMenu() {
        for (Map.Entry<Integer, UserAction> action : actions.entrySet()) {
            if (action.getValue() != null) {
                System.out.println(action.getValue().info());
            }
        }
    }

    /**
     * @return array of user actions
     */
    public Map<Integer, UserAction> getActions() {
        return actions;
    }
}

/**
 * Contains methods to add item.
 */
class AddItem extends BaseAction {

    /**
     * AddItem constructor.
     */
    AddItem(String name) {
        super(name);
    }

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

}

/**
 * Contains methods to show all items.
 */
class ShowAllItems extends BaseAction {

    /**
     * Constructor.
     * @param name any name as a String
     */
    ShowAllItems(String name) {
        super(name);
    }

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
        List<Item> items = tracker.findAll();
        if (items != null && items.size() > 0) {
            System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
            for (Item item : items) {
                System.out.printf("| %20s\t| %40s\t|\n", item.getId(), item.getName());            }
        } else {
            System.out.println("No items found.");
        }
    }
}

/**
 * Contains method for edit item object.
 */
class EditItem extends BaseAction {

    /**
     * Constructor.
     * @param name any name as a String
     */
    EditItem(String name) {
        super(name);
    }

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
}

/**
 * Contains method for delete the item object.
 */
class DeleteItem extends BaseAction {
    /**
     * Constructor.
     * @param name any name as a String
     */
    DeleteItem(String name) {
        super(name);
    }

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
}

/**
 * Contains method for find item by id.
 */
class FindItemById extends BaseAction {
    /**
     * Constructor.
     * @param name any name as a String
     */
    FindItemById(String name) {
        super(name);
    }

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
}

/**
 * Contains method for find item by id.
 */
class FindItemsByName extends BaseAction {
    /**
     * Constructor.
     * @param name any name as a String
     */
    FindItemsByName(String name) {
        super(name);
    }

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
        List<Item> foundItems = tracker.findByName(itemName);
        if (foundItems != null && foundItems.size() > 0) {
            System.out.printf("| %20s\t| %40s\t|\n", "Item Id", "Item Name");
            for (Item foundItem : foundItems) {
                System.out.printf("| %20s\t| %40s\t|\n", foundItem.getId(), foundItem.getName());
            }
        } else {
            System.out.println(String.format("Item with name:%s not found.", itemName));
        }
    }
}

/**
 * Contains method for find item by id.
 */
class ExitProgram extends BaseAction {
    /**
     * Constructor.
     * @param name any name as a String
     */
    ExitProgram(String name) {
        super(name);
    }

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
}