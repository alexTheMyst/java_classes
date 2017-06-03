package ru.job4j.conversion;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ConvertList.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 03.06.17
 */
public class ConvertListTests {

    /**
     * ConvertList instance.
     */
    private ConvertList convertList;

    /**
     * Creates new ConvertList instance before each test.
     */
    @Before
    public void setup() {
        this.convertList = new ConvertList();
    }

    /**
     * Tests two dimensional array to list conversion.
     */
    @Test
    public void testToListMethod() {
        final int[][] initialArray = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        final List list = new ArrayList(9);
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        assertThat(this.convertList.toList(initialArray), is(list));
    }

    /**
     * Tests list to two dimensional array conversion.
     */
    @Test
    public void testToArrayMethod() {
        final List initialList = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        final int[][] testArray = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        assertThat(this.convertList.toArray(initialList, 3), is(testArray));
    }
}
