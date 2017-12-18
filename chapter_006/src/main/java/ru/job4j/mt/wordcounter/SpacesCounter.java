package ru.job4j.mt.wordcounter;

import java.util.List;

/**
 * Counts spaces.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 18.12.2017
 */
public class SpacesCounter implements Runnable {

    /**
     * Space char constant.
     */
    private static final char SPACE_CHAR = ' ';

    /**
     * Space char counter.
     */
    private int counter = 0;

    /**
     * Collection of strings.
     */
    private final List<String> strings;

    /**
     * Constructor.
     *
     * @param strings collection of strings
     */
    public SpacesCounter(List<String> strings) {
        this.strings = strings;
    }

    /**
     * Overridden run method.
     */
    @Override
    public void run() {
        count();
    }

    /**
     * Counter getter.
     *
     * @return counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Counts space character in given list collection.
     */
    private void count() {
        for (String str : this.strings) {
            for (char currentChar : str.toCharArray()) {
                if (currentChar == SPACE_CHAR) {
                    this.counter++;
                    System.out.printf("Found %d spaces.\n", this.counter);
                }
            }
        }
    }
}