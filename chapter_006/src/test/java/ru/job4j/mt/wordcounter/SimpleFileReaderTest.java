package ru.job4j.mt.wordcounter;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Tests for simple file reader.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 18.12.2017
 */
public class SimpleFileReaderTest {
    /**
     * Path to file.
     */
    private String pathToFile = "src/test/resources/test.txt";

    /**
     * Simple reader instance.
     */
    private SimpleFileReader simpleFileReader;

    /**
     * Prepares variable before each test.
     */
    @Before
    public void setUp() {
        this.simpleFileReader = new SimpleFileReader(this.pathToFile);
    }

    /**
     * Tests that reader returns not blank list.
     */
    @Test
    public void whenFileReaderCreatedWithNonEmptyFileThenGetListSizeGreaterZero() {
        List<String> strings = this.simpleFileReader.readFile();

        assertThat(strings.size(), is(greaterThan(0)));
    }

    /**
     * Tests that in given staring one space character.
     *
     * @throws InterruptedException if thread interrupted
     */
    @Test
    public void whenSpaceCounterCreatedWithOneSpaceThenGetOne() throws InterruptedException {
        List<String> strings = new LinkedList<>();
        strings.add("string one");
        SpacesCounter spacesCounter = new SpacesCounter(strings);

        Thread thread = new Thread(spacesCounter);
        thread.start();
        thread.join();

        assertThat(spacesCounter.getCounter(), is(1));
    }

    /**
     * Tests
     *
     * @throws InterruptedException if thread interrupted
     */
    @Test
    public void whenWordCounterCreatedWithOneSpaceThenGetOne() throws InterruptedException {
        List<String> strings = new LinkedList<>();
        strings.add("string one");
        WordsCounter wordsCounter = new WordsCounter(strings);

        Thread thread = new Thread(wordsCounter);
        thread.start();
        thread.join();

        assertThat(wordsCounter.getCounter(), is(2));
    }

    /**
     * Runs both word and space counters in parallel threads.
     *
     * @throws InterruptedException if thread interrupted
     */
    @Test
    public void runTwoThreads() throws InterruptedException {
        List<String> strings = this.simpleFileReader.readFile();
        SpacesCounter spacesCounter = new SpacesCounter(strings);
        WordsCounter wordsCounter = new WordsCounter(strings);

        Thread spaceCountThread = new Thread(spacesCounter);
        Thread wordsCountThread = new Thread(wordsCounter);
        spaceCountThread.start();
        wordsCountThread.start();
        spaceCountThread.join();
        wordsCountThread.join();
    }

    /**
     * Runs Runs both word and space counters in parallel threads. Shows banner before and after.
     *
     * @throws InterruptedException
     */
    @Test
    public void runWithBanners() throws InterruptedException {
        List<String> strings = this.simpleFileReader.readFile();
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
}