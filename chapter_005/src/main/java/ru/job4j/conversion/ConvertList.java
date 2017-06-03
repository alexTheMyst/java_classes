package ru.job4j.conversion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Contains methods to convert two dimensional array to list.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 03.06.17
 */
public class ConvertList {

    /**
     * Converts given array to list.
     *
     * @param initialArray some array
     * @return list
     */
    public List toList(int[][] initialArray) {
        final List list = new ArrayList();
        for (int i = 0; i < initialArray.length; i++) {
            for (int j = 0; j < initialArray[i].length; j++) {
                list.add(initialArray[i][j]);
            }
        }
        return list;
    }

    /**
     * Converts list to two dimensional array.
     *
     * @param initialList some list
     * @param rows        desired count of rows in a returned array
     * @return two dimensional array
     */
    public int[][] toArray(List<Integer> initialList, int rows) {
        final int elementsInRow = (initialList.size() % rows == 0) ? initialList.size() / rows : initialList.size() / rows + 1;
        final int[][] result = new int[rows][elementsInRow];
        Iterator<Integer> iterator = initialList.iterator();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < elementsInRow; j++) {
                if (iterator.hasNext()) {
                    result[i][j] = iterator.next();
                } else {
                    result[i][j] = 0;
                }
            }
        }
        return result;
    }
}