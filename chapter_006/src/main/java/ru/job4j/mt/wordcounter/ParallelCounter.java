package ru.job4j.mt.wordcounter;

import java.util.List;

/**
 * Counts words nad spaces in parallel threads.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 22.12.2017
 */
public class ParallelCounter {

    /**
     * Path to file for parse.
     */
    private final String pathToFile;

    /**
     * Constructor.
     *
     * @param pathToFile path to file as a string
     */
    private ParallelCounter(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    /**
     * Runs threads.
     *
     * @throws InterruptedException interrupted exception
     */
    private void runParallelCount() throws InterruptedException {
        SimpleFileReader reader = new SimpleFileReader(this.pathToFile);
        List<String> strings = reader.readFile();

        SpacesCounter spacesCounter = new SpacesCounter(strings);
        WordsCounter wordsCounter = new WordsCounter(strings);

        Thread spaceCountThread = new Thread(spacesCounter);
        Thread wordsCountThread = new Thread(wordsCounter);
        Banner startBanner = new Banner("Program started.");
        Banner finishBanner = new Banner("Program finished.");
        Thread startBannerThread = new Thread(startBanner);
        Thread finishBannerThread = new Thread(finishBanner);

        startBannerThread.start();
        startBannerThread.join();

        spaceCountThread.start();
        wordsCountThread.start();
        spaceCountThread.join();
        wordsCountThread.join();

        finishBannerThread.start();
        finishBannerThread.join();
    }

    /**
     * Main method.
     *
     * @param args arguments
     * @throws InterruptedException interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Wrong argument number. Please run program with one argument - full file path.");
        } else {
            ParallelCounter parallelCounter = new ParallelCounter(args[0]);
            parallelCounter.runParallelCount();
        }
    }
}