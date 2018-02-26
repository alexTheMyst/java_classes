package ru.job4j.mt.wn;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for SimpleLockImplementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.02.2018
 */
public class SimpleLockImplementationTest {
    /**
     * Simple lock instance.
     */
    private SimpleLockImplementation simpleLockImplementation;

    /**
     * Sets up a new simple lock implementation.
     */
    @Before
    public void setUp() {
        this.simpleLockImplementation = new SimpleLockImplementation();
    }

    /**
     * New simple lock is unlocked.
     */
    @Test
    public void whenNewThreadThenIsUnlocked() {
        assertThat(this.simpleLockImplementation.isLocked(), is(false));
    }

    /**
     * Tests lock status should be isLocked after lock method run.
     *
     * @throws InterruptedException interrupted exception
     */
    @Test
    public void whenLockThenIsLocked() throws InterruptedException {
        this.simpleLockImplementation.lock();

        assertThat(this.simpleLockImplementation.isLocked(), is(true));
    }

    /**
     * Two threads example.
     *
     * @throws InterruptedException interrupted exception
     */
    @Test
    public void exampleWithTwoThreads() throws InterruptedException {
        Thread threadOne = new Thread(new TestLockRunnable(this.simpleLockImplementation));
        Thread threadTwo = new Thread(new TestLockRunnable(this.simpleLockImplementation));
        threadOne.start();
        threadTwo.start();
        threadTwo.join();
    }

    /**
     * Simple Runnable implementation for tests purposes.
     */
    static class TestLockRunnable implements Runnable {
        /**
         * Simple lock instance.
         */
        private SimpleLockImplementation simpleLockImplementation;

        /**
         * Constructor.
         *
         * @param simpleLockImplementation lock
         */
        public TestLockRunnable(SimpleLockImplementation simpleLockImplementation) {
            this.simpleLockImplementation = simpleLockImplementation;
        }

        /**
         * Does some work.
         */
        @Override
        public void run() {
            try {
                this.simpleLockImplementation.lock();
                System.out.println("Thread  "
                        + Thread.currentThread().getName()
                        + " lock status is "
                        + this.simpleLockImplementation.isLocked()
                );
                Thread.sleep(1000);
                this.simpleLockImplementation.unlock();
                System.out.println("Thread  "
                        + Thread.currentThread().getName()
                        + " released lock."
                );

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
