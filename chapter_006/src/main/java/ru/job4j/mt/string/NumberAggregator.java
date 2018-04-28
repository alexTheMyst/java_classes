package ru.job4j.mt.string;


import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Aggregate given ints into the string.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.04.2018
 */
public class NumberAggregator implements Aggregator {
    /**
     * Lock.
     */
    private ReentrantLock reentrantLock = new ReentrantLock();
    /**
     * Store.
     */
    private StringBuffer store;
    /**
     * Counter to control amount of repeated numbers.
     */
    private AtomicInteger repCount = new AtomicInteger(0);
    /**
     * Previous int storage.
     */
    private int previousValue = Integer.MAX_VALUE;

    /**
     * Constructor.
     */
    public NumberAggregator() {
        this.store = new StringBuffer();
    }

    /**
     * Adds given int to the store.
     *
     * @param number some int.
     */
    public void addInt(int number) {
        //base case
        if (this.reentrantLock.isHeldByCurrentThread()) {
            appendNumber(number);

            //unlock case
            if (this.repCount.get() == 10) {
                this.repCount.set(0);
                this.previousValue = number;
                this.reentrantLock.unlock();
            }
        }

        //lock acquire case
        if (this.repCount.get() == 0 && this.previousValue != number && this.reentrantLock.tryLock()) {
            appendNumber(number);
        }
    }

    /**
     * Returns store as a string.
     *
     * @return store as string
     */
    public String getCurrentString() {
        return this.store.toString();
    }

    /**
     * Appends number and increments counter.
     *
     * @param number some int
     */
    private void appendNumber(int number) {
        this.repCount.incrementAndGet();
        this.store.append(number);
    }
}