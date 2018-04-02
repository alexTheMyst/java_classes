package ru.job4j.mt.bomberman;

/**
 * Directions enum.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 24.03.2018
 */
public enum Direction {
    /**
     * Shows how coordinates should change for each direction.
     */
    UP (-1, 0),
    DOWN (1, 0),
    LEFT (0, -1),
    RIGHT (0, 1);
    /**
     * Row.
     */
    private final int row;
    /**
     * Column.
     */
    private final int column;

    /**
     * Creates direction.
     *
     * @param row row number
     * @param column column number
     */
    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Row getter.
     *
     * @return row number
     */
    public int getRow() {
        return row;
    }

    /**
     * Column getter
     *
     * @return column number.
     */
    public int getColumn() {
        return column;
    }
}
