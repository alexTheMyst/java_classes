package ru.job4j.mt.wn;

/**
 * Thread implementation for the SimpleThreadPool.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 07.02.2018
 */
public class SimpleThread extends Thread {
    /**
     * Queue for the work.
     */
    private final SimpleBlockingQueue<Work> blockingQueue;

    /**
     * Constructor.
     *
     * @param blockingQueue blocking queue implementation
     */
    public SimpleThread(SimpleBlockingQueue<Work> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    /**
     * Runs work.
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Work work = this.blockingQueue.pop();
                work.run();
            } catch (InterruptedException e) {
                System.out.println("Thread " + currentThread().getName() + " has been interrupted.");
            }
        }
    }
}
