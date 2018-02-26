package ru.job4j.mt.wn;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;

/**
 * Test that shows how SimpleThreadPool works.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 07.02.2018
 */
public class SimpleThreadPoolTest {
    /**
     * Thread pool.
     */
    private SimpleThreadPool simpleThreadPool;

    /**
     * Sets up thread pool.
     */
    @Before
    public void setUp() {
        this.simpleThreadPool = new SimpleThreadPool();
        this.simpleThreadPool.start();
    }

    @After
    public void tearDown() {
        this.simpleThreadPool.stop();
    }

    /**
     * Creates tasks and send them to the pool.
     *
     * @throws InterruptedException exception.
     */
    @Test
    public void name() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            System.out.println("I = " + i);
            Work work = new Work(i);
            simpleThreadPool.add(work);
        }
    }
}