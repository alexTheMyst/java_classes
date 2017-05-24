package ru.job4j.chess;

/**
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public abstract class Figure {
    /**
     * Current cell.
     */
    private final Cell position;

    /**
     * Constructor.
     * @param position cell position
     */
    public Figure(Cell position) {
        this.position = position;
    }

    /**
     * Returns the way to the destination cell.
     * @param dst destination
     * @return path as a cell array
     * @throws ImpossibleMoveException impossible move
     */
    abstract Cell[] way(Cell dst) throws ImpossibleMoveException;

    /**
     * Creates the new figure on a new cell.
     * @param cell new cell
     * @return the figure
     */
    abstract Figure clone(Cell cell);

    /**
     * Getter.
     * @return cell
     */
    public Cell getPosition() {
        return position;
    }
}
