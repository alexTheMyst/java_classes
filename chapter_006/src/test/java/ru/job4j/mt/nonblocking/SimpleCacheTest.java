package ru.job4j.mt.nonblocking;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Tests for SimpleCache.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 08.03.2018
 */
public class SimpleCacheTest {

    /**
     * Simple cache instance.
     */
    private SimpleCache cache;

    /**
     * DataObject instance.
     */
    private SimpleCache.DataObject dataObject;

    /**
     * Sets up variable before each test.
     */
    @Before
    public void setUp() {
        this.cache = new SimpleCache();
        this.dataObject = new SimpleCache.DataObject("test data");
    }

    /**
     * Checks that you can get the object from cache.
     */
    @Test
    public void whenAddThenGetNotNull() {
        this.cache.add("test_1", this.dataObject);

        assertThat(this.cache.get("test_1"), is(notNullValue()));
    }

    /**
     * Checks that you can delete object from cache.
     */
    @Test
    public void whenAddAndDeleteThenNull() {
        this.cache.add("test_1", this.dataObject);
        this.cache.delete("test_1");

        assertThat(this.cache.get("test_1"), is(nullValue()));
    }

    /**
     * Checks that you can update object in cache.
     */
    @Test
    public void whenUpdateThenDataChange() {
        this.cache.add("test_1", this.dataObject);
        SimpleCache.DataObject objectForUpdate = this.cache.get("test_1");
        objectForUpdate.setData("updated data");
        this.cache.update("test_1", objectForUpdate);

        assertThat(this.cache.get("test_1").getData(), is("updated data"));
    }

    /**
     * Checks that you can't update object with mismatched version.
     */
    @Test(expected = RuntimeException.class)
    public void whenUpdateAlreadyUpdatedThenException() {
        this.cache.add("test_1", this.dataObject);
        SimpleCache.DataObject objectForUpdate = this.cache.get("test_1");
        objectForUpdate.setData("updated data");
        this.cache.update("test_1", objectForUpdate);
        SimpleCache.DataObject newObjectForUpdate = new SimpleCache.DataObject("new updated data");
        this.cache.update("test_1", newObjectForUpdate);
    }
}