package ru.job4j.mt.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Hero
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 24.03.2018
 */
public class HeroMover implements Runnable {
    /**
     * Stopped flag.
     */
    private volatile boolean stopped;
    /**
     * Action needed flag.
     */
    private volatile boolean actionNeeded = false;
    /**
     * Direction for next move.
     */
    private volatile Direction nextMoveDirection;
    /**
     * Name field.
     */
    private final String heroName;
    /**
     * Board field.
     */
    private final Board board;
    /**
     * Initial row number.
     */
    private final int initRow;
    /**
     * Initial column number.
     */
    private final int initColumn;

    /**
     * Creates hero.
     *
     * @param heroName heroes name
     * @param board board
     * @param row initial row
     * @param column initial column
     */
    public HeroMover(String heroName, Board board, int row, int column) {
        this.heroName = heroName;
        this.board = board;
        this.initRow = row;
        this.initColumn = column;
    }

    /**
     * Stops hero.
     */
    public void stop() {
        this.stopped = true;
    }

    /**
     * Sets what next action hero should do.
     *
     * @param direction next move direction
     */
    public void setAction(Direction direction) {
        this.nextMoveDirection = direction;
        this.actionNeeded = true;
    }

    /**
     * While stopped flag is not true checks actionNeeded flag and if it is
     * true makes move to the cell on the nextMoveDirection.
     */
    @Override
    public void run() {
        ReentrantLock hero = getInitialPosition();
        int currentRowPosition = this.initRow;
        int currentColumnPosition = this.initColumn;
        while (!this.stopped) {
            if (this.actionNeeded) {
                this.actionNeeded = false;
                int newRowPosition = currentRowPosition + this.nextMoveDirection.getRow();
                int newColumnPosition = currentColumnPosition + this.nextMoveDirection.getColumn();
                ReentrantLock cell = this.board.getCell(newRowPosition, newColumnPosition);
                try {
                    if (cell.tryLock(500, TimeUnit.MILLISECONDS)) {
                        System.out.printf("Character %s moved on %d %d\n", this.heroName, newRowPosition, newColumnPosition);
                        currentRowPosition = newRowPosition;
                        currentColumnPosition = newColumnPosition;
                        hero.unlock();
                        hero = this.board.getCell(currentRowPosition, currentColumnPosition);
                    } else {
                        System.out.printf("Character %s can't moved on %d %d\n", this.heroName, newRowPosition, newColumnPosition);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.printf("Character %s stopped on %d %d.\n", this.heroName, currentRowPosition, currentColumnPosition);
    }

    /**
     * Gets the lock of the initial position.
     *
     * @return lock
     */
    private ReentrantLock getInitialPosition() {
        ReentrantLock cell = this.board.getCell(this.initRow, this.initColumn);
        cell.lock();
        System.out.printf("Character %s set on %d %d\n", this.heroName, this.initRow, this.initColumn);
        return cell;
    }
}
