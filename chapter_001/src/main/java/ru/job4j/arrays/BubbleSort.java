package ru.job4j.arrays;

/**
 * Bubble sort class.
 * @author Alexey
 * @version $id$
 * @since 05.03.17
 */
public class BubbleSort {
    /**
     * Sort givenArray with Bubble sort method.
     * @param givenArray array of int.
     */
    public void sort(int[] givenArray) {
        for (int i = 0; i < givenArray.length - 1; i++) {
            for (int j = 0; j < givenArray.length - 1; j++) {
                if (givenArray[j] > givenArray[j + 1]) {
                    int tmpValue = givenArray[j];
                    givenArray[j] = givenArray[j + 1];
                    givenArray[j + 1] = tmpValue;
                }
            }
        }
    }
}
