package ru.job4j.tracker;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Reads settings from files.
 *
 * @author alexey
 * @version 05.06.2018
 */
public class SettingsReader {
    /**
     * Path to configs.
     */
    public static final String DEFAULT_CONFIG_DIRECTORY = "./src/main/resources/tracker/";
    /**
     * File names with config data.
     */
    public static final String[] FILE_NAMES = {"application.properties", "create_tables.sql"};
    /**
     * Connect string key.
     */
    public static final String DATABASE_CONNECT_STRING_KEY = "app.db.connect.string";
    /**
     * Path to database key.
     */
    private static final String DATABASE_PATH_STRING_KEY = "app.db.connect.path";
    /**
     * Database file name key.
     */
    private static final String DATABASE_FILE_STRING_KEY = "app.db.connect.file";
    /**
     * Properties storage.
     */
    private Map<String, String> properties;
    /**
     * SQL statements storage.
     */
    private List<String> sqlStatements;

    /**
     * Constructor.
     *
     * @throws IOException input output exception
     */
    public SettingsReader() throws IOException {
        this.properties = new HashMap<>();
        this.sqlStatements = new LinkedList<>();
        readFiles();
    }

    /**
     * Reads files.
     *
     * @throws IOException input output exception
     */
    private void readFiles() throws IOException {
        for (String fileName : FILE_NAMES) {
            if (fileName.contains(".properties")) {
                addProperties(fileName);
            } else {
                addSqlStatements(fileName);
            }
        }
    }

    /**
     * Adds sql statements to the property storage.
     *
     * @param fileName
     * @throws IOException input output exception
     */
    private void addSqlStatements(String fileName) throws IOException {
        Path path = Paths.get(DEFAULT_CONFIG_DIRECTORY + fileName);
        List<String> strings = Files.readAllLines(path);
        this.sqlStatements.addAll(strings);
    }

    /**
     * Adds properties to the property storage.
     *
     * @param fileName
     * @throws IOException input output exception
     */
    private void addProperties(String fileName) throws IOException {
        Path path = Paths.get(DEFAULT_CONFIG_DIRECTORY + fileName);
        List<String> strings = Files.readAllLines(path);
        for (String property : strings) {
            if (property.contains("=")) {
                String[] splitedString = property.split("=");

                this.properties.put(splitedString[0], splitedString[1]);
            }
        }
    }

    /**
     * Gets connect string.
     *
     * @return connect string
     */
    public String getConnectTemplate() {
        return this.properties.get(DATABASE_CONNECT_STRING_KEY);
    }

    /**
     * Gets path to the database files.
     *
     * @return path as a string
     */
    public String getDbPath() {
        return this.properties.get(DATABASE_PATH_STRING_KEY);
    }

    /**
     * Gets the name of database file.
     *
     * @return filename as a string
     */
    public String getDbFileName() {
        return this.properties.get(DATABASE_FILE_STRING_KEY);
    }

    /**
     * Returns list of SQL statements.
     *
     * @return list of SQL statements.
     */
    public List<String> getSqlStatements() {
        return this.sqlStatements;
    }
}
