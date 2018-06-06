package ru.job4j.jdbc;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBException;
import javax.xml.transform.*;
import java.sql.*;

import static org.hamcrest.number.OrderingComparison.greaterThan;
import static org.junit.Assert.assertThat;

/**
 * Integration test.
 *
 * @author alexey
 * @version 25.05.18
 */
public class DatabaseManagerTest {
    /**
     * Number of entities.
     */
    private static final int MAX_NUM = 1_000_000;
    /**
     * Path to store all database and XML files.
     */
    private static final String TEST_DATABASE_PATH = "./src/test/resources/jdbc/";
    /**
     * Database file name.
     */
    private static final String TEST_DATABASE_FILE = "sample.db";

    /**
     * Database manager instance.
     */
    private DatabaseManager databaseManager;
    /**
     * XML processor instance.
     */
    private XmlProcessor xmlProcessor;

    /**
     * Sets up variables.
     *
     * @throws JAXBException exception
     */
    @Before
    public void setUp() throws JAXBException {
        this.databaseManager = new DatabaseManager();
        this.xmlProcessor = new XmlProcessor(TEST_DATABASE_PATH);
    }

    /**
     * Runs program.
     *
     * @throws SQLException exception
     * @throws JAXBException exception
     * @throws TransformerException exception
     */
    @Test
    public void applicationRun() throws SQLException, JAXBException, TransformerException {
        long startMs = System.currentTimeMillis();

        this.databaseManager.setRowsCount(MAX_NUM);
        this.databaseManager.setDatabaseFileName(TEST_DATABASE_FILE);
        this.databaseManager.setDatabasePath(TEST_DATABASE_PATH);
        this.databaseManager.setXmlProcessor(this.xmlProcessor);
        this.databaseManager.run();

        long duration = System.currentTimeMillis() - startMs;
        System.out.println(duration);

        //assertThat(Long.valueOf(50000 - duration), greaterThan(Long.valueOf(0)));
    }
}