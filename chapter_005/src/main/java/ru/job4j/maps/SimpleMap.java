package ru.job4j.maps;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple map implementation.
 *
 * @param <K> key type
 * @param <V> value type
 * @author Alexey Aleshin
 * @version $id$
 * @since 18.10.17
 */
public class SimpleMap<K, V> implements Iterable<SimpleMap.Entity<K, V>> {
    /**
     * Default store size.
     */
    private static final int DEFAULT_SIZE = 100;

    /**
     * Represents simple entity.
     *
     * @param <K> key type
     * @param <V> value type
     */
    public static class Entity<K, V> {

        /**
         * The key.
         */
        private final K key;
        /**
         * The value.
         */
        private final V value;

        /**
         * Constructor.
         *
         * @param key   some key
         * @param value some value
         */
        public Entity(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Checks equality with given object.
         *
         * @param o some object
         * @return true if equals and false otherwise
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Entity<?, ?> entity = (Entity<?, ?>) o;

            return (key != null ? key.equals(entity.key) : entity.key == null) && (value != null ? value.equals(entity.value) : entity.value == null);
        }

        /**
         * Calculates hashCode for entity object.
         *
         * @return integer
         */
        @Override
        public int hashCode() {
            int result = key != null ? key.hashCode() : 0;
            result = 31 * result + (value != null ? value.hashCode() : 0);
            return result;
        }

        /**
         * Creates string representation of current object.
         *
         * @return string representation
         */
        @Override
        public String toString() {
            return "Entity{"
                    + key
                    + " => "
                    + value
                    + '}';
        }
    }

    /**
     * Simple iterator implementation.
     */
    private class SimpleMapEntityIterator implements Iterator<Entity<K, V>> {
        /**
         * Last returned bucket index.
         */
        private int lastReturnedEntityIndex = 0;
        /**
         * Returned elements counter.
         */
        private int returnedElementsCounter = 0;

        /**
         * Checks that iterator has next value to return.
         *
         * @return true if iterator has next element or false otherwise
         */
        @Override
        public boolean hasNext() {
            return this.lastReturnedEntityIndex < store.length && returnedElementsCounter < size;
        }

        /**
         * Returns next element.
         *
         * @return next element
         */
        @Override
        public Entity<K, V> next() {
            Entity<K, V> result = null;
            try {
                while (result == null) {
                    result = getEntity(lastReturnedEntityIndex++);
                }
                returnedElementsCounter++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
            return result;
        }
    }

    /**
     * Store.
     */
    private final Object[] store;

    /**
     * Stored object counter.
     */
    private int size = 0;

    /**
     * Default constructor.
     */
    SimpleMap() {
        this(DEFAULT_SIZE);
    }

    /**
     * Constructor.
     *
     * @param size initial array size
     */
    SimpleMap(int size) {
        this.store = new Object[size];
    }

    /**
     * Inserts key-value pair to map.
     *
     * @param key   entity key
     * @param value entity value
     * @return true if bucket is not occupied and false otherwise
     */
    boolean insert(K key, V value) {
        boolean result = false;
        int bucketIndex = getIndexBucket(key);
        if (isBucketEmpty(bucketIndex)) {
            store[bucketIndex] = new Entity<>(key, value);
            this.size++;
            result = true;
        }
        return result;
    }

    /**
     * Get a value for a key.
     *
     * @param key some key
     * @return value for given key or null otherwise
     */
    V get(K key) {
        V result = null;
        int indexBucket = getIndexBucket(key);
        if (!isBucketEmpty(indexBucket)) {
            Entity<K, V> entity = getEntity(indexBucket);
            result = (entity.key.equals(key)) ? entity.value : null;
        }
        return result;
    }

    /**
     * Delete key-value pair with given key.
     *
     * @param key some key
     * @return true if deletion completed or false if entity with such key not found
     */
    boolean delete(K key) {
        boolean result = false;
        int indexBucket = getIndexBucket(key);
        if (!isBucketEmpty(indexBucket) && getEntity(indexBucket).key.equals(key)) {
            this.store[indexBucket] = null;
            this.size--;
            result = true;
        }
        return result;
    }

    /**
     * Creates iterator instance.
     *
     * @return iterator
     */
    public Iterator<Entity<K, V>> iterator() {
        return new SimpleMapEntityIterator();
    }

    /**
     * Gets entity for the index.
     *
     * @param index bucket index
     * @return entity
     */
    @SuppressWarnings("unchecked")
    private Entity<K, V> getEntity(int index) {
        return (Entity<K, V>) this.store[index];
    }

    /**
     * Checks bucket.
     *
     * @param bucketIndex index
     * @return true if bucket with index is empty or false otherwise
     */
    private boolean isBucketEmpty(int bucketIndex) {
        return store[bucketIndex] == null;
    }

    /**
     * Gets index of bucket for given key.
     *
     * @param key some key
     * @return int as result
     */
    private int getIndexBucket(K key) {
        return Math.abs(key.hashCode() % store.length);
    }
}