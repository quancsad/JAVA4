package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductCategoryEntitySchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testCategoryTableExists() throws SQLException {
        // Check if the 'category' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "CATEGORY", null);  // Check for 'CATEGORY' table
            assertTrue(tables.next(), "Table 'category' does not exist.");
        }
    }

    @Test
    public void testProductTableExists() throws SQLException {
        // Check if the 'product' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "PRODUCT", null);  // Check for 'PRODUCT' table
            assertTrue(tables.next(), "Table 'product' does not exist.");
        }
    }

    @Test
    public void testCategoryHasIdColumn() throws SQLException {
        // Check if 'id' column exists in the 'category' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "CATEGORY", null);

            boolean idExists = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("ID".equalsIgnoreCase(columnName)) {
                    idExists = true;
                    break;
                }
            }

            assertTrue(idExists, "Column 'id' does not exist in 'category' table.");
        }
    }

    @Test
    public void testProductHasCategoryForeignKey() throws SQLException {
        // Check if 'product' table has a foreign key to 'category' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, "PRODUCT");  // Foreign keys for 'product' table

            boolean categoryForeignKeyExists = false;
            while (foreignKeys.next()) {
                String fkTable = foreignKeys.getString("PKTABLE_NAME");
                String fkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                if ("CATEGORY".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    categoryForeignKeyExists = true;
                    break;
                }
            }

            assertTrue(categoryForeignKeyExists, "Foreign key from 'product' to 'category' does not exist.");
        }
    }

    @Test
    public void testProductHasCategoryColumn() throws SQLException {
        // Check if 'product' table has 'category_id' column (foreign key column)
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "PRODUCT", null);

            boolean categoryIdExists = false;
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("CATEGORY_ID".equalsIgnoreCase(columnName)) {
                    categoryIdExists = true;
                    break;
                }
            }

            assertTrue(categoryIdExists, "Column 'category_id' does not exist in 'product' table.");
        }
    }

    @Test
    public void testProductColumnTypes() throws SQLException {
        // Check column types for 'id', 'product_name', and 'category_id'
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "PRODUCT", null);

            boolean correctTypes = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String columnType = columns.getString("TYPE_NAME");

                // Check column types
                if ("ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("PRODUCT_NAME".equalsIgnoreCase(columnName) && "VARCHAR".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
                if ("CATEGORY_ID".equalsIgnoreCase(columnName) && "BIGINT".equalsIgnoreCase(columnType)) {
                    correctTypes = true;
                }
            }

            assertTrue(correctTypes, "One or more columns in 'product' table have incorrect data types.");
        }
    }
}