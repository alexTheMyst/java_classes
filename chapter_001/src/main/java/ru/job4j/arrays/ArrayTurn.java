package ru.job4j.arrays;

/**
 * ArrayTurn calss implements turn square array on 90 degree.
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.03.17
 */
public class ArrayTurn {
    /**
     * Turn square array on 90 degree.
     * @param givenArray array
     */
    public void turn(int[][] givenArray) {
        int circlesCount = givenArray.length;
        for (int i = 0; i < circlesCount / 2; i++) {
            for (int j = i; j < circlesCount - i - 1; j++) {
                int temp = givenArray[i][j];
                //first step
                givenArray[i][j] = givenArray[circlesCount - 1 - j][i];
                //second step
                givenArray[circlesCount - 1 - j][i] = givenArray[circlesCount - 1 - i][circlesCount - 1 - j];
                //third step
                givenArray[circlesCount - 1 - i][circlesCount - 1 - j] = givenArray[j][circlesCount - 1 - i];
                //forth step
                givenArray[j][circlesCount - 1 - i] = temp;
            }
        }
    }
}



