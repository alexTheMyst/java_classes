package ru.job4j.mt.jmm;

import org.junit.Test;

/**
 * Shows synchronization troubles.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 17.01.2018
 */
public class SynchronizationTests {

    /**
     * Tests sows or doesn't race condition.
     *
     * @throws InterruptedException
     */
    @Test
    public void testRace() throws InterruptedException {
        UnsafeCounter unsafeCounter = new UnsafeCounter(1);
        Thread threadOne = new Thread(new SimpleRunnable(unsafeCounter));
        Thread threadTwo = new Thread(new SimpleRunnable(unsafeCounter));

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();
    }
}