package ru.job4j.mt.nonblocking;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Simple cache implementation using non blocking algorithms.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 08.03.2018
 */
public class SimpleCache {
    /**
     * Storage.
     */
    private ConcurrentHashMap<String, DataObject> cache;

    /**
     * Constructor.
     */
    public SimpleCache() {
        this.cache = new ConcurrentHashMap<>();
    }

    /**
     * Ads an object to the cache.
     *
     * @param id  object id
     * @param obj object value
     */
    public void add(String id, DataObject obj) {
        this.cache.putIfAbsent(id, obj);
    }

    /**
     * Gets the object with given id.
     *
     * @param id objects' id
     * @return object
     */
    public DataObject get(String id) {
        return this.cache.get(id);
    }

    /**
     * Deletes the object with given id.
     *
     * @param id objects' id
     */
    public void delete(String id) {
        this.cache.remove(id);
    }

    /**
     * Updates the object with given id.
     *
     * @param id         objects' id
     * @param dataObject some object
     */
    public void update(String id, DataObject dataObject) {
        this.cache.computeIfPresent(id, (k, v) -> {
            if (v.getVersion() == dataObject.getVersion()) {
                dataObject.incrementVersion();
            } else {
                throw new RuntimeException("Optimistic lock issue.");
            }
            return dataObject;
        });
    }

    /**
     * Object implementation for caching.
     */
    static class DataObject {
        /**
         * Version of current object instance.
         */
        private int version;

        /**
         * Some data.
         */
        private String data;

        /**
         * Constructor.
         *
         * @param data some data
         */
        public DataObject(String data) {
            this.data = data;
        }

        /**
         * Gets the object version.
         *
         * @return version
         */
        public int getVersion() {
            return version;
        }

        /**
         * Increments objects version.
         */
        private void incrementVersion() {
            synchronized (this) {
                this.version++;
            }
        }

        /**
         * Gets object data.
         *
         * @return data as a string
         */
        public String getData() {
            return data;
        }

        /**
         * Sets object data.
         *
         * @param data some data.
         */
        public void setData(String data) {
            this.data = data;
        }
    }
}