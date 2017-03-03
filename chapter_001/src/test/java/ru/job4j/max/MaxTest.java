package ru.job4j.max;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


/**
 * @author Alexey Aleshin
 * @version $id$
 * @since 02.03.17
 */
public class MaxTest {

    /**
     * Max class instance.
     */
    private Max max = new Max();

    /**
     * Test that class exists.
     */
    @Test
    public void testMaxExtstance() {
        assertThat(max, notNullValue());
    }

    /**
     * Test first is greater than second.
     */
    @Test
    public void testFirstIsMax() {
        final int first = 3;
        final int second = 2;
        assertThat(max.max(first, second), is(first));
    }

    /**
     * Test second is the greater than first.
     */
    @Test
    public void testSecondIsMax() {
        final int first = 1;
        final int second = 2;
        assertThat(max.max(first, second), is(second));
    }

    /**
     * Test find max from three numbers.
     */
    @Test
    public void testMaxFromThreeNumbers() {
        final int first = 1;
        final int second = 2;
        final int third = 3;
        assertThat(max.max(first, second, third), is(third));
    }


}
