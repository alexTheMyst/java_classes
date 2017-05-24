package ru.job4j.chess;

/**
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public class Board {
    /**
     * Figures array index.
     */
    private int figureIndex;
    /**
     * Figures on the board.
     */
    private Figure[] figures = new Figure[16];

    /**
     * Board representation.
     */
    private Cell[][] cells = new Cell[8][8];

    /**
     * Constructor.
     */
    Board() {
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                cells[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex);
            }
        }
    }

    /**
     * Moves a figure from source cell to dst cell.
     *
     * @param source cell
     * @param dst cell
     * @return true if move possible.
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    boolean move(Cell source, Cell dst) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (isCellOccupied(source)) {
            Figure figure = getFigureFromCell(source);
            Cell[] way = figure.way(dst);
            for (Cell cell : way) {
                if (isCellOccupied(this.getCell(cell.getRowIndex(), cell.getColumnIndex()))) {
                    throw new OccupiedWayException();
                }
            }
            for (int index = 0; index < figureIndex; index++) {
                if (figures[index].getPosition() == source) {
                    figures[index] = figure.clone(dst);
                }
            }
        } else {
            throw new FigureNotFoundException();
        }
        return true;
    }

    /**
     * Gets the cell for given coordinates.
     *
     * @param rowIndex    row index
     * @param columnIndex column index
     * @return cell
     */
    public Cell getCell(int rowIndex, int columnIndex) {
        return this.cells[rowIndex][columnIndex];
    }

    /**
     * Checks the cell.
     * @param cell given cell
     * @return true if the cell is occupied
     */
    public boolean isCellOccupied(Cell cell) {
        for (int index = 0; index < figureIndex; index++) {
            if (figures[index].getPosition() == cell) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the @param figure on the board.
     */
    public void setupFigure(Figure figure) {
        figures[figureIndex++] = figure;
    }

    /**
     * Gets a figure from the @param source cell.
     *
     * @return the figure
     */
    private Figure getFigureFromCell(Cell source) {
        for (int index = 0; index < figureIndex; index++) {
            if (figures[index].getPosition() == source) {
                return figures[index];
            }
        }
        return null;
    }
}