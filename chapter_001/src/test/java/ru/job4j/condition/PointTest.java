package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Point class tests.
 * @author Alexey Aleshin
 * @version $$id
 * @since 02.03.17.
 */
public class PointTest {
    /**
     * Test Point instance existence.
     */
    @Test
    public void testPointExists() {
        final Point point = new Point(2, 3);
        assertThat(point, notNullValue());
    }

    /**
     * Test Point has x value.
     */
    @Test
    public void testPointHasX() {
        final Point point = new Point(2, 3);
        final int pointX = 2;
        assertThat(point.getX(), is(pointX));
    }

    /**
     * Test Point has y value.
     */
    @Test
    public void testPOinthasY() {
        final Point point = new Point(2, 3);
        final int pointY = 3;
        assertThat(point.getY(), is(pointY));
    }

    /**
     * Test is some point resides on function (y(x) = a * x + b).
     */
    @Test
    public void testPointNotResides() {
        final Point point = new Point(2, 3);
        final int aValue = 1;
        final int bValue = 1;
        assertThat(point.is(aValue, bValue), is(true));
    }
}
