package collegeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Database {
	
	 // Database connection details
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
//    private static final String DB_USER = "root"; // Update with your MySQL username
//    private static final String DB_PASSWORD = "12345678"; // Update with your MySQL password

    // SQL script for creating the schema
    private static final String[] SQL_SCRIPT = DatabaseSqlScript.SQL_SCRIPT;
    
 // SQL script for inserting default value im the schema
    private static final String[] SQL_SCRIPT2 = DatabaseSqlScriptInsert.SQL_SCRIPT2 ;
           

 // Method to create the database schema
	public static void createDatabaseSchema() {
		
	/*	 try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             Statement stmt = conn.createStatement())   */
		 
		 try
		 {
			 JdbcConnection jdc = new JdbcConnection();
			 Connection con = jdc.getConnection();
			 
			// JOptionPane.showMessageDialog(null, "Connected Successfully!");
			 
			 Statement stmt = con.createStatement();
			// Statement stmt = jdc.createStatement();
		//	 JOptionPane.showMessageDialog(null, "Statement Creatment Successfully!");

	            // Execute the SQL script
	          //  stmt.execute(SQL_SCRIPT);
	            
	         // Execute each SQL statement individually
	            for (String sql : SQL_SCRIPT) {
	            //	 JOptionPane.showMessageDialog(null, "SQL : > "+sql);
	           // 	 JOptionPane.showMessageDialog(null, "Trim SQL : > "+sql.trim());       	
	      //      JOptionPane.showMessageDialog(null, "SQL Executing : > "+stmt.execute(sql.trim()));
	            	
	                stmt.execute(sql.trim());
	            }
	          //  JOptionPane.showMessageDialog(null, "Script1 Executed Successfully!");
	            
	            for (String sql2 : SQL_SCRIPT2) {
	                stmt.execute(sql2.trim());
	            }
	           // JOptionPane.showMessageDialog(null, "Script2 Executed Successfully!");
	            
	            // Show success message
	            JOptionPane.showMessageDialog(null, "Database Schema Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

	        } catch (Exception e) {
	            // Show error message if any exception occurs
	            JOptionPane.showMessageDialog(null, "Failed to Create Database Schema: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	        }
		
	}

}





	
    
   
	

