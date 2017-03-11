package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ArraysMerge.
 * @author Alexey Aleshin
 * @version $id$
 * since 11.03.17
 */
public class ArraysMergeTest {
    /**
     * Test ArraysMerge existance.
     */
    @Test
    public void testArrayMergeExists() {
        final ArraysMerge arraysMerge = new ArraysMerge();
        assertThat(arraysMerge, notNullValue());
    }

    /**
     * Test merge method.
     */
    @Test
    public void testMerge() {
        final int[] firstArray = {1, 3, 5};
        final int[] secondArray = {3, 5, 7, 8, 9};
        final int[] testArray = {1, 3, 3, 5, 5, 7, 8, 9};
        final ArraysMerge arraysMerge = new ArraysMerge();
        assertThat(arraysMerge.merge(firstArray, secondArray), is(testArray));
    }
}
