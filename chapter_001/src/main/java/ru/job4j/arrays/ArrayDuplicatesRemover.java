package ru.job4j.arrays;

import java.util.Arrays;

/**
 * ArrayDuplicatesRemover removes duplicates from array of strings.
 * @author Alexey
 * @version $id$
 * @since 08.03.17
 */
public class ArrayDuplicatesRemover {

    /**
     * Removes duplicates from array.
     *
     * @param givenArray array
     * @return array
     */
    public String[] removeDuplicates(String[] givenArray) {
        for (String origString : givenArray) {
            int equalsCounter = 0;
            for (int i = 0; i < givenArray.length; i++) {
                if (origString.equals(givenArray[i])) {
                    equalsCounter++;
                    if (equalsCounter > 1) {
                        shiftArrayVals(i, givenArray);
                        givenArray = Arrays.copyOf(givenArray, givenArray.length - 1);
                    }
                }
            }
        }
        return givenArray;
    }

    /**
     * Shifts array elements to position.
     * @param position position
     * @param givenArray array
     */
    private void shiftArrayVals(int position, String[] givenArray) {
        for (int arrayIndex = position; arrayIndex < givenArray.length - 1; arrayIndex++) {
            givenArray[arrayIndex] = givenArray[arrayIndex + 1];
        }
    }
}