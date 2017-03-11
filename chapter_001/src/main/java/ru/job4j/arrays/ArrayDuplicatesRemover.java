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
     * @param givenArray array
     * @return array
     */
    public String[] removeDuplicates(String[] givenArray) {
        int equalsCounter = 0;
        for (int currentWordIndex = 0; currentWordIndex < givenArray.length; currentWordIndex++) {
            int wordInstances = 0;
            for (int checkWordIndex = 0; checkWordIndex < givenArray.length - equalsCounter; checkWordIndex++) {
                if (givenArray[currentWordIndex].equals(givenArray[checkWordIndex])) {
                    wordInstances++;
                    if (wordInstances > 1) {
                        equalsCounter++;
                        shiftArrayVals(checkWordIndex, givenArray);
                    }
                }
            }
        }
        if (equalsCounter >= 1) {
            givenArray = Arrays.copyOf(givenArray, givenArray.length - equalsCounter);
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