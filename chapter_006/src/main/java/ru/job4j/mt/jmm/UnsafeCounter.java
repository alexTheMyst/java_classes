package ru.job4j.mt.jmm;

/**
 * Counter implementation.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 17.01.2018
 */
public class UnsafeCounter {

    /**
     * Counters value.
     */
    private int value;

    /**
     * Constructor.
     *
     * @param value some value
     */
    public UnsafeCounter(int value) {
        this.value = value;
    }

    /**
     * Getter.
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Increases value by one.
     */
    public void increaseByOne() {
        this.value = this.value + 1;
    }
}