package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * Tests for Triangle class.
 * @author Alexey
 * @version $id$
 * @since 03.03.17
 */
public class TriangleTest {
    /**
     * Test Triangle class exists.
     */
    @Test
    public void testTriangleExists() {
        Point aPoint = new Point(1, 1);
        Point bPoint = new Point(2, 1);
        Point cPoint = new Point(1, 2);
        Triangle triangle = new Triangle(aPoint, bPoint, cPoint);
        assertThat(triangle, notNullValue());
    }

    /**
     * Test Triangle.area() method.
     */
    @Test
    public void testTriangleArea() {
        Point aPoint = new Point(1, 1);
        Point bPoint = new Point(2, 1);
        Point cPoint = new Point(1, 2);
        Triangle triangle = new Triangle(aPoint, bPoint, cPoint);
        assertThat(triangle.area(), closeTo(0.5, 0.01));
    }

    /**
     * Test not a triangle case.
     */
    @Test
    public void testLine() {
        Point aPoint = new Point(1, 1);
        Point bPoint = new Point(2, 2);
        Point cPoint = new Point(3, 3);
        Triangle triangle = new Triangle(aPoint, bPoint, cPoint);
        assertThat(triangle.area(), closeTo(0.0, 0.01));
    }
}
