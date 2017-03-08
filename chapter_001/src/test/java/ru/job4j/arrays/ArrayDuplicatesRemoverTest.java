package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ArrayDuplicatesRemover.
 * @author Alexey
 * @version $id$
 * @since  08.03.17
 */
public class ArrayDuplicatesRemoverTest {

    /**
     * Test ArrayDuplicatesRemover existence.
     */
    @Test
    public void testArrayDuplicatesRemoverExists() {
        final ArrayDuplicatesRemover arrayDuplicatesRemover = new ArrayDuplicatesRemover();
        assertThat(arrayDuplicatesRemover, notNullValue());
    }

    /**
     * Test removeDuplicates method.
     */
    @Test
    public void testRemoveDuplicates() {
        final ArrayDuplicatesRemover arrayDuplicatesRemover = new ArrayDuplicatesRemover();
        final String[] givenArray = new String[] {"Привет", "Привет", "Мир", "Мир", "Привет", "Мир", "Привет", "Мир"};
        final String[] testArray = new String[] {"Привет", "Мир"};
        assertThat(arrayDuplicatesRemover.removeDuplicates(givenArray), is(testArray));
    }
}