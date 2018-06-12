package ru.job4j.tracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Initialize database.
 *
 * @author alexey
 * @version 05.06.2018
 */
public class DbInitializer {
    /**
     * Check table existence statement.
     */
    private static final String CHECK_TABLE_QUERY = "SELECT count(name) FROM sqlite_master WHERE type = 'table' AND name = 'items'";
    /**
     * Connection.
     */
    private Connection connection;
    /**
     * Settings reader.
     */
    private SettingsReader settingsReader;

    /**
     * Constructor.
     *
     * @param connection connection object
     * @param reader     reader object
     */
    public DbInitializer(Connection connection, SettingsReader reader) {
        this.connection = connection;
        this.settingsReader = reader;
    }

    /**
     * Runs initialization process.
     *
     * @throws SQLException SQL exception
     */
    public void init() throws SQLException {
        if (!isTableExists()) {
            createTables();
        }
    }

    /**
     * Creates the table.
     *
     * @throws SQLException SQL exception
     */
    private void createTables() throws SQLException {
        for (String statement : this.settingsReader.getSqlStatements()) {
            try (PreparedStatement preparedStatement = this.connection.prepareStatement(statement)) {
                System.out.println("Create " + statement);
                preparedStatement.executeUpdate();
            }
        }
    }

    /**
     * Checks table existence.
     *
     * @return true if table exists or false otherwise
     * @throws SQLException SQL exception
     */
    private boolean isTableExists() throws SQLException {
        boolean result = false;
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(CHECK_TABLE_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet != null && resultSet.getInt(1) == 1) {
                result = true;
            }
            return result;
        }
    }
}
