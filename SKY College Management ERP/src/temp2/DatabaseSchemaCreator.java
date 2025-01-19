package temp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DatabaseSchemaCreator {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root"; // Update with your MySQL username
    private static final String DB_PASSWORD = "12345678"; // Update with your MySQL password

    // SQL script for creating the schema
    private static final String SQL_SCRIPT = """
            CREATE DATABASE IF NOT EXISTS college;

            USE college;

            CREATE TABLE IF NOT EXISTS temp_students (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                age INT,
                course VARCHAR(100)
            );

            CREATE TABLE IF NOT EXISTS students (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                age INT,
                course VARCHAR(100)
            );
            """;
    
    private static final String[] SQL_SCRIPT2 = {
            "CREATE DATABASE IF NOT EXISTS college",
            "USE college",
            """
            CREATE TABLE IF NOT EXISTS temp_students (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                age INT,
                course VARCHAR(100)
            )
            """,
            """
            CREATE TABLE IF NOT EXISTS students (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100),
                age INT,
                course VARCHAR(100)
            )
            """
    };

    // Method to create the database schema
    public static void createDatabaseSchema() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Execute the SQL script
          //  stmt.execute(SQL_SCRIPT);
            
         // Execute each SQL statement individually
            for (String sql : SQL_SCRIPT2) {
                stmt.execute(sql.trim());
            }

            // Show success message
            JOptionPane.showMessageDialog(null, "Database schema created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // Show error message if any exception occurs
            JOptionPane.showMessageDialog(null, "Failed to create database schema: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
