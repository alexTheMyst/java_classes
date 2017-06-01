package ru.job4j.collections;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Tests for some collections.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.06.17
 */
public class Tests {

    /**
     * PerformanceTester instance.
     */
    private PerformanceTester performanceTester;

    /**
     * Creates new PerformanceTester before each test.
     */
    @Before
    public void setup() {
        this.performanceTester = new PerformanceTester();
    }

    /**
     * Tests ArrayList.
     */
    @Test
    public void testArrayList() {
        final List arrayList = new ArrayList();
        this.performanceTester.add(arrayList, 1000000);
        this.performanceTester.delete(arrayList, 10000);
        assertThat(arrayList.size(), is(990000));
    }

    /**
     * Tests LinkedList.
     */
    @Test
    public void testLinkedList() {
        final List linkedlist = new LinkedList();
        this.performanceTester.add(linkedlist, 1000000);
        this.performanceTester.delete(linkedlist, 10000);
        assertThat(linkedlist.size(), is(990000));

    }

    /**
     * Tests TreeSet.
     */
    @Test
    public void testTreeSet() {
        final Set treeSet = new TreeSet();
        this.performanceTester.add(treeSet, 1000000);
        this.performanceTester.delete(treeSet, 10000);
        assertThat(treeSet.size(), is(990000));

    }
}
