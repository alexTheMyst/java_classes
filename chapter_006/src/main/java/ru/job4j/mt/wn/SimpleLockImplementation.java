package ru.job4j.mt.wn;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Simple lock implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.02.2018
 */
@ThreadSafe
public class SimpleLockImplementation {
    /**
     * Locked flag.
     */
    @GuardedBy("this")
    private boolean isLocked;

    /**
     * Default constructor.
     */
    public SimpleLockImplementation() {
        this.isLocked = false;
    }

    /**
     * Tries to get lock.
     *
     * @throws InterruptedException exception
     */
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (this.isLocked) {
                this.wait();
            }
            this.isLocked = true;
        }
    }

    /**
     * Unlocks current lock.
     */
    public void unlock() {
        synchronized (this) {
            this.isLocked = false;
            notifyAll();
        }
    }

    /**
     * Lock status getter.
     *
     * @return isLocked status
     */
    public synchronized boolean isLocked() {
        return this.isLocked;
    }
}