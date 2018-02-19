package ru.job4j.mt.wn;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple thread pool implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 07.02.2018
 */
public class SimpleThreadPool {
    /**
     * Queue for the work.
     */
    private final SimpleBlockingQueue<Work> blockingQueue;
    /**
     * Threads container.
     */
    private final List<SimpleThread> threads;
    /**
     * CPU count.
     */
    private final int cpuCount;

    /**
     * Constructor.
     */
    public SimpleThreadPool() {
        System.out.println("Thread pool constructor started.");
        this.cpuCount = Runtime.getRuntime().availableProcessors();
        this.blockingQueue = new SimpleBlockingQueue<>();
        this.threads = new ArrayList<>(this.cpuCount);
    }

    /**
     * Adds work to the thread pool.
     *
     * @param work work implementation.
     * @throws InterruptedException exception
     */
    public void add(Work work) throws InterruptedException {
        this.blockingQueue.addElement(work);
    }

    /**
     * Starts thread pool.
     */
    public synchronized void start() {
        if (this.threads.size() != this.cpuCount) {
            System.out.println("Thread pool start method started");
            for (int i = 0; i < this.cpuCount; i++) {
                SimpleThread simpleThread;
                simpleThread = new SimpleThread(this.blockingQueue);
                simpleThread.start();
                this.threads.add(simpleThread);
            }
        }
    }

    /**
     * Stops thread pool.
     */
    public synchronized void stop() {
        System.out.println("Thread pool stop method started");
        for (SimpleThread simpleThread : this.threads) {
            try {
                simpleThread.setCanceled();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
