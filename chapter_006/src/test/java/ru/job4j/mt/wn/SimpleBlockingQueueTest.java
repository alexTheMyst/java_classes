package ru.job4j.mt.wn;

import org.junit.Before;
import org.junit.Test;

/**
 * Simple bounded blocking queue tests.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.02.2018
 */
public class SimpleBlockingQueueTest {
    /**
     * Simple blocking queue instance.
     */
    private SimpleBlockingQueue blockingQueue;
    /**
     * Producer.
     */
    private Producer producer;
    /**
     * Consumer.
     */
    private Consumer consumer;

    /**
     * Sets up variables.
     */
    @Before
    public void setUp() {
        this.blockingQueue = new SimpleBlockingQueue();
        this.producer = new Producer(this.blockingQueue, 10);
        this.consumer = new Consumer(this.blockingQueue, 10);
    }

    /**
     * Application demo.
     *
     * @throws InterruptedException exception
     */
    @Test
    public void appDemo() throws InterruptedException {
        Thread prod = new Thread(this.producer);
        Thread cons = new Thread(this.consumer);

        cons.start();
        prod.start();

        cons.join();
    }
}