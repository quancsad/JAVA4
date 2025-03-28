package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserUserProfileEntitySchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testUserTableExists() throws SQLException {
        // Check if the 'user' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "USERS", null);  // Check for 'USER' table
            assertTrue(tables.next(), "Table 'users' does not exist.");
        }
    }

    @Test
    public void testUserProfileTableExists() throws SQLException {
        // Check if the 'user_profile' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "USER_PROFILE", null);  // Check for 'USER_PROFILE' table
            assertTrue(tables.next(), "Table 'user_profile' does not exist.");
        }
    }

    @Test
    public void testUserProfileHasUserForeignKey() throws SQLException {
        // Check if 'user_profile' table has a foreign key to 'user' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, "USER_PROFILE");  // Foreign keys for 'user_profile' table

            boolean userForeignKeyExists = false;
            while (foreignKeys.next()) {
                String fkTable = foreignKeys.getString("PKTABLE_NAME");
                String fkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                if ("USERS".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    userForeignKeyExists = true;
                    break;
                }
            }

            assertTrue(userForeignKeyExists, "Foreign key from 'user_profile' to 'user' does not exist.");
        }
    }

    @Test
    public void testUserProfileHasUserIdColumn() throws SQLException {
        // Check if 'user_profile' table has 'user_id' column (foreign key column)
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "USER_PROFILE", null);

            boolean userIdExists = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("USER_ID".equalsIgnoreCase(columnName)) {
                    userIdExists = true;
                    break;
                }
            }

            assertTrue(userIdExists, "Column 'user_id' does not exist in 'user_profile' table.");
        }
    }

    @Test
    public void testUserProfileColumnTypes() throws SQLException {
        // Check column types for 'id', 'bio', and 'user_id'
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "USER_PROFILE", null);

            boolean correctTypes = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");

                // Check column types
                if ("ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("BIO".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("USER_ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
            }

            assertTrue(correctTypes, "One or more columns in 'user_profile' table have incorrect data types.");
        }
    }
}