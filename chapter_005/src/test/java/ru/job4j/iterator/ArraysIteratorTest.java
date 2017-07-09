package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ArrayIterator methods.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 09.07.17
 */
public class ArraysIteratorTest {

    /**
     * Tests that iterator returns elements in right order.
     */
    @Test
    public void whenPutTwoDimensionalArrayGetIterator() {
        final ArraysIterator arraysIterator = new ArraysIterator(new int[][]{{1, 2, 3}, {4, 5, 6}});
        final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        final List<Integer> testList = new ArrayList<>();
        while (arraysIterator.hasNext()) {
            testList.add(arraysIterator.next());
        }

        assertThat(testList, is(list));
    }
}