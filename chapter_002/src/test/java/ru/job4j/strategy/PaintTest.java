package ru.job4j.strategy;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Paint class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.04.17
 */
public class PaintTest {
    /**
     * Testsa class existence.
     */
    @Test
    public void testPaintExistance() {
        final Paint paint = new Paint();
        assertThat(paint, notNullValue());
    }

    /**
     * Tests that paint draws a triangle.
     */
    @Test
    public void testPaintDraw() {
        final Paint paint = new Paint();
        paint.setShape(new Triangle());
        final String triangle = "  ^\n /  \\\n------";
        assertThat(paint.draw(), is(triangle));
    }

    /**
     * Tests that paint draws a square.
     */
    @Test
    public void testPaintDrawsSquare() {
        final Paint paint = new Paint();
        paint.setShape(new Square());
        final String square = "+---+\n|   |\n+---+";
        assertThat(paint.draw(), is(square));
    }
}
