package ru.job4j.condition;

/**
 * Simple Point class.
 * @author Alexey Aleshin
 * @version $id$
 * @since 02.03.17
 */
public class Point {
    /**
     * Point X coordinate variable.
     */
    private int x;

    /**
     * Point Y coordinate variable.
     */
    private int y;

    /**
     * Point constructor.
     * @param x X point coordinate
     * @param y Y point coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Point X coordinate getter.
     * @return X coordinate value
     */
    public int getX() {
        return x;
    }

    /**
     * Point Y coordinate getter.
     * @return Y coordinate value
     */
    public int getY() {
        return y;
    }

    /**
     * Check is Point instance exists on function (y(x) = a * x + b).
     * @param aValue a value
     * @param bValue b value
     * @return true if point exists on function
     */
    public boolean is(int aValue, int bValue) {
        return this.y == aValue * this.x + bValue;
    }
}
