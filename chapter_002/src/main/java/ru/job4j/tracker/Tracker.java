package ru.job4j.tracker;

import java.util.Arrays;
import java.util.Random;

/**
 * Tracker is a array wrapper with some additional logic.
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
    private Item[] items = new Item[10];

    /**
     * Index of last non null item in the array.
     */
    private int position = 0;

    /**
     * Adds given item to the array.
     * @param item given item
     * @return inserted item
     */
    public Item add(Item item) {
        if (this.position == this.items.length) {
            Item[] increasedItems = new Item[this.items.length * 2];
            System.arraycopy(increasedItems, 0, this.items, 0, this.position);
            this.items = increasedItems;
        }
        item.setId(String.valueOf(System.currentTimeMillis() + RANDOM.nextInt(100)));
        this.items[position++] = item;
        return item;
    }

    /**
     * Gets copy of the array of items.
     * @return Item[]
     */
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }

    /**
     * Deletes item from array.
     * @param item to delete
     */
    public void delete(Item item) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(item.getId())) {
                this.items[index] = null;
                System.arraycopy(this.items, index + 1, this.items, index,  this.position - index);
                this.position--;
                break;
            }
        }
    }

    /**
     * Searches for items in the array by its name.
     * @param name of item to search
     * @return item with given name
     */
    public Item[] findByName(String name) {
        Item[] result = new Item[this.items.length];
        int resultIndex = 0;
        for (Item currentItem : this.items) {
            if (currentItem != null && currentItem.getName().equals(name)) {
                result[resultIndex++] = currentItem;
            }
        }
        return Arrays.copyOf(result, resultIndex);
    }

    /**
     * Searches item by id.
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
     * @param updatedItem item to update
     */
    public void update(Item updatedItem) {
        for (int index = 0; index < this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(updatedItem.getId())) {
                this.items[index] = updatedItem;
            }
        }
    }
}