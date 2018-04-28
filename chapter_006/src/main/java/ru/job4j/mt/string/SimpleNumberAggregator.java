package ru.job4j.mt.string;


/**
 * Simple number aggregation using wait/notify.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 28.04.2018
 */
public class SimpleNumberAggregator implements Aggregator {
    /**
     * Character store.
     */
    private volatile StringBuffer store;
    /**
     * Length of similar char sequence.
     */
    private volatile int maxEqualCharCount;
    /**
     * Counter for character sequence.
     */
    private volatile int currentCharCount = 0;
    /**
     * Constructor.
     */
    public SimpleNumberAggregator(int maxEqualCharCount) {
        this.store = new StringBuffer();
        this.maxEqualCharCount = maxEqualCharCount;
    }

    /**
     * Adds int to the store.
     *
     * @param someInt int
     */
    public synchronized void addInt(int someInt) {

        if (currentCharCount == 0) {
            this.store.append(someInt);
            this.currentCharCount++;
        }

        if (currentCharCount > 0
                && currentCharCount < maxEqualCharCount
                && someInt != Character.getNumericValue(this.store.charAt(this.store.length() - 1))) {
            blockCurrentThread();
        } else {
            this.store.append(someInt);
            this.currentCharCount++;
        }

        if (currentCharCount == maxEqualCharCount) {
            currentCharCount = 0;
            this.notifyAll();
            blockCurrentThread();
        }
    }

    /**
     * Blocks current thread.
     */
    private void blockCurrentThread() {
        try {
            this.wait();
        } catch (InterruptedException e) {
            System.err.println(String.format("Thread %s has been interrupted.", Thread.currentThread().getName()));
            System.exit(-1);
        }
    }

    /**
     * Returns current store as string.
     *
     * @return current store as a string
     */
    public String getCurrentString() {
        return this.store.toString();
    }
}