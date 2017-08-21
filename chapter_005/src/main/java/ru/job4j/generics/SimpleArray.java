package ru.job4j.generics;

/**
 * Represents simple container with generic type.
 *
 * @param <E> entity type.
 * @author Alexey Aleshin
 * @version $id$
 * @since 21.08.17
 */
public class SimpleArray<E> {
    /**
     * Index of last stored element.
     */
    private int currentStorageIndex = 0;

    /**
     * Array for element storage.
     */
    private Object[] storage = new Object[10];

    /**
     * Default constructor.
     */
    public SimpleArray() {
    }

    /**
     * Adds an element to array.
     *
     * @param element element to add
     */
    void add(E element) {
        checkAndExtendStorage();
        this.storage[currentStorageIndex++] = element;
    }

    /**
     * Get the element by index.
     *
     * @param index index in array
     * @return element
     */
    @SuppressWarnings("unchecked")
    E get(int index) {
        return (E) storage[index];
    }

    /**
     * Deletes the element form array.
     *
     * @param element to delete
     * @return true if operation successful and false if nothing to do
     */
    boolean delete(E element) {
        boolean result = false;
        for (int i = 0; i < this.currentStorageIndex; i++) {
            if (storage[i].equals(element)) {
                System.arraycopy(storage, i + 1, storage, 0, storage.length - 1);
                currentStorageIndex--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Updates given element in array.
     *
     * @param element to update
     * @return true if update succeeded.
     */
    boolean update(E element) {
        boolean result = false;
        for (int i = 0; i < currentStorageIndex; i++) {
            if (storage[i].equals(element)) {
                storage[i] = element;
                result = true;
            }
        }
        return result;
    }

    /**
     * Checks storage size and extend it if needed.
     */
    private void checkAndExtendStorage() {
        if (this.storage.length == currentStorageIndex) {
            Object[] newStorage = new Object[this.storage.length * 2];
            System.arraycopy(storage, 0, newStorage, 0, this.storage.length);
            this.storage = newStorage;
        }
    }
}
