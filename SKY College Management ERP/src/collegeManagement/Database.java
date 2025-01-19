package collegeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
	
	 // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_USER = "root"; // Update with your MySQL username
    private static final String DB_PASSWORD = "12345678"; // Update with your MySQL password

    // SQL script for creating the schema
    private static final String[] SQL_SCRIPT = DatabaseSqlScript.SQL_SCRIPT;
           

 // Method to create the database schema
	public static void createDatabaseSchema() {
		
		 try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             Statement stmt = conn.createStatement()) {
			 
			   // JdbcConnection conc = new JdbcConnection();

	            // Execute the SQL script
	          //  stmt.execute(SQL_SCRIPT);
	            
	         // Execute each SQL statement individually
	            for (String sql : SQL_SCRIPT) {
	                stmt.execute(sql.trim());
	            }

	            // Show success message
	            JOptionPane.showMessageDialog(null, "Database Schema Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

	        } catch (Exception e) {
	            // Show error message if any exception occurs
	            JOptionPane.showMessageDialog(null, "Failed to Create Database Schema: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
		
	}

}





	
    
   
	

