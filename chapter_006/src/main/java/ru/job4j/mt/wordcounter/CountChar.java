package ru.job4j.mt.wordcounter;

import java.util.List;

/**
 * Character counter.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 15.01.2018
 */
public class CountChar implements Runnable {

    /**
     * Strings to count characters.
     */
    private final List<String> strings;

    /**
     * Counter.
     */
    private int counter;

    /**
     * Constructor.
     *
     * @param strings strings
     */
    public CountChar(List<String> strings) {
        this.strings = strings;
    }

    /**
     * Overridden run method.
     */
    @Override
    public void run() {
        for (String string : strings) {
            for (char character : string.toCharArray()) {
                if (character != ' ') {
                    counter++;
                }
            }
            System.out.printf("Found %d characters.\n", counter);
            if (Thread.interrupted()) {
                System.err.println("Thread has been interrupted!");
                break;
            }
        }
    }
}
