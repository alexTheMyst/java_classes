package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Prime numbers iterator implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 19.07.17
 */
public class PrimeNumberIterator implements Iterator<Integer> {
    /**
     * Array of int.
     */
    private final int[] numbers;

    /**
     * Index of next prime number in numbers array.
     */
    private int nextPrimeNumberIndex = -1;

    /**
     * Index of previous prime number in numbers array.
     */
    private int previousPrimeNumberIndex = -1;

    /**
     * Assigns array and tries to find next prime number in the numbers array.
     *
     * @param numbers any array of int
     */
    public PrimeNumberIterator(final int[] numbers) {
        this.numbers = numbers;
    }

    /**
     * Checks that numbers contains next prime number.
     *
     * @return true if next prime number exists in number or false otherwise.
     */
    @Override
    public boolean hasNext() {
        if (this.previousPrimeNumberIndex == -1) {
            this.findNextPrimeIndex(0);
        }
        return this.nextPrimeNumberIndex >= 0;
    }

    /**
     * Returns prime number and checks that next prime number exists in numbers array.
     *
     * @return prime number as Integer
     */
    @Override
    public Integer next() {
        final Integer result = this.numbers[nextPrimeNumberIndex];
        this.previousPrimeNumberIndex = this.nextPrimeNumberIndex;
        this.findNextPrimeIndex(++this.nextPrimeNumberIndex);
        return result;
    }

    /**
     * Checks is given number is prime.
     *
     * @param number some int
     * @return true if given number is a prime number or else if it is not
     */
    private boolean isPrime(int number) {
        boolean result = true;
        //checks that number is even
        if (number % 2 == 0) {
            result = false;
        } else {
            //checks that number divides with any odd number
            for (int i = 3; i < number; i += 2) {
                if (number % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Searches for the next prime number and if it found sets nextPrimeNumberIndex.
     * If prime number not found nextPrimeNumberIndex will be set to -1.
     *
     * @param startIndex index from which search will be started
     */
    private void findNextPrimeIndex(int startIndex) {
        int result = -1;
        for (int index = startIndex; index < this.numbers.length; index++) {
            if (isPrime(this.numbers[index])) {
                result = index;
                break;
            }
        }
        this.nextPrimeNumberIndex = result;
    }
}