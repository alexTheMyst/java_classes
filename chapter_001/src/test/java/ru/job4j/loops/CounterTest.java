package ru.job4j.loops;

import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Counter class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.03.17
 */
public class CounterTest {
    /**
     * Test Counter class exists.
     */
    @Test
    public void testCounter() {
        Counter counter = new Counter();
        assertThat(counter, notNullValue());
    }

    /**
     * Test add method.
     */
    @Test
    public void testCounterAdd() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 10), is(30));
    }
}
