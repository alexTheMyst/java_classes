package ru.job4j.mt.wordcounter;

import java.util.List;

/**
 * Counts words in given list of strings.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 18.12.2017
 */
public class WordsCounter implements Runnable {
    /**
     * Space character constant.
     */
    private static final char SPACE = ' ';

    /**
     * End of line character constant.
     */
    private static final char END_OF_LINE = '\n';

    /**
     * Words counter.
     */
    private int counter = 0;

    /**
     * Collection of strings.
     */
    private final List<String> strings;

    /**
     * Constructor.
     *
     * @param strings list of strings
     */
    public WordsCounter(List<String> strings) {
        this.strings = strings;
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
     * Overridden run method.
     */
    @Override
    public void run() {
        count();
    }

    /**
     * Counts words in string.
     */
    private void count() {
        for (String str : strings) {
            boolean isPreviousCharBlank = (!str.isEmpty()) && str.charAt(0) == SPACE;
            for (char currentChar : str.toCharArray()) {
                if (currentChar == SPACE || currentChar == END_OF_LINE) {
                    if (!isPreviousCharBlank) {
                        wordFound();
                        isPreviousCharBlank = true;
                    }
                } else {
                    isPreviousCharBlank = false;
                }
            }
            if (!isPreviousCharBlank) {
                wordFound();
            }
        }
    }

    /**
     * Increases counter and print the message.
     */
    private void wordFound() {
        counter++;
        System.out.printf("Found %d words\n", counter);
    }
}