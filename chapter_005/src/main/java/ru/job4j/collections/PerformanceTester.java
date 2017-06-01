package ru.job4j.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * Tests for insertion and deletion on collections.
 * @author Alexey Aleshin
 * @version $id$
 * @since 01.06.17
 */
class PerformanceTester {

    /**
     * Inserts given number of String instances into given collection.
     * @param collection some collection
     * @param amount strings to insert
     */
    public void add(Collection collection, int amount) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < amount; i++) {
            collection.add(UUID.randomUUID().toString());
        }
        System.out.println(String.format("Add %d elements in %s in %d ms.",
                amount,
                collection.getClass().getName(),
                System.currentTimeMillis() - startTime));
    }

    /**
     * Deletes given number of elements from given collection.
     * @param collection some collection
     * @param amount elements to delete
     */
    public void delete(Collection collection, int amount) {
        long startTime = System.currentTimeMillis();
        int counter = 0;
        Iterator iterator = collection.iterator();
        while (counter < amount && iterator.hasNext()) {
            iterator.next();
            iterator.remove();
            counter++;
        }
        System.out.println(String.format("Delete %d elements from %s in %d ms.",
                amount,
                collection.getClass().getName(),
                System.currentTimeMillis() - startTime));
    }
}
