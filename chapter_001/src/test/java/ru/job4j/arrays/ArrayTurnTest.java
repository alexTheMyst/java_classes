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
        final int[][] givenArray = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20},
                {21, 22, 23, 24, 25}};
        final int[][] testArray = {{21, 16, 11, 6, 1}, {22, 17, 12, 7, 2}, {23, 18, 13, 8, 3}, {24, 19, 14, 9, 4},
                {25, 20, 15, 10, 5}};
        final ArrayTurn arrayTurn = new ArrayTurn();
        arrayTurn.turn(givenArray);
        assertThat(givenArray, is(testArray));
    }
}
