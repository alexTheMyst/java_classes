package ru.job4j.loops;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for Paint class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 04.03.17
 */
public class PaintTest {
    /**
     * Test Pain class existence.
     */
    @Test
    public void testPaint() {
        Paint paint = new Paint();
        assertThat(paint, notNullValue());
    }

    /**
     * Test piramid method.
     */
    @Test
    public void testPiramid() {
        Paint paint = new Paint();
        assertThat(paint.piramid(3), is("  ^\n ^ ^\n^ ^ ^"));
    }
}
