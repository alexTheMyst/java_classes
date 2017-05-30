package ru.job4j.chess;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for a chess game.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 11.05.17
 */
public class TestChess {

    /**
     * Tests the board could be created.
     */
    @Test
    public void createBoard() {
        final Board board = new Board();
        assertThat(board, notNullValue());
    }

    /**
     * Tests that cell with figure is occupied.
     */
    @Test
    public void setFigureOnBoard() {
        final Board board = new Board();
        Cell cell00 = board.getCell(0, 0);
        Figure bishop = new Bishop(cell00);
        board.setupFigure(bishop);
        assertThat(board.isCellOccupied(cell00), is(equalTo(true)));
    }

    /**
     * Tests that wrong movement throws ImpossibleMoveException.
     *
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    @Test(expected = ImpossibleMoveException.class)
    public void moveFigureToInvalidPosition() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        final Board board = new Board();
        final Figure bishop = new Bishop(board.getCell(0, 0));
        board.setupFigure(bishop);
        board.move(board.getCell(0, 0), board.getCell(1, 0));
    }

    /**
     * Tests that figure can be moved.
     *
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    @Test
    public void moveFigureToValidPosition() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        final Board board = new Board();
        final Figure bishop = new Bishop(board.getCell(0, 0));
        board.setupFigure(bishop);
        board.move(board.getCell(0, 0), board.getCell(1, 1));
        assertThat(board.isCellOccupied(board.getCell(1, 1)), is(equalTo(true)));
    }

    /**
     * Tests that figure can't pass occupied cell.
     *
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    @Test(expected = OccupiedWayException.class)
    public void moveFigureToOccupiedPosition() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        final Board board = new Board();
        Figure bishopOne = new Bishop(board.getCell(0, 0));
        Figure bishopTwo = new Bishop(board.getCell(1, 1));
        board.setupFigure(bishopOne);
        board.setupFigure(bishopTwo);
        board.move(board.getCell(0, 0), board.getCell(2, 2));
    }

    /**
     * Tests impossibility to move from blank cell.
     *
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    @Test(expected = FigureNotFoundException.class)
    public void tryToMoveFromBlankCell() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        final Board board = new Board();
        board.move(board.getCell(0, 0), board.getCell(1, 1));
    }

    /**
     * Tests movements of the bishop in different directions.
     *
     * @throws OccupiedWayException    figure can't move through occupied cells
     * @throws ImpossibleMoveException figure can't move that way
     * @throws FigureNotFoundException cell doesn't have any figure
     */
    @Test
    public void moveDifferentDirections() throws OccupiedWayException, ImpossibleMoveException, FigureNotFoundException {
        final Board board = new Board();
        final Figure bishop = new Bishop(board.getCell(0, 0));
        board.setupFigure(bishop);
        board.move(board.getCell(0, 0), board.getCell(7, 7));
        board.move(board.getCell(7, 7), board.getCell(3, 3));
        board.move(board.getCell(3, 3), board.getCell(2, 4));
        board.move(board.getCell(2, 4), board.getCell(4, 2));
        assertThat(board.isCellOccupied(board.getCell(4, 2)), is(equalTo(true)));
    }
}
