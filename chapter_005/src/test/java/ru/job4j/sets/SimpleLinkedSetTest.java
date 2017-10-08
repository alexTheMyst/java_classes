package ru.job4j.sets;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SimpleLinkedSet.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 22.09.17
 */
public class SimpleLinkedSetTest {
    /**
     * Adds two equal elements and check that set size is one.
     */
    @Test
    public void whenAddTwoSameEntitiesThenSizeOne() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        int setSize = 0;

        set.add(1);
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            setSize++;
        }

        assertThat(setSize, is(1));
    }

    /**
     * Adds three elements and check that after iteration sum is equal.
     */
    @Test
    public void whenAddThreeThenIteratorReturnsThree() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        int sumResult = 0;

        set.add(1);
        set.add(2);
        set.add(3);
        for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
            sumResult += iterator.next();
        }

        assertThat(sumResult, is(6));
    }

    /**
     * Asks next element from empty set iterator and gets exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskNextFromSetIteratorThenException() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.iterator().next();
    }

    /**
     * Adds one element and asks two times and get exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddOneAndAskTwoFromIteratorThenException() {
        SimpleLinkedSet<Integer> set = new SimpleLinkedSet<>();
        set.add(1);

        Iterator<Integer> iterator = set.iterator();
        iterator.next();
        iterator.next();
    }
}