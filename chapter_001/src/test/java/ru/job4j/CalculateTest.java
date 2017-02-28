package ru.job4j;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Alexey Aleshin (mailto:al-al-m@yandex.ru)
* @version $Id$
* @since 28.02.2017
*/
public class CalculateTest {
    /**
     * Test main method.
     */
    @Test
    public void whenRunThenPrintString() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Calculate.main(null);
        assertThat(
                out.toString(),
                is(String.format("Hello World!%s",
                        System.getProperty("line.separator"))
                )
        );
    }
}
