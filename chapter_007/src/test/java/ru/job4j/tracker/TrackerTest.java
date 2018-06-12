package ru.job4j.tracker;

import org.junit.Test;

/**
 * Demonstrates tracker with DB storage.
 *
 * @author alexey
 * @version 6/5/18
 */
public class TrackerTest {
    /**
     * Runs tracker and does some operations.
     */
    @Test
    public void demo() {
        System.out.println("Program started.");
        try (Tracker tracker = new Tracker()) {
            deleteAllItems(tracker);
            tracker.add(new Item("test1"));
            tracker.add(new Item("test2"));
            printAllItems(tracker);

            Item[] foundItems = tracker.findByName("test2");
            for (Item item : foundItems) {
                tracker.delete(item);
            }

            printAllItems(tracker);

            Item testItem = tracker.findById("1");
            testItem.setName("test123");
            tracker.update(testItem);
            printAllItems(tracker);
        }
        System.out.println("Program finished.");

    }

    /**
     * Deletes all items from tracker.
     *
     * @param tracker tracker object
     */
    private void deleteAllItems(Tracker tracker)  {
        Item[] allItems = tracker.findAll();
        for (Item item : allItems) {
            tracker.delete(item);
        }
    }

    /**
     * Prints all items from tracker.
     *
     * @param tracker tracker object
     */
    private void printAllItems(Tracker tracker)  {
        Item[] allItems = tracker.findAll();
        for (Item item : allItems) {
            System.out.println(item);
        }
        System.out.println("_______________________________________________________________________");
    }
}