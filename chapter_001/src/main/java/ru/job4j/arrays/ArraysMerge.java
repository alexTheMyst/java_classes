package ru.job4j.arrays;

/**
 * Tests for ArraysMerge.
 * @author Alexey Aleshin
 * @version $id$
 * since 11.03.17
 */
public class ArraysMerge {

    /**
     * Implements merge of two sorted arrays od int.
     * @param firstArray fist array
     * @param secondArray second array
     * @return sorted array
     */
    public int[] merge(int[] firstArray, int[] secondArray) {
        final int[] result = new int[firstArray.length + secondArray.length];
        int firstArrayIndex = 0;
        int secondArrayIndex = 0;
        int resultArrayIndex = 0;
        while (firstArrayIndex < firstArray.length && secondArrayIndex < secondArray.length) {
            result[resultArrayIndex++] = (firstArray[firstArrayIndex] < secondArray[secondArrayIndex])
                    ? firstArray[firstArrayIndex++] : secondArray[secondArrayIndex++];
        }
        while (firstArrayIndex < firstArray.length) {
            result[resultArrayIndex++] = firstArray[firstArrayIndex++];
        }
        while (secondArrayIndex < secondArray.length) {
            result[resultArrayIndex++] = secondArray[secondArrayIndex++];
        }
        return result;
    }
}
