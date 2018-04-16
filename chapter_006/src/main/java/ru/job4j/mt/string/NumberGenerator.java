package ru.job4j.mt.string;

/**
 * Number generator.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 14.04.2018
 */
public class NumberGenerator implements Runnable {
    /**
     * Number to generate.
     */
    private int numberToGenerate;
    /**
     * Delay in milliseconds.
     */
    private int delay;
    /**
     * StringGenetator to a accept generated numbers.
     */
    private NumberAggregator numberAggregator;
    /**
     * Stopped flag.
     */
    private volatile boolean stopped = false;

    /**
     * Constructor.
     *
     * @param numberToGenerate  some int
     * @param delay             delay in ms
     * @param numberAggregator  StringGenerator instance
     */
    public NumberGenerator(int numberToGenerate, int delay, NumberAggregator numberAggregator) {
        this.numberToGenerate = numberToGenerate;
        this.delay = delay;
        this.numberAggregator = numberAggregator;
    }

    /**
     * Overridden run method. Adds ints into StringGenerator until stop flag will be set.
     */
    @Override
    public void run() {
        while (!stopped) {
            this.numberAggregator.addInt(this.numberToGenerate);
            try {
                Thread.sleep(this.delay);
            } catch (InterruptedException e) {
                this.stopped = true;
                System.err.println("Int generator was interrupted.");
            }
        }
    }
}
