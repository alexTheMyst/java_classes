package ru.job4j.mt.bomberman;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Represent game board.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 18.03.2018
 */
public class Board {
    /**
     * Board cells.
     */
    private final ReentrantLock[][] cells;

    /**
     * Creates the board with given number rows and columns.
     *
     * @param rows rows number
     * @param columns columns number
     */
    public Board(int rows, int columns) {
        this.cells = new ReentrantLock[rows][columns];
    }

    /**
     * Fills the board with cells.
     */
    public void initialize() {
        for (ReentrantLock[] locks : this.cells) {
            for (int i=0; i < locks.length; i++) {
                locks[i] = new ReentrantLock();
            }
        }
    }

    /**
     * Getter for the cell by given coordinates.
     *
     * @param row row number
     * @param column column number
     * @return lock for the cell
     */
    public ReentrantLock getCell(int row, int column) {
        return this.cells[row][column];
    }
}