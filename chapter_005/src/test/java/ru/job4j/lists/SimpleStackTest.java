package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Tests for stack and queue implementations.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 10.09.17
 */
public class SimpleStackTest {
    /**
     * Stack.
     */
    private SimpleStack<Integer> stack;

    /**
     * Queue.
     */
    private SimpleQueue<Integer> queue;

    /**
     * Initialize stack and queue before each test.
     */
    @Before
    public void setup() {
        this.stack = new SimpleStack<>();
        this.stack.push(1);
        this.stack.push(2);

        this.queue = new SimpleQueue<>();
        this.queue.push(1);
        this.queue.push(2);
    }

    /**
     * Polls once and check returned element.
     */
    @Test
    public void whenAddTwoEntitiesToStackThenFirstPollReturnSecond() {
        assertThat(stack.poll(), is(2));
    }

    /**
     * Polls twice and check returned element.
     */
    @Test
    public void whenAddTwoEntitiesToStackThenSecondPollReturnFirst() {
        stack.poll();

        assertThat(stack.poll(), is(1));
    }

    /**
     * Polls once and check returned element.
     */
    @Test
    public void whenAddTwoEntitiesToQueueThenFirstPollReturnFirst() {
        assertThat(this.queue.poll(), is(1));
    }

    /**
     * Polls twice and check returned element.
     */
    @Test
    public void whenAddTwoEntitiesToQueueThenSecondPollReturnSecond() {
        this.queue.poll();

        assertThat(this.queue.poll(), is(2));
    }

}