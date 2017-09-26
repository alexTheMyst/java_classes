package ru.job4j.sets;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for improved set.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.09.17
 */
public class SimpleSetImprovedTest {
    /**
     * Set for tests.
     */
    private SimpleSetImproved<Integer> integers;

    /**
     * Setup variable before each test.
     */
    @Before
    public void setup() {
        this.integers = new SimpleSetImproved<>(10);
    }

    /**
     * Adds one element and check that set size is one.
     */
    @Test
    public void whenAddOneThenSizeIsOne() {
        this.integers.add(1);

        assertThat(this.integers.getSize(), is(1));
    }

    /**
     * Asks next element from empty set iterator and gets exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskNextFromSetIteratorThenException() {
        integers.iterator().next();
    }

    /**
     * Adds one element and asks two times and get exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddOneAndAskTwoFromIteratorThenException() {
        integers.add(1);

        Iterator<Integer> iterator = integers.iterator();
        iterator.next();
        iterator.next();
    }

    /**
     * Inserts 100000 elements in different simple set implementation and shows time.
     */
    @Test
    public void performanceMeasurements() {
        final SimpleSet<Integer> setWithArray = new SimpleSet<>();
        final SimpleLinkedSet<Integer> setWithLinkedList = new SimpleLinkedSet<>();
        final SimpleSetImproved<Integer> set100 = new SimpleSetImproved<>(10_000);
        final Set<Integer> javaSet = new HashSet<>();
        final int testSize = 10_000;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < testSize; i++) {
            setWithArray.add(i);
        }
        System.out.printf("Inserted %d values in set %d ms\n", testSize, System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            setWithLinkedList.add(i);
        }
        System.out.printf("Inserted %d values in set using linked list %d ms\n", testSize, System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            this.integers.add(i);
        }
        System.out.printf("Inserted %d values in improved set with 10 buckets %d ms\n", testSize, System.currentTimeMillis() - startTime);


        startTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            set100.add(i);
        }
        System.out.printf("Inserted %d values in improved set with 10000 buckets  %d ms\n", testSize, System.currentTimeMillis() - startTime);

        startTime = System.currentTimeMillis();
        for (int i = 0; i < testSize; i++) {
            javaSet.add(i);
        }
        System.out.printf("Inserted %d values in java set %d ms\n", testSize, System.currentTimeMillis() - startTime);
    }
}