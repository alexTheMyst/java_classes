package ru.job4j.mt.jmm;

/**
 * Increases counter and print result.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 17.01.2018
 */
public class SimpleRunnable implements Runnable {

    /**
     * Counter variable.
     */
    private final UnsafeCounter counter;

    /**
     * Constructor.
     *
     * @param counter counter instance
     */
    public SimpleRunnable(UnsafeCounter counter) {
        this.counter = counter;
    }

    /**
     * Increases counter and print result.
     */
    @Override
    public void run() {
        this.counter.increaseByOne();
        System.out.printf("thread one counter is %d\n", this.counter.getValue());
    }
}