package ru.job4j.arrays;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for ArrayTurn calss.
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.03.17
 */
public class ArrayTurnTest {
    /**
     * Test ArrayTurn existence.
     */
    @Test
    public void testArrayTurnExists() {
        final ArrayTurn arrayTurn = new ArrayTurn();
        assertThat(arrayTurn, notNullValue());
    }

    /**
     * Test turn square array on 90 degree.
     */
    @Test
    public void testTurnLogic() {
        final int[][] givenArray = {{1, 5}, {3, 7}};
        final int[][] testArray = {{3, 1}, {7, 5}};
        final ArrayTurn arrayTurn = new ArrayTurn();
        assertThat(arrayTurn.turn(givenArray), is(testArray));
    }
}
