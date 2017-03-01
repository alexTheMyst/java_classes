package ru.job4j.calculator;

/**
 * Simple calculator class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.03.17.
 */
public class Calculator {
    /**
     * Variable to store result.
     */
    private double result = 0d;

    /**
     * Result getter.
     * @return result
     */
    public double getResult() {
        return result;
    }

    /**
     * Store sum of parameters to result variable.
     * @param first First parameter.
     * @param second Second parameter.
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Store result after substraction second from first.
     * @param first First parameter.
     * @param second Second parameter.
     */
    public void substruct(double first, double second) {
        this.result = first - second;
    }

    /**
     * Store result of division first on second.
     * @param first First parameter.
     * @param second Second parameter.
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Store result of multiplication first on second.
     * @param first First parameter.
     * @param second Second parameter.
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }
}
