package ru.job4j.chess;

/**
 * A chess cell representation.
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public class Cell {
    /**
     * Cell row index.
     */
    private final int rowIndex;
    /**
     * Cell column index.
     */
    private final int columnIndex;
    /**
     * Figure on this cell.
     */
    //private Figure figure;

    /**
     * Constructor.
     * @param rowIndex Cell row index.
     * @param columnName Cell column index
     */
    Cell(int rowIndex, int columnName) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnName;
    }

    /**
     * Set the figure on the cell.
     * @param figure some figure
     */
/*    void occupy(Figure figure) {
        this.figure = figure;
    }*/

    /**
     * Clear the cell.
     */
/*    void clear() {
        this.figure = null;
    }*/

    /**
     * @return true if this cell has a figure.
     */
/*    boolean occupied() {
        return this.figure != null;
    }*/

    /**
     * @return the figure
     */
/*    Figure getFigure() {
        return this.figure;
    }*/

    /**
     * @return row index
     */
    int getRowIndex() {
        return rowIndex;
    }

    /**
     * @return column index
     */
    int getColumnIndex() {
        return columnIndex;
    }
}
