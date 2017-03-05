package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Tests for Bubble sort class.
 * @author Alexey
 * @version $id$
 * @since 05.03.17
 */
public class BubbleSortTest {
    /**
     * Test BubbleSortTest existence.
     */
    @Test
    public void testBubbleSortExists() {
        final BubbleSort bubbleSort = new BubbleSort();
    }

    /**
     * Test that sorted array returned.
     */
    @Test
    public void testSortReturnesSortedArray() {
        final BubbleSort bubbleSort = new BubbleSort();
        final int[] givenArray = new int[] {5, 2, 3, 4, 1};
        final int[] sortedArray = new int[] {1, 2, 3, 4, 5};
        bubbleSort.sort(givenArray);
        assertThat(givenArray, is(sortedArray));
    }
}
