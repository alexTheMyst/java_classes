package ru.job4j.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Simple map tests.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 18.10.17
 */
public class SimpleMapTest {
    /**
     * Map for tests.
     */
    private SimpleMap<Integer, String> map;

    /**
     * Initializes the map before each test.
     *
     * @throws Exception
     */
    @Before
    public void setUp() {
        map = new SimpleMap<>();
    }

    /**
     * Inserts one entity and checks that delete from map with the same key returns true.
     */
    @Test
    public void whenInsertOneThenDeleteReturnsTrue() {
        map.insert(1, "test1");

        assertThat(map.delete(1), is(true));
    }

    /**
     * Inserts one entity and checks that get from map with the same key returns expected result.
     */
    @Test
    public void whenInsertOneThenGetReturnsValue() {
        String value = "test";

        map.insert(1, value);

        assertThat(map.get(1), is("test"));
    }

    /**
     * Adds ten entities to map and checks that iterator returns the same entities.
     */
    @Test
    public void whenInsertTenEntitiesThenIteratorReturnsSameTen() {
        List<SimpleMap.Entity> entities = new LinkedList<>();
        List<SimpleMap.Entity> result = new LinkedList<>();
        String valuePrefix = "test";

        for (int i = 0; i < 10; i++) {
            entities.add(new SimpleMap.Entity<>(i, valuePrefix + i));
            this.map.insert(i, valuePrefix + i);
        }
        Iterator<SimpleMap.Entity<Integer, String>> iterator = this.map.iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }

        assertThat(result, is(entities));
    }

    /**
     * Tests that next() returns NoSuchElementException if nothing to return.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenAskNextOnEmptyMapThenReturnsException() {
        this.map.iterator().next();
    }
}