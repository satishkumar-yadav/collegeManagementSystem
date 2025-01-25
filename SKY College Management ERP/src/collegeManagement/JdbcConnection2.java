package collegeManagement;

import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JdbcConnection2 {
    private final String path = "com.mysql.cj.jdbc.Driver";

    private String userid = "root"; // Default userid
    private String passWord = "12345678"; // Default password
    private final String dbName = "collegemanagement";

    private final String url = "jdbc:mysql://localhost:3306/" + dbName;
    private Connection con;
    public Statement st;

    public JdbcConnection2() {
        try {
            // Establish connection
            Class.forName(path);
            con = DriverManager.getConnection(url, userid, passWord);
            st = con.createStatement();
        } catch (Exception e) {
            System.out.println("Error in database connection: " + e.getMessage());
        }
    }

    // Allows setting the database credentials only once during database creation
    public void setDatabaseCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new database username (leave blank to use default): ");
        String inputUserid = scanner.nextLine();

        System.out.println("Enter new database password (leave blank to use default): ");
        String inputPassword = scanner.nextLine();

        // Validate and set the credentials
        if (!inputUserid.isBlank() && !inputPassword.isBlank()) {
            this.userid = inputUserid;
            this.passWord = inputPassword;

            JOptionPane.showMessageDialog(null,
                    "Database credentials set successfully!\nUserID: " + userid + "\nPassword: " + passWord,
                    "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Using default credentials.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }

        // Store credentials persistently (if needed)
        storeCredentials(inputUserid, inputPassword);
    }

    // Store credentials persistently (optional)
    private void storeCredentials(String userid, String password) {
        // Implement logic to store credentials securely, e.g., in a configuration file or encrypted database
        try {
            String createCredentialsTable = """
                    CREATE TABLE IF NOT EXISTS user_credentials (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        username VARCHAR(100) NOT NULL,
                        password VARCHAR(100) NOT NULL
                    )
                    """;
            st.execute(createCredentialsTable);

            String insertCredentials = "INSERT INTO user_credentials (username, password) VALUES (?, ?)";
            PreparedStatement ps = con.prepareStatement(insertCredentials);
            ps.setString(1, userid);
            ps.setString(2, password);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Failed to store credentials: " + e.getMessage());
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
    
//  public static void main(String[] args) {   new JdbcConnection2();  }
}
