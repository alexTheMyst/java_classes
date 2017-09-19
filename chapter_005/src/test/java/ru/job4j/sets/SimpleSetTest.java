package ru.job4j.sets;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for simple set implementation using array.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 17.09.17
 */
public class SimpleSetTest {
    /**
     * Set for tests.
     */
    private SimpleSet<Integer> set;

    /**
     * Initialize set before each test.
     */
    @Before
    public void setUp() {
        this.set = new SimpleSet<>();
    }

    /**
     * Adds two identical entities and test that size is one.
     */
    @Test
    public void whenAddTwoSameEntitiesThenSizeOne() {
        this.set.add(1);
        this.set.add(1);

        assertThat(this.set.getSize(), is(1));
    }

    /**
     * Adds three entities and tests sum.
     */
    @Test
    public void whenAddThreeThenIteratorReturnsThree() {
        int sumResult = 0;

        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        for (Iterator<Integer> iterator = this.set.iterator(); iterator.hasNext(); ) {
            sumResult += iterator.next();
        }

        assertThat(sumResult, is(6));
    }

    /**
     * Tries to ask next element from empty set iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskForNonExistedElementThenException() {
        this.set.iterator().next();
    }

    /**
     * Loop with size + 1 steps for iterator next method should throw exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskMoreThanNeededThenException() {
        this.set.add(1);
        this.set.add(2);
        this.set.add(3);
        Iterator<Integer> iterator = this.set.iterator();
        int counter = 0;
        while (counter < 4) {
            iterator.next();
            counter++;
        }
    }
}