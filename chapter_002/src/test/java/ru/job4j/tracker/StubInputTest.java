package ru.job4j.tracker;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests tracker application by emualting users input.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.04.17
 */
public class StubInputTest {

    /**
     * Adds one item and expects to get item with the same name.
     */
    @Test
    public void testAddNewItemAndGetItemWithSameName() {
        final Tracker tracker = new Tracker();
        final Input input = new StubInput(new String[]{"0", "test1", "6"});
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll()[0].getName(), is("test1"));
    }

    /**
     * Adds two items and expects that findAll return two items.
     */
    @Test
    public void testGetsTwoItems() {
        final Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        tracker.add(new Item("test2"));
        new StartUI(tracker, new StubInput(new String[]{"1", "6"}));
        assertThat(tracker.findAll().length, is(2));
    }

    /**
     * Adds one item than changes its name and check that item have a new name.
     */
    @Test
    public void testsAfterEditItemHasNew() {
        final Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        final String id = tracker.findAll()[0].getId();
        new StartUI(tracker, new StubInput(new String[]{"2", id, "test2", "6"})).init();
        assertThat(tracker.findAll()[0].getName(), is("test2"));
    }

    /**
     * Adds one item then deletes one item and check that tracker has no items.
     */
    @Test
    public void addOneDeleteOneAndTestItemsIsEmpty() {
        final Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        final String id = tracker.findAll()[0].getId();
        new StartUI(tracker, new StubInput(new String[]{"3", id, "6"})).init();
        assertThat(tracker.findAll().length, is(0));
    }

    /**
     * Adds one item and searches it by id.
     */
    @Test @Ignore
    public void addOneItemAndSearchItemById() {
        final Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        final String id = tracker.findAll()[0].getId();
        new StartUI(tracker, new StubInput(new String[]{"4", "6"})).init();
    }

    /**
     * Adds two items and searches by name.
     */
    @Test @Ignore
    public void addTwoAndSearchByName() {
        final Tracker tracker = new Tracker();
        tracker.add(new Item("test1"));
        tracker.add(new Item("test1"));
        final String id = tracker.findAll()[0].getId();
        new StartUI(tracker, new StubInput(new String[]{"5", "test1", "6"})).init();
    }
}