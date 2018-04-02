package ru.job4j.mt.bomberman;

/**
 * Direction representation.
 *
 * @author alexey
 * @version 29.03.18
 */
@SuppressWarnings("unused")
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);

    private final int row;
    private final int column;

    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
