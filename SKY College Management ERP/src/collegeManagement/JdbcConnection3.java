package collegeManagement;

import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JdbcConnection3 {
    private final String path = "com.mysql.cj.jdbc.Driver";
    private String userid;
    private String passWord;
    private final String dbName = "collegemanagement";
    private final String url = "jdbc:mysql://localhost:3306/" + dbName;
    private Connection con;
    public Statement st;

    public JdbcConnection3() {
        try {
            // Load database credentials dynamically
            loadCredentials();

            // Establish connection
            Class.forName(path);
            con = DriverManager.getConnection(url, userid, passWord);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error in database connection: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in database connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Load credentials either from the user (first run) or from persistent storage.
     */
    private void loadCredentials() {
        try (Connection tempCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "12345678");
             Statement tempSt = tempCon.createStatement()) {
        	
        	String query = "create database if not exists collegemanagement";
             
            // Create credentials storage table if it doesn't exist
            String createCredentialsTable = """
                    CREATE TABLE IF NOT EXISTS credential_store (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL
                    )
                    """;
            
            tempSt.execute(query);
            tempSt.execute(createCredentialsTable);

            // Check if credentials are already stored
            String fetchCredentials = "SELECT username, password FROM credential_store LIMIT 1";
            ResultSet rs = tempSt.executeQuery(fetchCredentials);

            if (rs.next()) {
                // Load stored credentials
                userid = rs.getString("username");
                passWord = rs.getString("password");
            } else {
                // Prompt user for credentials (first run)
                setCredentials(tempSt);
            }
        } catch (Exception e) {
            System.out.println("Failed to load or set credentials: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to load or set credentials: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prompt user for credentials and save them to the database.
     */
    private void setCredentials(Statement tempSt) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter database username: ");
        userid = scanner.nextLine();

        System.out.println("Enter database password: ");
        passWord = scanner.nextLine();

        try {
            // Save credentials persistently
            String insertCredentials = "INSERT INTO credential_store (username, password) VALUES (?, ?)";
            PreparedStatement ps = tempSt.getConnection().prepareStatement(insertCredentials);
            ps.setString(1, userid);
            ps.setString(2, passWord);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Credentials set successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            System.out.println("Failed to save credentials: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Failed to save credentials: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
    
//  public static void main(String[] args) {   new JdbcConnection3();  }
}
