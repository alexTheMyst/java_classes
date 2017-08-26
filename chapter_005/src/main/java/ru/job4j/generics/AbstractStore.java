package ru.job4j.generics;

/**
 * Basic Store implementation.
 *
 * @param <T> type parameter any class extends Base
 * @author Alexey Aleshin
 * @version $id$
 * @since 26.08.17
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Sore for entities.
     */
    private SimpleArray<T> store = new SimpleArray<>();

    /**
     * Adds entity to the store.
     *
     * @param unit some entity
     */
    @Override
    public void add(T unit) {
        this.store.add(unit);
    }

    /**
     * Updates entity in the store.
     *
     * @param unit some unit
     * @return true if update successful or false otherwise
     */
    @Override
    public boolean update(T unit) {
        return this.store.update(unit);
    }

    /**
     * Deletes entity from the store.
     *
     * @param unit some unit
     * @return true if delete successful or false otherwise
     */
    @Override
    public boolean delete(T unit) {
        return this.store.delete(unit);
    }
}