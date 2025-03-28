package collegeManagement;

import java.io.*;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JdbcConnection3 {
    private static final String FILE_PATH = "database.txt";
    private final String path = "com.mysql.cj.jdbc.Driver";
    private static String userid;                 // added static
    private static String passWord;         // added static
    private String dbName = null; // Initially set to null on first run
    private String url;
  //  private final String url = "jdbc:mysql://localhost:3306/" + dbName;
    private Connection con;
    public Statement st;

    public JdbcConnection3() {
        try {
            // Load database credentials dynamically
            loadCredentials();

            // Set database URL
            if (dbName == null) {
                url = "jdbc:mysql://localhost:3306/"; // No database selected initially
            } else {
                url = "jdbc:mysql://localhost:3306/" + dbName;
            }
            
            System.out.println("Username : "+userid+" password : "+passWord);
            
            // Establish connection
            Class.forName(path);
            con = DriverManager.getConnection(url, userid, passWord);
            st = con.createStatement();
            
         // Create database if it doesn't exist (after credentials are set)
            if (dbName == null) {
                createDatabase();
                dbName = "collegemanagement"; // Now database exists, so update dbName
            }
            
        } catch (Exception e) {
            System.out.println("Error in database connection: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in database connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Ensures database connection is established before returning it.
     */
    public Connection getConnection() throws SQLException
    {
 	 //  return con;
 	   if (con != null  && !con.isClosed() ) {
 		   return con;
        }
        throw new SQLException("Connection is not established.");
    }
    
    
    /**
     * Load credentials from database.txt or prompt the user if the file is empty.
     */
    private void loadCredentials() {
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                userid = reader.readLine();
                passWord = reader.readLine();
                dbName = "collegemanagement"; // Use database after credentials exist
            } catch (IOException e) {
                System.out.println("Failed to read credentials: " + e.getMessage());
            }
        } else {
            // Prompt user for credentials on first run
            setCredentials();
        }
    }

    /**
     * Prompt the user to enter database credentials and save them in database.txt.
     */
    private void setCredentials() {
    	String username = userid;
    	String password = passWord;
    	
    	
        Scanner scanner = new Scanner(System.in);
    //    System.out.print("Enter database username (leave blank to use default: root): ");
     //   userid = scanner.nextLine().trim();
        if (username.isEmpty()) userid = "root";

   //     System.out.print("Enter database password (leave blank to use default: 12345678): ");
   //     passWord = scanner.nextLine().trim();
        if (password.isEmpty()) passWord = "12345678";

        // Save credentials to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(userid);
            writer.newLine();
            writer.write(passWord);
        } catch (IOException e) {
            System.out.println("Failed to save credentials: " + e.getMessage());
        }

        System.out.println("Credentials saved successfully.");
    }

    
    public static void putCredential (String username, String password)
    {
    	userid = username;
        passWord = password;
    	System.out.println("Username : "+userid+" password : "+passWord);
    }
    
    /**
     * Creates the database "collegemanagement" after credentials are set.
     */
    private void createDatabase() {
        try (Connection tempCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/", userid, passWord);
             Statement tempSt = tempCon.createStatement()) {

            String query = "CREATE DATABASE IF NOT EXISTS collegemanagement";
            tempSt.execute(query);
            System.out.println("Database 'collegemanagement' created successfully.");

        } catch (SQLException e) {
            System.out.println("Failed to create database: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to create database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    // Returns the PreparedStatement
    public PreparedStatement prepareStatement(String query) throws SQLException {
        if (con != null) {
            return con.prepareStatement(query);
        }
        throw new SQLException("Connection is not established.");
    }

    // Closes the connection and resources
    public void close() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Uncomment for testing
//    public static void main(String[] args) { new JdbcConnection4(); }
}

