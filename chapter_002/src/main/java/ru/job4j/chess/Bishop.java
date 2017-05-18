package ru.job4j.chess;

import java.util.Arrays;

/**
 * Bishop figure.
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public class Bishop extends Figure {

    /**
     * Constructor.
     * @param position of the figure
     */
    public Bishop(Cell position) {
        super(position);
    }

    /**
     * Returns the way to the destination cell.
     * @param dst destination cell
     * @return array that represents the way
     * @throws ImpossibleMoveException
     */
    @Override
    Cell[] way(Cell dst) throws ImpossibleMoveException {
        int currentPositionRowIndex = super.getPosition().getRowIndex();
        int currentPositionColumnIndex = super.getPosition().getColumnIndex();
        Cell[] way = new Cell[8];
        int wayIndex = 0;
        if (Math.abs(currentPositionRowIndex - dst.getRowIndex()) == Math.abs(currentPositionColumnIndex - dst.getColumnIndex())) {
            int wayLength = Math.abs(currentPositionRowIndex - dst.getRowIndex());
            int wayRowIndex;
            int wayColumnIndex;
            boolean xAsc = currentPositionRowIndex < dst.getRowIndex();
            boolean yAsc = currentPositionColumnIndex < dst.getColumnIndex();
            if (xAsc) {
                wayRowIndex = currentPositionRowIndex + 1;
            } else {
                wayRowIndex = currentPositionRowIndex - 1;
            }
            if (yAsc) {
                wayColumnIndex = currentPositionColumnIndex + 1;
            } else {
                wayColumnIndex = currentPositionColumnIndex - 1;
            }
            do {
                way[wayIndex++] = new Cell(wayRowIndex, wayColumnIndex);
                if (xAsc) {
                    wayRowIndex++;
                } else {
                    wayRowIndex--;
                }
                if (yAsc) {
                    wayColumnIndex++;
                } else {
                    wayColumnIndex--;
                }
            } while (wayIndex < wayLength);
        } else {
            throw new ImpossibleMoveException();
        }
        return Arrays.copyOf(way, wayIndex);
    }

    /**
     * Creates the new figure on the give cell.
     * @param cell new cell
     * @return the figure
     */
    Figure clone(Cell cell) {
        return new Bishop(cell);
    }
}
