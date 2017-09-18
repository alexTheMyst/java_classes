package ru.job4j.sets;

import org.junit.Test;

import java.util.Iterator;

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
     * Adds two identical entities and test that size is one.
     */
    @Test
    public void whenAddTwoSameEntitiesThenSizeOne() {
        SimpleSet<Integer> set = new SimpleSet<>();

        set.add(1);
        set.add(1);

        assertThat(set.getSize(), is(1));
    }

    /**
     * Adds three entities and tests sum.
     */
    @Test
    public void whenAddThreeThenIteratorReturnsThree() {
        SimpleSet<Integer> set = new SimpleSet<>();
        int sumResult = 0;

        set.add(1);
        set.add(2);
        set.add(3);
        for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext(); ) {
            sumResult += iterator.next();
        }

        assertThat(sumResult, is(6));
    }
}