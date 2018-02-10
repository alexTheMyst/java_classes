package ru.job4j.mt.wn;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple bounded blocking queue implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.02.2018
 */
@ThreadSafe
class SimpleBlockingQueue {
    /**
     * Default max queue size.
     */
    private static final int DEFAULT_MAX_QUEUE_SIZE = 3;
    /**
     * Max queue size.
     */
    private final int maxQueueSize;
    /**
     * Elements storage.
     */
    @GuardedBy("this")
    private final Queue<String> queue;

    /**
     * Default constructor.
     */
    public SimpleBlockingQueue() {
        this(DEFAULT_MAX_QUEUE_SIZE);
    }

    /**
     * Creates blocking queue with given size.
     *
     * @param size size of queue
     */
    private SimpleBlockingQueue(int size) {
        this.maxQueueSize = size;
        this.queue = new LinkedList<>();
    }

    /**
     * Adds element to the queue.
     *
     * @param element some element
     * @return true if operation successful
     * @throws InterruptedException exception
     */
    public boolean addElement(String element) throws InterruptedException {
        synchronized (this) {
            boolean result;
            while (this.queue.size() >= this.maxQueueSize) {
                this.wait();
            }
            result = this.queue.add(element);
            this.notifyAll();
            return result;
        }
    }

    /**
     * Pops element form the queue.
     *
     * @return element
     * @throws InterruptedException exception
     */
    public String pop() throws InterruptedException {
        synchronized (this) {

            String result;
            while (this.queue.size() == 0) {
                this.wait();
            }
            result = this.queue.poll();
            this.notifyAll();
            return result;
        }
    }

    /**
     * Current size getter.
     *
     * @return this.currentQueueSize
     */
    public int getCurrentQueueSize() {
        synchronized (this) {
            return this.queue.size();
        }
    }
}