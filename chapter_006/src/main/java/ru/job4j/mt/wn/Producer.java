package ru.job4j.mt.wn;

import java.util.UUID;

/**
 * Producer puts some text messages in the given simple blocking queue.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 05.02.2018
 */
class Producer implements Runnable {
    /**
     * Queue to post some messages.
     */
    private final SimpleBlockingQueue blockingQueue;
    /**
     * How many messages post to queue.
     */
    private final int count;

    /**
     * Constructor.
     *
     * @param blockingQueue some blocking queue
     * @param count         messages count
     */
    public Producer(SimpleBlockingQueue blockingQueue, int count) {
        this.blockingQueue = blockingQueue;
        this.count = count;
        System.out.println("Producer started");
    }

    /**
     * Implements producer logic.
     */
    @Override
    public void run() {
        try {
            for (int i = 0; i < this.count; i++) {
                UUID uuid = UUID.randomUUID();
                this.blockingQueue.addElement(uuid.toString());
                System.out.println("Producer message: add element " + uuid.toString());
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
