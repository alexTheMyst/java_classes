package ru.job4j.iterator;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Tests for iterator of iterators.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 25.07.17
 */
public class IteratorOfIteratorsTest {

    /**
     * Tests iterator of iterators.
     */
    @Test
    public void whenCreateWithSeveralIteratorsReturnOneIterator() {
        final Iterator<Integer> iterator1 = new ArrayList<>(Arrays.asList(1, 2)).iterator();
        final Iterator<Integer> iterator2 = new ArrayList<>(Arrays.asList(3, 4, 5)).iterator();
        final Iterator<Integer> iterator3 = new ArrayList<>(Arrays.asList(6, 7, 8, 9)).iterator();
        final Iterator<Iterator<Integer>> listOfLists = new ArrayList<>(Arrays.asList(iterator1, iterator2, iterator3)).iterator();
        final List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        final List<Integer> testList = new ArrayList<>();
        final IteratorOfIterators iteratorOfIterators = new IteratorOfIterators();

        final Iterator<Integer> integers = iteratorOfIterators.convert(listOfLists);
        while (integers.hasNext()) {
            testList.add(integers.next());
        }

        assertThat(testList, is(list));
    }
}
