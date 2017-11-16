package ru.job4j.additional;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Contains tests for StringComparator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 16.11.17
 */
public class StringComparatorTest {

    /**
     * Checks two strings compare.
     */
    @Test
    public void whenTwoIdenticalStringThenTrue() {
        String one = new String("test");
        String two = new String("test");
        StringComparator stringComparator = new StringComparator(one, two);

        assertThat(stringComparator.compare(), is(true));
    }

    /**
     * Checks two strings with identical letter set.
     */
    @Test
    public void whenTwoStringsWithSameLettersThenTrue() {
        String one = new String("set");
        String two = new String("est");
        StringComparator stringComparator = new StringComparator(one, two);

        assertThat(stringComparator.compare(), is(true));
    }

    /**
     * Checks that two different strings.
     */
    @Test
    public void whenTwoStringsThenFalse() {
        String one = new String("testOne");
        String two = new String("testTwo");
        StringComparator stringComparator = new StringComparator(one, two);

        assertThat(stringComparator.compare(), is(false));
    }

}