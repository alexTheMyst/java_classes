package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for eve numbers iterator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 16.07.17
 */
public class EvenNumbersIteratorTest {

    /**
     * Tests that iterator returns even values.
     */
    @Test
    public void whenGetIteratorItReturnsOnlyEvenNumbers() {
        final EvenNumbersIterator evenNumbersIterator = new EvenNumbersIterator(4);
        final List<Integer> list = new ArrayList<>(Arrays.asList(2, 4));
        final List<Integer> testList = new ArrayList<>();

        while (evenNumbersIterator.hasNext()) {
            testList.add(evenNumbersIterator.next());
        }

        assertThat(testList, is(list));
    }
}
