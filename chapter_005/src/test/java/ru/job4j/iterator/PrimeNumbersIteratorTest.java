package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Contains tests for prim numbers iterator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 19.07.17
 */
public class PrimeNumbersIteratorTest {
    /**
     * Creates PrimeNumberIterator using array which includes prime and not prime numbers.
     * Checks that after iteration we got only prime numbers.
     */
    @Test
    public void whenCreateIteratorItReturnsOnlyPrimeNumbers() {
        final int[] numbers = {3, 4, 5, 6, 7, 8, 9};
        final PrimeNumberIterator primeNumberIterator = new PrimeNumberIterator(numbers);
        final List<Integer> primeNumbers = Arrays.asList(3, 5, 7);
        final List<Integer> result = new ArrayList<>();

        while (primeNumberIterator.hasNext()) {
            result.add(primeNumberIterator.next());
        }

        assertThat(result, is(primeNumbers));
    }

    /**
     * Checks if we created PrimeNumberIterator with array without any prime number.
     * Than we should get blank list as result of iteration.
     */
    @Test
    public void whenCreateIteratorWithoutPrimeMumbersReturnsBlankList() {
        final int[] numbers = {4, 6, 8, 9};
        final PrimeNumberIterator primeNumberIterator = new PrimeNumberIterator(numbers);
        final List<Integer> result = new ArrayList<>();

        while (primeNumberIterator.hasNext()) {
            result.add(primeNumberIterator.next());
        }

        assertThat(result.size(), is(0));
    }
}