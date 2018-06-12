package ru.job4j.tracker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Items repository.
 *
 * @author alexey
 * @version 6/5/18
 */
public class ItemRepository {
    /**
     * Insert item statement.
     */
    public static final String INSERT_INTO_ITEMS_ID_NAME_VALUES = "insert into items (id, name) values (?, ?)";
    /**
     * Select all items statement.
     */
    public static final String SELECT_ID_NAME_FROM_ITEMS = "select id, name from items";
    /**
     * Delete item statement.
     */
    public static final String DELETE_FROM_ITEMS_BY_ID = "delete from items where id = ?";
    /**
     * Select item by name statement.
     */
    public static final String SELECT_ID_NAME_FROM_ITEMS_BY_NAME = "select id, name from items where name = ?";
    /**
     * Select item by name statement.
     */
    public static final String SELECT_ID_NAME_FROM_ITEMS_BY_ID = "select id, name from items where id = ?";
    /**
     * Update item statement.
     */
    public static final String UPDATE_ITEMS_NAME_BY_ID = "update items set name = ? where id = ?";
    /**
     * Connection property.
     */
    private Connection connection;

    /**
     * Constructor.
     *
     * @param connection Connection instance.
     */
    public ItemRepository(Connection connection) {
        this.connection = connection;
    }

    /**
     * Saves given item object to the database.
     *
     * @param item some item object
     * @throws SQLException SQL exception
     */
    public void saveItem(Item item) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(INSERT_INTO_ITEMS_ID_NAME_VALUES)) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Gets all items from the database.
     *
     * @return array of items
     * @throws SQLException SQL exception
     */
    public Item[] getAllItems() throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ID_NAME_FROM_ITEMS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            return getItems(resultSet).toArray(new Item[0]);
        }
    }

    /**
     * Deletes item with the same properties form the database.
     *
     * @param item some item
     * @throws SQLException SQL exception
     */
    public void deleteItem(Item item) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE_FROM_ITEMS_BY_ID)) {
            preparedStatement.setString(1, item.getId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Gets items with the given name
     *
     * @param name item's name
     * @return found items
     * @throws SQLException SQL exception
     */
    public Item[] findByName(String name) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ID_NAME_FROM_ITEMS_BY_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return getItems(resultSet).toArray(new Item[0]);
            }
        }
    }

    /**
     * Finds the item with given id.
     *
     * @param id some id as string
     * @return found item
     * @throws SQLException
     */
    public Item findById(String id) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ID_NAME_FROM_ITEMS_BY_ID)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return getItem(resultSet);
            }
        }
    }

    /**
     * Updates the item.
     *
     * @param updatedItem some updated item
     * @throws SQLException SQL exception
     */
    public void update(Item updatedItem) throws SQLException {
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE_ITEMS_NAME_BY_ID)) {
            preparedStatement.setString(1, updatedItem.getName());
            preparedStatement.setString(2, updatedItem.getId());
            preparedStatement.executeUpdate();
        }
    }

    /**
     * Closes connection to the database.
     *
     * @throws SQLException SQL exception
     */
    public void close() throws SQLException {
        this.connection.close();
    }

    /**
     * Gets all items from given ResultSet.
     *
     * @param resultSet some ResultSet
     * @return list of items
     * @throws SQLException SQL exception
     */
    private List<Item> getItems(ResultSet resultSet) throws SQLException {
        List<Item> result = new LinkedList<>();
        while (resultSet.next()) {
            Item item = getItem(resultSet);
            result.add(item);
        }
        return result;
    }

    /**
     * Gets one item form given ResultSet
     *
     * @param resultSet some ResultSet
     * @return an item
     * @throws SQLException SQL exception
     */
    private Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item(resultSet.getString("NAME"));
        item.setId(resultSet.getString("ID"));
        return item;
    }
}