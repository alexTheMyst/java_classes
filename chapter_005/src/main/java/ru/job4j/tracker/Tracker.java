package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Tracker is a array wrapper with some additional logic.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.03.17
 */
public class Tracker {
    /**
     * Random generator.
     */
    private static final Random RANDOM = new Random();

    /**
     * Array of items.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Adds given item to the array.
     *
     * @param item given item
     * @return inserted item
     */
    public Item add(Item item) {
        item.setId(String.valueOf(System.currentTimeMillis() + RANDOM.nextInt(100)));
        this.items.add(item);
        return item;
    }

    /**
     * Gets copy of the array of items.
     *
     * @return Item[]
     */
    public List<Item> findAll() {
        return this.items;
    }

    /**
     * Deletes item from array.
     *
     * @param itemToDelete to delete
     */
    public void delete(Item itemToDelete) {
        this.items.removeIf(item -> item.getId().equals(itemToDelete.getId()));
    }

    /**
     * Searches for items in the array by its name.
     *
     * @param name of item to search
     * @return item with given name
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item currentItem : this.items) {
            if (currentItem != null && currentItem.getName().equals(name)) {
                result.add(currentItem);
            }
        }
        return result;
    }

    /**
     * Searches item by id.
     *
     * @param id to search
     * @return item with the same id
     */
    public Item findById(String id) {
        Item result = null;
        for (Item currentItem : this.items) {
            if (currentItem != null && currentItem.getId().equals(id)) {
                result = currentItem;
                break;
            }
        }
        return result;
    }

    /**
     * Searches for item with the same id as updatedItem and updates found item.
     *
     * @param updatedItem item to update
     */
    public void update(Item updatedItem) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getId() == updatedItem.getId()) {
                this.items.remove(i);
                this.items.add(updatedItem);
            }
        }
    }
}