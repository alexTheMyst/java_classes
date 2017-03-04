package ru.job4j.loops;

/**
 * Calculate factorial using loop.
 * @author Alexey
 * @version $id$
 * @since 04.03.17
 */
public class Factorial {
    /**
     * Calculate factorial of input value.
     * @param inputValue some value
     * @return factorial
     */
    public int calc(int inputValue) {
        int result = 1;
        if (inputValue > 1) {
            for (int i = 1; i <= inputValue; i++) {
                result *= i;
            }
        }
        return result;
    }
}
