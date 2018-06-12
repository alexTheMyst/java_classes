package ru.job4j.tracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Tracker is a array wrapper with some additional logic.
 *
 * @author Alexey Aleshin
 * @version $id$
 * @since 23.03.17
 */
public class Tracker implements AutoCloseable {
    /**
     * SettingsReader property.
     */
    private SettingsReader settingsReader;
    /**
     * DbInitializer property.
     */
    private DbInitializer dbInitializer;
    /**
     * Connection property.
     */
    private Connection connection;
    /**
     * ItemStorage property.
     */
    private ItemRepository repository;

    /**
     * Constructor.
     */
    public Tracker() {
        try {
            this.settingsReader = new SettingsReader();
            this.connection = DriverManager.getConnection(String.format(this.settingsReader.getConnectTemplate(),
                    this.settingsReader.getDbPath(),
                    this.settingsReader.getDbFileName()));
            this.dbInitializer = new DbInitializer(this.connection, this.settingsReader);
            this.dbInitializer.init();
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
        this.repository = new ItemRepository(this.connection);
    }

    /**
     * Adds given item to the array.
     *
     * @param item given item
     * @return inserted item
     */
    public Item add(Item item) {
        try {
            this.repository.saveItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    /**
     * Gets copy of the array of items.
     *
     * @return Item[]
     */
    public Item[] findAll() {
        Item[] result = null;
        try {
            result = this.repository.getAllItems();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deletes item from array.
     *
     * @param item to delete
     */
    public void delete(Item item) {
        try {
            this.repository.deleteItem(item);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for items in the array by its name.
     *
     * @param name of item to search
     * @return item with given name
     */
    public Item[] findByName(String name) {
        Item[] result = new Item[1];
        try {
            result = this.repository.findByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Searches item by id.
     *
     * @param id to search
     * @return item with the same id
     */
    public Item findById(String id) {
        Item result = new Item("void");
        try {
            result = this.repository.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Searches for item with the same id as updatedItem and updates found item.
     *
     * @param updatedItem item to update
     */
    public void update(Item updatedItem) {
        try {
            this.repository.update(updatedItem);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes repository.
     */
    @Override
    public void close() {
        try {
            this.repository.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}