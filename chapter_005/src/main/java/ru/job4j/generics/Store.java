package ru.job4j.generics;

/**
 * Basic store operations for Base and its inheritors.
 *
 * @param <T> type parameter any class extends Base
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.08.17
 */
public interface Store<T extends Base> {

    /**
     * Adds the unit to the store.
     *
     * @param unit some unit
     */
    void add(T unit);

    /**
     * Updates the unit in the store.
     *
     * @param unit some unit
     * @return true if succeed and false otherwise
     */
    boolean update(T unit);

    /**
     * Deletes the unit from the store.
     *
     * @param unit some unit
     * @return true if succeed and false otherwise
     */
    boolean delete(T unit);
}
