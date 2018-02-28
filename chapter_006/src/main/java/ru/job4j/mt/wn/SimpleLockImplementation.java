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
     * Stores link to lock holder;
     */
    private Object locker;

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
    public void lock(Object locker) throws InterruptedException {
        synchronized (this) {
            while (this.isLocked) {
                this.wait();
            }
            this.locker = locker;
            this.isLocked = true;
        }
    }

    /**
     * Unlocks current lock.
     */
    public void unlock(Object locker) {
        synchronized (this) {
            if (this.locker.equals(locker)) {
                this.locker = null;
                this.isLocked = false;
                notifyAll();
            }
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