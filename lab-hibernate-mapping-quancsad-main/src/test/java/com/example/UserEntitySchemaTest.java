package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserEntitySchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate; // Autowire JdbcTemplate for database access

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testUserTableExists() throws SQLException {
        // Check if the 'users' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "USERS", null);  // Case sensitive: 'USERS' in H2 is typically uppercase
            assertTrue(tables.next(), "Table 'users' does not exist.");
        }
    }

    @Test
    public void testUserColumnsExist() throws SQLException {
        // Check if 'id', 'name', 'email' columns exist in the 'users' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "USERS", null);

            boolean idExists = false;
            boolean nameExists = false;
            boolean emailExists = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("ID".equalsIgnoreCase(columnName)) idExists = true;
                if ("NAME".equalsIgnoreCase(columnName)) nameExists = true;
                if ("EMAIL".equalsIgnoreCase(columnName)) emailExists = true;
            }

            assertTrue(idExists, "Column 'id' does not exist.");
            assertTrue(nameExists, "Column 'name' does not exist.");
            assertTrue(emailExists, "Column 'email' does not exist.");
        }
    }

    @Test
    public void testEmailColumnUniqueConstraint() throws SQLException {
        // Check if 'email' column has a UNIQUE constraint
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet indexes = metaData.getIndexInfo(null, null, "USERS", true, false);  // true means unique index

            boolean emailUnique = false;
            while (indexes.next()) {
                String columnName = indexes.getString("COLUMN_NAME");
                if ("EMAIL".equalsIgnoreCase(columnName)) {
                    emailUnique = true;
                    break;
                }
            }

            assertTrue(emailUnique, "Column 'email' does not have a UNIQUE constraint.");
        }
    }

    @Test
    public void testEmailColumnNotNull() throws SQLException {
        // Check if 'email' column has a NOT NULL constraint
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "USERS", null);

            boolean emailNotNull = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String isNullable = columns.getString("IS_NULLABLE");

                if ("EMAIL".equalsIgnoreCase(columnName) && "NO".equalsIgnoreCase(isNullable)) {
                    emailNotNull = true;
                    break;
                }
            }

            assertTrue(emailNotNull, "Column 'email' does not have a NOT NULL constraint.");
        }
    }

    @Test
    public void testColumnTypes() throws SQLException {
        // Check column types for 'id', 'name', and 'email'
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "USERS", null);

            boolean correctTypes = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");

                // Check column types
                if ("ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("NAME".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("EMAIL".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
            }

            assertTrue(correctTypes, "One or more columns have incorrect data types.");
        }
    }
}