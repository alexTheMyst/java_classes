package ru.job4j.loops;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests factorial class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.03.17
 */
public class FactorialTest {
    /**
     * Test Factorial exists.
     */
    @Test
    public void testFactorial() {
        Factorial factorial = new Factorial();
        assertThat(factorial, notNullValue());
    }

    /**
     * Test Factorial calculation.
     */
    @Test
    public void testFactorialCalculation() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(4), is(24));
    }

    /**
     * Test Factorial for 0.
     */
    @Test
    public void testFactorialForZero() {
        Factorial factorial = new Factorial();
        int res = 1;
        assertThat(factorial.calc(0), is(1));
    }

    /**
     * Test Factorial for 1.
     */
    @Test
    public void testFactorialForOne() {
        Factorial factorial = new Factorial();
        int res = 1;
        assertThat(factorial.calc(1), is(1));
    }
}
