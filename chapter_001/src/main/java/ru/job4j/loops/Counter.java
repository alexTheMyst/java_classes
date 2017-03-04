package ru.job4j.loops;

/**
 * Counter class to some calculation methods.
 * @author Alexey
 * @version $id$
 * @since  04.03.17
 */
public class Counter {
    /**
     * add calculates sum of even numbers from startValue to finishValue.
     * @param startValue Start value.
     * @param finishValue Finish value.
     * @return sum
     */
    public int add(int startValue, int finishValue) {
        int result = 0;
        for (int i = startValue; i <= finishValue; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return result;
    }
}
