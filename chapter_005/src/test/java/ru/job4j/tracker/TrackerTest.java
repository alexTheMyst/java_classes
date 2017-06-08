package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for a tracker.
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.03.17
 */
public class TrackerTest {

    /**
     * Tests that tracker class exists.
     */
    @Test
    public void testTrackerExistence() {
        final Tracker tracker = new Tracker();
        assertThat(tracker, notNullValue());
    }

    /**
     * Tests that Item class exists.
     */
    @Test
    public void testItemClassExistence() {
        final Item item = new Item("test");
        assertThat(item, notNullValue());
    }

    /**
     * Tests tracker add method.
     */
    @Test
    public void testAddMethod() {
        final Tracker tracker = new Tracker();
        final Item item = new Item("test1");
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Test tracker delete method.
     */
    @Test
    public void testAddOneDeleteOne() {
        final Tracker tracker = new Tracker();
        final Item itemOne = new Item("test1");
        final Item itemTwo = new Item("test2");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        tracker.delete(itemOne);
        assertThat(tracker.findAll().get(0), is(itemTwo));
    }

    /**
     * Test that we can find added item by name.
     */
    @Test
    public void testAddTwoItemsAndGetOneById() {
        final Tracker tracker = new Tracker();
        final Item itemOne = new Item("test1");
        final Item itemTwo = new Item("test2");
        tracker.add(itemOne);
        tracker.add(itemTwo);
        assertThat(tracker.findByName("test2").get(0), is(itemTwo));
    }

    /**
     * Tests that item changed after update.
     */
    @Test
    public void testUpdatedItemHasChanged() {
        final Tracker tracker = new Tracker();
        final Item itemOne = tracker.add(new Item("test1"));
        final Item itemTwo = new Item("test2");
        itemTwo.setId(itemOne.getId());
        tracker.update(itemTwo);
        assertThat(tracker.findById(itemOne.getId()), is(itemTwo));
    }

    /**
     * Tests that find all return only filled elements.
     */
    @Test
    public void testThatLastElementNotNull() {
        final Tracker tracker = new Tracker();
        final Item itemOne = new Item("test1");
        final Item itemTwo = new Item("test2");
        final int numberOfElements = 2;
        tracker.add(itemOne);
        tracker.add(itemTwo);
        assertThat(tracker.findAll().size(), is(numberOfElements));
    }

    /**
     * Tests that tracker array size increases.
     */
    @Test
    public void testTrackerArrayExtension() {
        final Tracker tracker = new Tracker();
        final int elementsCount = 100;
        for (int index = 0; index < elementsCount; index++) {
            tracker.add(new Item("test1"));
        }
        assertThat(tracker.findAll().size(), is(elementsCount));
    }
}
