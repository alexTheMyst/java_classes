package ru.job4j.mt.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * GAme board representation.
 *
 * @author alexey
 * @version 29.03.18
 */
public class Board {

    /**
     * Game field.
     */
    private final ReentrantLock[][] cells;

    /**
     * Constructor.
     *
     * @param rows    rows count
     * @param columns columns count
     */
    public Board(int rows, int columns) {
        this.cells = new ReentrantLock[rows][columns];
    }

    /**
     * Initialize board.
     */
    public void initialize() {
        for (ReentrantLock[] locks : this.cells) {
            for (int i = 0; i < locks.length; i++) {
                locks[i] = new ReentrantLock();
            }
        }
    }

    /**
     * Gets the cell by its coordinate.
     *
     * @param row    row number
     * @param column column number
     * @return cell
     */
    public ReentrantLock getCell(int row, int column) {
        return this.cells[row][column];
    }

    /**
     * Checks that give cell exists on this board.
     *
     * @param cell cell
     * @return true if exists or false otherwise
     */
    public boolean isCellExists(ReentrantLock cell) {
        boolean result = false;
        for (ReentrantLock[] lockRows : cells) {
            for (ReentrantLock lock : lockRows) {
                if (lock.equals(cell)) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Gets the cell from given direction.
     *
     * @param cell      start cell
     * @param direction direction to next cell
     * @return cell
     * @throws IllegalPosition if start cell doesn't exists
     */
    public ReentrantLock getCellFromDirection(ReentrantLock cell, Direction direction) throws IllegalPosition {
        Coordinate coordinate = getCoordinate(cell);
        return cells[coordinate.row + direction.getRow()][coordinate.column + direction.getColumn()];
    }

    private Coordinate getCoordinate(ReentrantLock cell) throws IllegalPosition {
        Coordinate result = new Coordinate(-1, -1);
        for (int rowIndex = 0; rowIndex < cells.length; rowIndex++) {
            for (int columnIndex = 0; columnIndex < cells[rowIndex].length; columnIndex++) {
                ReentrantLock reentrantLock = cells[rowIndex][columnIndex];
                if (reentrantLock.equals(cell)) {
                    result = new Coordinate(rowIndex, columnIndex);
                    break;
                }
            }
        }
        checkCoordinate(result);
        return result;
    }

    /**
     * Checks coordinate position.
     *
     * @param coordinate some coordinate
     * @throws IllegalPosition when position is out of board
     */
    private void checkCoordinate(Board.Coordinate coordinate) throws IllegalPosition {
        if (coordinate.row < 0 && coordinate.column < 0) {
            throw new IllegalPosition();
        }
    }

    /**
     * Represents boar coordinate.
     */
    static class Coordinate {
        /**
         * Row number.
         */
        private final int row;
        /**
         * Column number.
         */
        private final int column;

        /**
         * Constructor.
         *
         * @param row    row number
         * @param column column number
         */
        Coordinate(int row, int column) {
            this.row = row;
            this.column = column;
        }
    }
}