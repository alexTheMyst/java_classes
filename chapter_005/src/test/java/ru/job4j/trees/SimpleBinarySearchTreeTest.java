package ru.job4j.trees;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for simple binary search tree implementation.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.11.17
 */
public class SimpleBinarySearchTreeTest {
    /**
     * Tree for tests.
     */
    private SimpleBinarySearchTree<Integer> tree;

    /**
     * Creates new tree instance before each test.
     */
    @Before
    public void setUp() {
        this.tree = new SimpleBinarySearchTree<>();
    }

    /**
     * Adds one element and checks that iterator returns the same element.
     */
    @Test
    public void whenAddOneToEmptyTreeThenIteratorHasNext() {
        tree.add(1);

        Iterator<Integer> iterator = this.tree.iterator();

        assertThat(iterator.next(), is(1));
    }

    /**
     * Adds two elements and checks that same two elements added into test list.
     */
    @Test
    public void whenAddElementToTreeThenIteratorReturnsSorted() {
        final List<Integer> resultList = Arrays.asList(4, 3, 6);
        final List<Integer> testList = new LinkedList<>();
        this.tree.add(4);
        this.tree.add(6);
        this.tree.add(3);

        Iterator<Integer> iterator = this.tree.iterator();
        testList.add(iterator.next());
        testList.add(iterator.next());
        testList.add(iterator.next());

        assertThat(testList, is(resultList));
    }

    /**
     * Checks NoSuchElement exception.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAddOneToEmptyTreeThenAskTwoGetsException() {
        this.tree.add(1);

        Iterator<Integer> iterator = this.tree.iterator();
        iterator.next();
        iterator.next();
    }

    /**
     * Checks hasNext method.
     */
    @Test
    public void whenAddOneElementThanHasNextIsTrue() {
        this.tree.add(1);

        assertThat(this.tree.iterator().hasNext(), is(true));
    }

    /**
     * Checks hasNext method negative.
     */
    @Test
    public void whenTreeIsBlankThanHasNextIsFalse() {
        assertThat(this.tree.iterator().hasNext(), is(false));
    }
}