package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class StudentCourseEntitySchemaTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void waitForSchemaCreation() throws InterruptedException {
        // Wait a few seconds to ensure Hibernate has created the database schema
        Thread.sleep(1000);  // Wait for 3 seconds before running tests to allow schema creation
    }

    @Test
    public void testStudentTableExists() throws SQLException {
        // Check if the 'student' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "STUDENT", null);  // Check for 'STUDENT' table
            assertTrue(tables.next(), "Table 'student' does not exist.");
        }
    }

    @Test
    public void testCourseTableExists() throws SQLException {
        // Check if the 'course' table exists in H2 database
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "COURSE", null);  // Check for 'COURSE' table
            assertTrue(tables.next(), "Table 'course' does not exist.");
        }
    }

    @Test
    public void testStudentCourseJoinTableExists() throws SQLException {
        // Check if the 'student_course' join table exists
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "STUDENT_COURSE", null);  // Check for 'student_course' join table
            assertTrue(tables.next(), "Join table 'student_course' does not exist.");
        }
    }

    @Test
    public void testStudentHasCoursesForeignKey() throws SQLException {
        // Check if the 'student_course' join table has foreign keys to 'student' and 'course'
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet foreignKeys = metaData.getImportedKeys(null, null, "STUDENT_COURSE");  // Foreign keys for 'student_course' table

            boolean studentForeignKeyExists = false;
            boolean courseForeignKeyExists = false;

            while (foreignKeys.next()) {
                String fkTable = foreignKeys.getString("PKTABLE_NAME");
                String fkColumn = foreignKeys.getString("PKCOLUMN_NAME");
                if ("STUDENT".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    studentForeignKeyExists = true;
                }
                if ("COURSE".equalsIgnoreCase(fkTable) && "ID".equalsIgnoreCase(fkColumn)) {
                    courseForeignKeyExists = true;
                }
            }

            assertTrue(studentForeignKeyExists, "Foreign key to 'student' does not exist in 'student_course' table.");
            assertTrue(courseForeignKeyExists, "Foreign key to 'course' does not exist in 'student_course' table.");
        }
    }

    @Test
    public void testStudentCourseJoinTableColumns() throws SQLException {
        // Check if the 'student_course' join table has the correct columns ('student_id' and 'course_id')
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet columns = metaData.getColumns(null, null, "STUDENT_COURSE", null);

            boolean studentIdExists = false;
            boolean courseIdExists = false;

            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                if ("STUDENT_ID".equalsIgnoreCase(columnName)) {
                    studentIdExists = true;
                }
                if ("COURSE_ID".equalsIgnoreCase(columnName)) {
                    courseIdExists = true;
                }
            }

            assertTrue(studentIdExists, "Column 'student_id' does not exist in 'student_course' table.");
            assertTrue(courseIdExists, "Column 'course_id' does not exist in 'student_course' table.");
        }
    }
}