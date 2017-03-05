package ru.job4j.arrays;

/**
 * Turn class implements reverse array logic.
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.03.17
 */
public class Turn {
    /**
     * Reverse given int array.
     * @param givenArray array
     */
    public void back(int[] givenArray) {
        int lastArrayElementIndex = givenArray.length - 1;
        for (int i = 0; i < givenArray.length / 2; i++) {
            int tmpValue = givenArray[i];
            givenArray[i] = givenArray[lastArrayElementIndex - i];
            givenArray[lastArrayElementIndex - i] = tmpValue;
        }
    }
}