package ru.job4j.mt.wn;

/**
 * Consumer gets some messages from given simple blocking queue.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.02.2018
 */
class Consumer implements Runnable {
    /**
     * Queue to get some messages.
     */
    private final SimpleBlockingQueue blockingQueue;
    /**
     * How many messages get from queue.
     */
    private final int count;

    /**
     * Constructor.
     *
     * @param blockingQueue some blocking queue
     * @param count         messages count
     */
    public Consumer(SimpleBlockingQueue blockingQueue, int count) {
        this.blockingQueue = blockingQueue;
        this.count = count;
        System.out.println("Consumer started");
    }

    /**
     * Implements consumer logic.
     */
    @Override
    public void run() {
        int counter = 0;
        while (counter < this.count) {
            try {
                Thread.sleep(20);
                System.out.println("Consumer message: checking queue");
                if (this.blockingQueue.getCurrentQueueSize() > 0) {
                    System.out.println("Consumer message: got element " + this.blockingQueue.pop());
                    counter++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
