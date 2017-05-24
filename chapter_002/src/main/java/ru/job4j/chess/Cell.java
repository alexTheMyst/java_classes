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
     * Constructor.
     * @param rowIndex Cell row index.
     * @param columnName Cell column index
     */
    Cell(int rowIndex, int columnName) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnName;
    }

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
