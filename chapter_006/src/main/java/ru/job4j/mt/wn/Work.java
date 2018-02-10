package ru.job4j.mt.wn;

/**
 * Work for the SimpleThreadPool.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 07.02.2018
 */
public class Work implements Runnable {
    /**
     * Counter.
     */
    private final int count;

    /**
     * Constructor.
     *
     * @param count some ount
     *
     */
    public Work(int count) {
        this.count = count;
    }

    /**
     * Overridden run method.
     */
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " Thread working with " + this.count);
    }
}
