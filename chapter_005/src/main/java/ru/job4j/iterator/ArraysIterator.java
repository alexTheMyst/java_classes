package ru.job4j.iterator;

import java.util.Iterator;

/**
 * Create iterator for two dimensional array.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 09.07.17
 */
public class ArraysIterator implements Iterator<Integer> {

    /**
     * Array of int instance.
     */
    private int[][] array;

    /**
     * Current element index.
     */
    private int elementIndex = -1;

    /**
     * Counter for passed elements.
     */
    private int arrayIndex = 0;

    /**
     * Dimensions of the array.
     */
    private int rowsCount, columnsCount;

    /**
     * Constructor.
     *
     * @param array some array of ints.
     */
    public ArraysIterator(int[][] array) {
        this.array = array;
        rowsCount = array.length - 1;
        columnsCount = array[0].length - 1;
    }

    /**
     * Checks that iterator has the next element.
     *
     * @return true if next element exists and false if it's not.
     */
    @Override
    public boolean hasNext() {
        return (elementIndex < columnsCount || arrayIndex < rowsCount);
    }

    /**
     * Gets the next element.
     *
     * @return next elemen as an Integer.
     */
    @Override
    public Integer next() {
        if (elementIndex < columnsCount) {
            elementIndex++;
        } else if (arrayIndex < rowsCount) {
            elementIndex = 0;
            arrayIndex++;
        }
        return array[arrayIndex][elementIndex];
    }
}