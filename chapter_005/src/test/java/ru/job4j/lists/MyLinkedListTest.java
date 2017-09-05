package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for simple linked list implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.09.17
 */
public class MyLinkedListTest {
    /**
     * List of Integer.
     */
    private MyLinkedList<Integer> myList;

    /**
     * Creates new list before each test.
     */
    @Before
    public void setup() {
        myList = new MyLinkedList<>();
    }

    /**
     * Adds an element and checks that element with zero index the same.
     */
    @Test
    public void whenAddOneAndGetThenReturnsSameValue() {
        this.myList.add(1);

        assertThat(this.myList.get(0), is(1));
    }

    /**
     * Adds several values and gets the same values.
     */
    @Test
    public void whenAddSomeValuesThenGetSameList() {
        final List<Integer> originalList = Arrays.asList(1, 2, 3, 4, 5);
        final List<Integer> testList = new ArrayList<>();

        originalList.forEach(this.myList::add);
        this.myList.forEach(testList::add);

        assertThat(testList, is(originalList));
    }

    /**
     * Adds 99 values and checks that list has size 99.
     */
    @Test
    public void whenAddManyValuesThanArrayAutomaticallyResized() {
        for (int i = 0; i < 100; i++) {
            this.myList.add(i);
        }

        assertThat(this.myList.get(99), is(99));
    }


}