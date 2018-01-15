package ru.job4j.mt.wordcounter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Tests for CountChar with Timer.
 *
 * @author Alexey Aleshin
 * @version $id\$
 * @since 15.01.2018
 */
public class CountCharInterruptibleTest {

    /**
     * List of strings for the test.
     */
    private List<String> strings;

    /**
     * Sets up new list before each test.
     */
    @Before
    public void setUp() {
        this.strings = new ArrayList<>(1000);
        for (int i = 0; i < 1000; i++) {
            strings.add(UUID.randomUUID().toString());
        }
    }

    /**
     * Shows that counter works without interruption.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenCounterRunWithNonZeroListThenPrints() throws InterruptedException {
        Thread counterThread = new Thread(new CountChar(this.strings));
        counterThread.start();
        counterThread.join();
    }

    /**
     * Shows that with Timer counter stopped.
     *
     * @throws InterruptedException
     */
    @Test
    public void whenCounterWithTimerThenException() throws InterruptedException {
        Thread counterThread = new Thread(new CountChar(this.strings));
        Thread timerThread = new Thread(new Time(counterThread, 50));
        counterThread.start();
        timerThread.start();
        counterThread.join();
        timerThread.join();

    }
}