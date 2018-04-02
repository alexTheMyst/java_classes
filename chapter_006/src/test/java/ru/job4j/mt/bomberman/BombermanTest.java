package ru.job4j.mt.bomberman;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Bomberman prototype test.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 18.03.2018
 */
public class BombermanTest {
    /**
     * Board field.
     */
    private final Board board = new Board(2, 2);

    /**
     * Initialize new board before each test.
     */
    @Before
    public void setUp() {
        this.board.initialize();
    }

    /**
     * Checks hero on initial position.
     *
     * @throws InterruptedException interrupted exception
     */
    @Test
    public void checkHeroOnInitialPosition() throws InterruptedException {
        HeroMover heroMover = new HeroMover("Test hero", this.board, 0, 0);
        Thread thread = new Thread(heroMover);
        thread.start();
        Thread.sleep(1000);
        heroMover.stop();
    }

    /**
     * Shows how hero moves.
     *
     * @throws InterruptedException interrupted exception
     */
    @Test
    public void showsHeroMovements() throws InterruptedException {
        HeroMover heroMover = new HeroMover("Test hero", this.board, 0, 0);
        Thread thread = new Thread(heroMover);
        thread.start();
        heroMover.setAction(Direction.DOWN);
        Thread.sleep(100);
        heroMover.setAction(Direction.RIGHT);
        Thread.sleep(100);
        heroMover.setAction(Direction.UP);
        Thread.sleep(100);
        heroMover.setAction(Direction.LEFT);
        Thread.sleep(100);
        heroMover.stop();
    }

    /**
     * Show two heroes movements.
     *
     * @throws InterruptedException interrupted exception
     */
    @Test
    public void showsTwoHeroesMovements() throws InterruptedException {
        HeroMover heroMoverOne = new HeroMover("Test hero 1", this.board, 0, 0);
        HeroMover heroMoverTwo = new HeroMover("Test hero 2", this.board, 1, 1);
        Thread threadOne = new Thread(heroMoverOne);
        Thread threadTwo = new Thread(heroMoverTwo);
        threadOne.start();
        threadTwo.start();
        Thread.sleep(100);
        heroMoverOne.setAction(Direction.DOWN);
        heroMoverTwo.setAction(Direction.LEFT);
        Thread.sleep(100);
        heroMoverOne.stop();
        heroMoverTwo.stop();
        Thread.sleep(500);
    }
}