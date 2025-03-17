package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OrderItemElementCollectionSchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testOrderTableExists() throws SQLException {
        // Check if the 'order' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "ORDERS", null);  // Check for 'ORDER' table
            assertTrue(tables.next(), "Table 'orders' does not exist.");
        }
    }

    @Test
    public void testOrderItemsTableExists() throws SQLException {
        // Check if the 'order_items' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "ORDER_ITEMS", null);  // Check for 'order_items' table
            assertTrue(tables.next(), "Table 'order_items' does not exist.");
        }
    }

    @Test
    public void testOrderItemsForeignKeyExists() throws SQLException {
        // Check if the 'order_items' table has a foreign key to the 'order' table
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, "ORDER_ITEMS");  // Foreign keys for 'order_items' table

            boolean orderForeignKeyExists = false;

            while (foreignKeys.next()) {
                String fkTable = foreignKeys.getString("PKTABLE_NAME");
                String fkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                if ("ORDERS".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    orderForeignKeyExists = true;
                }
            }

            assertTrue(orderForeignKeyExists, "Foreign key to 'orders' does not exist in 'order_items' table.");
        }
    }

    @Test
    public void testOrderItemsColumnsExist() throws SQLException {
        // Check if the 'order_items' table has the correct columns ('order_id', 'product_name', 'quantity', 'price')
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "ORDER_ITEMS", null);

            boolean orderIdExists = false;
            boolean productNameExists = false;
            boolean quantityExists = false;
            boolean priceExists = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("ORDER_ID".equalsIgnoreCase(columnName)) {
                    orderIdExists = true;
                }
                if ("PRODUCT_NAME".equalsIgnoreCase(columnName)) {
                    productNameExists = true;
                }
                if ("QUANTITY".equalsIgnoreCase(columnName)) {
                    quantityExists = true;
                }
                if ("PRICE".equalsIgnoreCase(columnName)) {
                    priceExists = true;
                }
            }

            assertTrue(orderIdExists, "Column 'order_id' does not exist in 'order_items' table.");
            assertTrue(productNameExists, "Column 'product_name' does not exist in 'order_items' table.");
            assertTrue(quantityExists, "Column 'quantity' does not exist in 'order_items' table.");
            assertTrue(priceExists, "Column 'price' does not exist in 'order_items' table.");
        }
    }
}