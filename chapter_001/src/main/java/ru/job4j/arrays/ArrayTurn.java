package ru.job4j.arrays;

/**
 * ArrayTurn calss implements turn square array on 90 degree.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.03.17
 */
public class ArrayTurn {
    /**
     * Turn square array on 90 degree.
     * @param givenArray array
     * @return result array
     */
    public int[][] turn(int[][] givenArray) {
        int width = givenArray.length;
        int heith = givenArray[0].length;
        int[][] result = new int[heith][width];
        for (int i = 0; i < heith; ++i) {
            for (int j = 0; j < width; ++j) {
                result[i][j] = givenArray[width - j - 1][i];
            }
        }
        return result;
    }
}



