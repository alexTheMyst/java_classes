package ru.job4j.trees;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Simple tree implementation tests.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.10.17
 */
public class SimpleTreeTest {

    /**
     * The tree for tests.
     */
    private Tree<Integer> tree;

    /**
     * Creates new tree instance before each test.
     */
    @Before
    public void setup() {
        this.tree = new Tree<>();
    }

    /**
     * Adds an element with null parent and this element becomes root.
     */
    @Test
    public void whenAddOneElementWithNullParentToNewTreeGetsTrue() {
        assertThat(this.tree.add(null, 1), is(true));
    }

    /**
     * Adds an element with null parent to existing tree.
     */
    @Test
    public void whenAddElementWithNullParentToExistingTreeGetsFalse() {
        this.tree.add(null, 1);

        assertThat(this.tree.add(null, 1), is(false));
    }

    /**
     * Adds an element to existing tree.
     */
    @Test
    public void whenAddElementToExistingPArentGetsTrue() {
        this.tree.add(null, 1);

        assertThat(this.tree.add(1, 2), is(true));
    }

    /**
     * Adds three elements and get the same amount from iterator.
     */
    @Test
    public void whenAddSeveralElementsIteratorReturnsSameAmount() {
        this.tree.add(null, 1);
        this.tree.add(1, 2);
        this.tree.add(1, 3);

        Iterator<Integer> iterator = this.tree.iterator();
        iterator.next();
        iterator.next();

        assertThat(iterator.next(), is(notNullValue()));
    }

    /**
     * Adds three elements and get four values from iterator.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskIteratorMoreThanNodes() {
        this.tree.add(null, 1);
        this.tree.add(1, 2);
        this.tree.add(1, 3);

        Iterator<Integer> iterator = this.tree.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
    }

    /**
     * Adds element with the already existed value.
     */
    @Test
    public void whenAddNodeWithValueIsAlreadyExistsThenAddReturnsFalse() {
        this.tree.add(null, 1);

        assertThat(this.tree.add(1, 1), is(false));
    }

    /**
     * Creates a tree with height more than three.
     */
    @Test
    public void whenTreeIsHeightMoreThanThree() {
        this.tree.add(null, 50);
        this.tree.add(50, 70);
        this.tree.add(50, 30);
        this.tree.add(30, 15);
        this.tree.add(30, 45);
        this.tree.add(70, 90);
        this.tree.add(70, 60);
        this.tree.add(45, 7);
    }
}