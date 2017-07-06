package ru.job4j.misc;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for auxiliary tasks.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.07.17
 */
public class AuxTaskTests {

    /**
     * Utils var.
     */
    private Utils utils;

    /**
     * Creates nee utils var before each test.
     */
    @Before
    public void setup() {
        utils = new Utils();
    }

    /**
     * Test for Utils.getElementFromList method.
     */
    @Test
    public void whenDoSomething() {
        final List<Integer> someList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        assertThat(utils.getElementFromList(someList, 2), is(5));
    }

    /**
     * Tests getting N minimal elements from unsorted container.
     */
    @Test
    public void testGetSeveralMinimalElementsFromUnsortedList() {
        final List<Integer> someList = new LinkedList<>(Arrays.asList(6, 4, 5, 3, 2, 1));
        final List<Integer> resultList = new LinkedList<>(Arrays.asList(1, 2));

        assertThat(utils.getMinElements(someList, 2), is(resultList));
    }
}