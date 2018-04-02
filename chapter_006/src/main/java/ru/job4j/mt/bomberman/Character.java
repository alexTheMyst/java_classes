package ru.job4j.mt.bomberman;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Character representation.
 *
 * @author alexey
 * @version 20.03.18
 */
public class Character implements Runnable {
    /**
     * Stopped flag.
     */
    private volatile boolean stopped;
    /**
     * Action requested flag.
     */
    private volatile boolean actionRequested = false;
    /**
     * Direction for next movement.
     */
    private volatile Direction nextMoveDirection;
    /**
     * Heroes name.
     */
    private final String heroName;
    /**
     * Board.
     */
    private final Board board;
    /**
     * Current boards cell.
     */
    private final ReentrantLock currentCell;

    /**
     * Constructor.
     *
     * @param heroName    name as a string
     * @param board       board
     * @param currentCell cell
     */
    Character(String heroName, Board board, ReentrantLock currentCell) {
        this.heroName = heroName;
        this.board = board;
        this.currentCell = currentCell;
    }

    /**
     * Sets stopped flag.
     */
    @SuppressWarnings("unused")
    void stop() {
        this.stopped = true;
    }

    /**
     * Sets next move direction.
     *
     * @param direction direction.
     */
    void setAction(Direction direction) {
        this.nextMoveDirection = direction;
        this.actionRequested = true;
    }

    /**
     * Overridden runnable.
     */
    @Override
    public void run() {
        ReentrantLock position = getInitialPosition();
        while (!this.stopped) {
            if (this.actionRequested) {
                this.actionRequested = false;
                ReentrantLock newPosition = getNewPosition(position);
                try {
                    position = move(position, newPosition);
                } catch (InterruptedException e) {
                    this.stopped = true;
                    System.err.printf("Hero %s has been interrupted.", this.heroName);
                }
            }
        }
    }

    /**
     * Moves from position to newPosition.
     *
     * @param position    start position
     * @param newPosition finish position
     * @return locked current cell
     * @throws InterruptedException exception
     */
    private ReentrantLock move(ReentrantLock position, ReentrantLock newPosition) throws InterruptedException {
        /*
      Default timeout.
     */
        long timeOut = 500L;
        if (!newPosition.equals(position) && newPosition.tryLock(timeOut, TimeUnit.MILLISECONDS)) {
            position.unlock();
            position = newPosition;
            System.out.printf("Hero %s moves in direction %s.\n", this.heroName, this.nextMoveDirection);
        } else {
            System.out.printf("Hero %s can't move in direction %s.\n", this.heroName, this.nextMoveDirection);
        }
        return position;
    }

    /**
     * Gets initial position.
     *
     * @return locked cell
     */
    private ReentrantLock getInitialPosition() {
        ReentrantLock position = this.currentCell;
        position.lock();
        return position;
    }

    /**
     * Gets new position.
     *
     * @param position start position
     * @return finish position
     */
    private ReentrantLock getNewPosition(ReentrantLock position) {
        ReentrantLock lock = position;
        try {
            lock = this.board.getCellFromDirection(position, nextMoveDirection);
        } catch (IllegalPosition illegalPosition) {
            System.out.printf("Can't getNewPosition with %s", nextMoveDirection.toString());
        }
        return lock;
    }
}