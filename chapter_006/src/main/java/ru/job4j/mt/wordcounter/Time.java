package ru.job4j.mt.wordcounter;

/**
 * Timer to check other thread.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 15.01.2018
 */
public class Time implements Runnable {

    /**
     * Thread to check.
     */
    final private Thread threadToStop;

    /**
     * Time interval in milliseconds.
     */
    final private long timeIntervalMs;

    /**
     * Constructor.
     *
     * @param threadToStop thread
     * @param timeInterval interval
     */
    public Time(Thread threadToStop, long timeInterval) {
        this.threadToStop = threadToStop;
        this.timeIntervalMs = timeInterval;
    }

    /**
     * Overridden run method.
     */
    @Override
    public void run() {
        try {
            Thread.sleep(timeIntervalMs);
            if (this.threadToStop.isAlive()) {
                this.threadToStop.interrupt();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}