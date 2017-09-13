package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for LoopFinder.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 12.09.17
 */
public class LoopFinderTest {
    /**
     * Nodes.
     */
    private Node<Integer> first, second, third, forth;

    /**
     * Finder.
     */
    private LoopFinder<Integer> integerLoopFinder;

    /**
     * Sets up variables before each test.
     */
    @Before
    public void setUp() {
        this.first = new Node<>(1);
        this.second = new Node<>(2);
        this.third = new Node<>(3);
        this.forth = new Node<>(4);
        this.integerLoopFinder = new LoopFinder<>(first);
    }

    /**
     * Tests list with cycle.
     */
    @Test
    public void whenFoundCycleThenTrue() {
        this.first.setNext(second);
        this.second.setNext(third);
        this.third.setNext(forth);
        this.forth.setNext(first);

        assertThat(this.integerLoopFinder.hasCycle(), is(true));
    }

    /**
     * Tests list without cycle.
     */
    @Test
    public void whenCycleNotFoundThenFalse() {
        this.first.setNext(second);
        this.second.setNext(third);
        this.third.setNext(forth);
        this.forth.setNext(null);

        assertThat(this.integerLoopFinder.hasCycle(), is(false));
    }

}