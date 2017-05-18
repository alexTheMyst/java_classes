package ru.job4j.chess;

/**
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public class Board {

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
    public Board() {
        for (int rowIndex = 0; rowIndex < 8; rowIndex++) {
            for (int columnIndex = 0; columnIndex < 8; columnIndex++) {
                cells[rowIndex][columnIndex] = new Cell(rowIndex, columnIndex);
            }
        }
    }

    /**
     * Moves a figure.
     * @param source from
     * @param dst to
     * @return true if move possible
     * @throws OccupiedWayException figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    boolean move(Cell source, Cell dst) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {
        if (source.occupied()) {
            Cell[] way = source.getFigure().way(dst);
                for (Cell cell : way) {
/*                    for(int figuresIndex = 0; figuresIndex < figures.length; figuresIndex++) {
                        if (figures[figuresIndex] != null && figures[figuresIndex].position == cell) {
                            throw new OccupiedWayException();
                        }
                    }*/
                    if (cells[cell.getRowIndex()][cell.getColumnIndex()].occupied()) {
                        throw new OccupiedWayException();
                    }
                }
                source.getFigure().clone(dst);
                source.clear();
        } else {
            throw new FigureNotFoundException();
        }
        return false;
    }

    /**
     * Gets the cell for given coordinates.
     * @param rowIndex row index
     * @param columnIndex column index
     * @return cell
     */
    public Cell getCell(int rowIndex, int columnIndex) {
        return this.cells[rowIndex][columnIndex];
    }
}
