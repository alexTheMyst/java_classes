package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Even numbers iterator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 16.07.17
 */
public class EvenNumbersIterator implements Iterator<Integer> {

    /**
     * Upper bound of iterator.
     */
    private int maxValue;

    /**
     * Previous returned value.
     */
    private Integer lastReturnedValue = 0;

    /**
     * Constructor, sets maxValue.
     *
     * @param maxValue upper bound for the iterator
     */
    public EvenNumbersIterator(int maxValue) {
        this.maxValue = maxValue % 2 == 0 ? maxValue : maxValue - 1;
    }

    /**
     * Checks that previous returned value less than upper bound.
     *
     * @return true if lastReturnedValue less than maxValue
     */
    @Override
    public boolean hasNext() {
        return this.lastReturnedValue < this.maxValue;
    }

    /**
     * Increments last returnedValue by 2 and returns it.
     *
     * @return lastReturnedValue
     */
    @Override
    public Integer next() {
        this.lastReturnedValue += 2;
        return this.lastReturnedValue;
    }
}
