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
     * Max queue size.
     */
    @GuardedBy("this")
    private int currentQueueSize;
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
        this.currentQueueSize = 0;
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
            System.out.println("Queue message: addElement started");
            boolean result;
            while (this.currentQueueSize >= this.maxQueueSize) {
                System.out.println("Queue message: reached max queue size");
                this.wait();
            }
            this.currentQueueSize++;
            result = this.queue.add(element);
            if (this.currentQueueSize > 0) {
                this.notify();
            }
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
            System.out.println("Queue message: pop started");

            String result;
            while (this.currentQueueSize == 0) {
                System.out.println("Queue message: queue is empty nothing to consume");
                this.wait();
            }
            this.currentQueueSize--;
            result = this.queue.peek();
            if (this.currentQueueSize < this.maxQueueSize) {
                this.notify();
            }
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
            return this.currentQueueSize;
        }
    }
}