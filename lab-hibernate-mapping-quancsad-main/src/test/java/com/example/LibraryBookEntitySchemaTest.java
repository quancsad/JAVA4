package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LibraryBookEntitySchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testLibraryTableExists() throws SQLException {
        // Check if the 'library' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "LIBRARY", null);  // Check for 'LIBRARY' table
            assertTrue(tables.next(), "Table 'library' does not exist.");
        }
    }

    @Test
    public void testBookTableExists() throws SQLException {
        // Check if the 'book' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "BOOK", null);  // Check for 'BOOK' table
            assertTrue(tables.next(), "Table 'book' does not exist.");
        }
    }

    @Test
    public void testLibraryHasIdColumn() throws SQLException {
        // Check if 'id' column exists in the 'library' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "LIBRARY", null);

            boolean idExists = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("ID".equalsIgnoreCase(columnName)) {
                    idExists = true;
                    break;
                }
            }

            assertTrue(idExists, "Column 'id' does not exist in 'library' table.");
        }
    }

    @Test
    public void testBookHasLibraryForeignKey() throws SQLException {
        // Check if 'book' table has a foreign key to 'library' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, "BOOK");  // Foreign keys for 'book' table

            boolean libraryForeignKeyExists = false;
            while (foreignKeys.next()) {
                String fkTable = foreignKeys.getString("PKTABLE_NAME");
                String fkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                if ("LIBRARY".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    libraryForeignKeyExists = true;
                    break;
                }
            }

            assertTrue(libraryForeignKeyExists, "Foreign key from 'book' to 'library' does not exist.");
        }
    }

    @Test
    public void testBookHasLibraryColumn() throws SQLException {
        // Check if 'book' table has 'library_id' column (foreign key column)
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "BOOK", null);

            boolean libraryIdExists = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("LIBRARY_ID".equalsIgnoreCase(columnName)) {
                    libraryIdExists = true;
                    break;
                }
            }

            assertTrue(libraryIdExists, "Column 'library_id' does not exist in 'book' table.");
        }
    }

    @Test
    public void testBookColumnTypes() throws SQLException {
        // Check column types for 'id', 'title', 'author' and 'library_id'
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "BOOK", null);

            boolean correctTypes = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");

                // Check column types
                if ("ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("TITLE".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("AUTHOR".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("LIBRARY_ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
            }

            assertTrue(correctTypes, "One or more columns in 'book' table have incorrect data types.");
        }
    }
}
