package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Turn class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.03.17
 */
public class TurnTest {
    /**
     * Test Turn class existence.
     */
    @Test
    public void testTurnExists() {
        Turn turn = new Turn();
        assertThat(turn, notNullValue());
    }

    /**
     * Test Trun.back() reverse given array.
     */
    @Test
    public void testBackReturnsReversedArray() {
        Turn turn = new Turn();
        final int[] givenArray = new int[] {1, 2, 3, 4, 5};
        final int[] reversedArray = new int[] {5, 4, 3, 2, 1};
        turn.back(givenArray);
        assertThat(givenArray, is(reversedArray));
    }
}
