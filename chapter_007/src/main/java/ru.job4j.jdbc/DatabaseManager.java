package ru.job4j.jdbc;

import javax.xml.bind.JAXBException;
import javax.xml.transform.*;
import java.io.File;
import java.sql.*;

/**
 * Contains operations with a database.
 *
 * @author alexey
 * @version 25.05.18
 */
public class DatabaseManager {
    /**
     * Query to check that table exists.
     */
    private static final String CHECK_TABLE_QUERY = "SELECT name FROM sqlite_master WHERE type='table' AND name= ?";
    /**
     * Create table statement.
     */
    private static final String CREATE_TABLE_STATEMENT = "CREATE TABLE %s (FIELD INT)";
    /**
     * Truncate table statement.
     */
    private static final String TRUNCATE_TABLE_STATEMENT = "DELETE FROM %s";
    /**
     * Insert data statement.
     */
    private static final String INSERT_STATEMENT = "INSERT INTO %s values (?)";
    /**
     * Select all rows query.
     */
    private static final String SELECT_ALL_ROWS = "select field from %s";
    /**
     * Table name.
     */
    private static final String TABLE_NAME = "test";
    /**
     * Connection object.
     */
    private Connection connection;
    /**
     * Rows count.
     */
    private int rowsCount;
    /**
     * Path where the database located.
     */
    private String databasePath;
    /**
     * Name of the database file.
     */
    private String databaseFileName;
    /**
     * XmlProcessor instance.
     */
    private XmlProcessor xmlProcessor;

    /**
     * Runs all action.
     *
     * @throws SQLException         sql exception
     * @throws JAXBException        jaxb exception
     * @throws TransformerException transformer exception
     */
    public void run() throws SQLException, JAXBException, TransformerException {
        initDatabase();
        insertRows();
        File fileOne = this.xmlProcessor.createFirstXml(createEntryContainer());
        File fileTwo = this.xmlProcessor.createSecondXmlWithXslt(fileOne);
        String resultXml = this.xmlProcessor.getTransformedString(fileTwo);
        System.out.println(this.xmlProcessor.getResult(resultXml));
    }

    /**
     * XmlProcessor getter.
     *
     * @return xml processor instance
     */
    public XmlProcessor getXmlProcessor() {
        return xmlProcessor;
    }

    /**
     * XmlProcessor setter.
     *
     * @param xmlProcessor xml processor instance
     */
    public void setXmlProcessor(XmlProcessor xmlProcessor) {
        this.xmlProcessor = xmlProcessor;
    }

    /**
     * RowsCount getter.
     *
     * @return rows count
     */
    public int getRowsCount() {
        return rowsCount;
    }

    /**
     * RowsCount setter.
     *
     * @param rowsCount rows count
     */
    public void setRowsCount(int rowsCount) {
        this.rowsCount = rowsCount;
    }

    /**
     * Database path getter.
     *
     * @return path to the database as a string
     */
    public String getDatabasePath() {
        return this.databasePath;
    }

    /**
     * Database path setter.
     *
     * @param databasePath path to the database as a string
     */
    public void setDatabasePath(String databasePath) {
        this.databasePath = databasePath;
    }

    /**
     * Database file name getter.
     *
     * @return name of tha database file
     */
    public String getDatabaseFileName() {
        return databaseFileName;
    }

    /**
     * Database file setter.
     *
     * @param databaseFileName name of tha database file
     */
    public void setDatabaseFileName(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }

    /**
     * Creates EntryContainer object.
     *
     * @return created EntryContainer
     * @throws SQLException sql exception
     */
    private EntryContainer createEntryContainer() throws SQLException {
        EntryContainer entryContainer = new EntryContainer();
        ResultSet resultSet = selectAllRows();
        while (resultSet.next()) {
            entryContainer.setEntry(resultSet.getInt(1));
        }
        return entryContainer;
    }

    /**
     * Initialize the database.
     *
     * @throws SQLException sql exception
     */
    private void initDatabase() throws SQLException {
        this.connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s%s", this.databasePath, this.databaseFileName));
        this.connection.setAutoCommit(false);
        if (isTableExists()) {
            truncateTable();
        } else {
            createTable();
        }
    }

    /**
     * Checks that table exists.
     *
     * @return true if table with TABLE_NAME exists or false otherwise
     * @throws SQLException
     */
    private boolean isTableExists() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHECK_TABLE_QUERY);
        preparedStatement.setString(1, TABLE_NAME);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    /**
     * Creates table with TABLE_NAME.
     *
     * @throws SQLException sql exception
     */
    private void createTable() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(CREATE_TABLE_STATEMENT, TABLE_NAME));
        preparedStatement.executeUpdate();
        this.connection.commit();
    }

    /**
     * Truncates the table with TABLE_NAME.
     *
     * @throws SQLException sql exception
     */
    private void truncateTable() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(String.format(TRUNCATE_TABLE_STATEMENT, TABLE_NAME));
        preparedStatement.executeUpdate();
        this.connection.commit();
    }

    /**
     * Inserts rows in the table.
     *
     * @throws SQLException sql exception
     */
    private void insertRows() throws SQLException {
        PreparedStatement preparedStatement = this.connection.prepareStatement(String.format(INSERT_STATEMENT, TABLE_NAME));
        for (int i = 0; i < this.rowsCount; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.executeUpdate();
        }
        this.connection.commit();
    }

    /**
     * Returns all rows from table with TABLE_NAME.
     *
     * @return all rows as a ResultSet
     * @throws SQLException
     */
    private ResultSet selectAllRows() throws SQLException {
        Statement statement = this.connection.createStatement();
        return statement.executeQuery(String.format(SELECT_ALL_ROWS, TABLE_NAME));
    }
}