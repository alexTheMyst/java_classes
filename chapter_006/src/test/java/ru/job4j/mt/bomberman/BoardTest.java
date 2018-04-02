package ru.job4j.mt.bomberman;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Tests for bomberman example.
 *
 * @author alexey
 * @version 29.03.18
 */
@SuppressWarnings("SpellCheckingInspection")
public class BoardTest {

    /**
     * Board instance.
     */
    private final Board board = new Board(2, 2);

    /**
     * Setups board before each test.
     */
    @Before
    public void setUp() {
        this.board.initialize();
    }

    /**
     * Checks that cell exists on the board.
     */
    @Test
    public void whenFindExistingCellThenTrue() {
        ReentrantLock cell = board.getCell(0, 0);

        assertThat(board.isCellExists(cell), is(true));
    }

    /**
     * Checks non existing cell.
     */
    @Test
    public void whenFindNonExistingThanFalse() {
        ReentrantLock cell = new ReentrantLock();

        assertThat(board.isCellExists(cell), is(false));
    }

    /**
     * Checks that movement works.
     *
     * @throws IllegalPosition when position is out of board
     */
    @Test
    public void whenMoveToRightPLaceThenTrue() throws IllegalPosition {
        ReentrantLock cell = board.getCell(0, 0);

        assertThat(board.getCellFromDirection(cell, Direction.DOWN), is(board.getCell(1, 0)));
    }

    /**
     * Checks that ont existing cell throws exception.
     *
     * @throws IllegalPosition when position is out of board
     */
    @Test(expected = IllegalPosition.class)
    public void whenMoveFromBlankCellThenTrue() throws IllegalPosition {
        assertThat(board.getCellFromDirection(new ReentrantLock(), Direction.DOWN), is(board.getCell(1, 0)));
    }

    /**
     * Sets character on the board and checks that position is occupied.
     *
     * @throws InterruptedException when position is out of board
     */
    @Test
    public void whenSetupAndGetCellLockThenTrue() throws InterruptedException {
        ReentrantLock cell = this.board.getCell(0, 0);
        Character heroOne = new Character("Hero One", this.board, cell);
        new Thread(heroOne).start();
        Thread.sleep(50);

        assertThat(board.getCell(0, 0).isLocked(), is(true));
    }

    /**
     * Sets and moves the character and checks cells.
     *
     * @throws InterruptedException when position is out of board
     */
    @Test
    public void whenMoveAndCheckThenTrue() throws InterruptedException {
        ReentrantLock cell = this.board.getCell(0, 0);
        Character heroOne = new Character("Hero One", this.board, cell);
        Thread thread = new Thread(heroOne);
        thread.start();
        heroOne.setAction(Direction.DOWN);
        Thread.sleep(100);

        assertThat(board.getCell(1, 0).isLocked(), is(true));
        assertThat(board.getCell(0, 0).isLocked(), is(false));
        assertThat(board.getCell(1, 1).isLocked(), is(false));
        assertThat(board.getCell(0, 1).isLocked(), is(false));
    }

    /**
     * Sets and moves the character twice and checks cells.
     *
     * @throws InterruptedException when position is out of board
     */
    @Test
    public void whenMakeTwoMovesAndCheckThenTrue() throws InterruptedException {
        ReentrantLock cell = this.board.getCell(0, 0);
        Character heroOne = new Character("Hero One", this.board, cell);
        Thread thread = new Thread(heroOne);
        thread.start();
        heroOne.setAction(Direction.DOWN);
        Thread.sleep(50);
        heroOne.setAction(Direction.RIGHT);
        Thread.sleep(50);

        assertThat(board.getCell(0, 0).isLocked(), is(false));
        assertThat(board.getCell(1, 0).isLocked(), is(false));
        assertThat(board.getCell(1, 1).isLocked(), is(true));
        assertThat(board.getCell(0, 1).isLocked(), is(false));
    }

    /**
     * Sets and moves two characters and checks cells.
     *
     * @throws InterruptedException when position is out of board
     */
    @Test
    public void whenTwoHeroesMovesThenTrue() throws InterruptedException {
        ReentrantLock cellOne = this.board.getCell(0, 0);
        ReentrantLock cellTwo = this.board.getCell(0, 1);
        Character heroOne = new Character("Hero One", this.board, cellOne);
        Character heroTwo = new Character("Hero Two", this.board, cellTwo);
        Thread threadOne = new Thread(heroOne);
        Thread threadTwo = new Thread(heroTwo);
        threadOne.start();
        threadTwo.start();
        heroOne.setAction(Direction.DOWN);
        heroTwo.setAction(Direction.DOWN);
        Thread.sleep(50);
        heroOne.setAction(Direction.RIGHT);
        heroTwo.setAction(Direction.LEFT);
        Thread.sleep(1200);

        assertThat(board.getCell(0, 0).isLocked(), is(false));
        assertThat(board.getCell(1, 0).isLocked(), is(true));
        assertThat(board.getCell(1, 1).isLocked(), is(true));
        assertThat(board.getCell(0, 1).isLocked(), is(false));
    }
}