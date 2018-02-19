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
     * Flag represents threads state.
     */
    private volatile boolean canceled;

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
        while (!canceled) {
            try {
                Work work = this.blockingQueue.pop();
                work.run();
            } catch (InterruptedException ie) {
                this.canceled = true;
                System.err.println("Thread has interrupted");
                ie.printStackTrace();
            }

        }
    }

    /**
     * Sets cancelled flag.
     */
    public void setCanceled() {
        System.out.println("Thread has stopped");
        this.canceled = true;
    }
}
