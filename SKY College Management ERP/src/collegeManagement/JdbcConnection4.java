package collegeManagement;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.sql.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class JdbcConnection4 {
    private static final String FILE_PATH = "database.txt"; // Keep it in a restricted folder if possible
    private final String path = "com.mysql.cj.jdbc.Driver";
    private static String userid;
    private static String passWord;
    private String dbName = null;
    private String url;
    private Connection con;
    public Statement st;

    public JdbcConnection4() {
        try {
            // Secure file permissions
            secureFile();

            // Load database credentials
            loadCredentials();

            // Set database URL
            url = dbName == null ? "jdbc:mysql://localhost:3306/" : "jdbc:mysql://localhost:3306/" + dbName;

            // Establish connection
            Class.forName(path);
            con = DriverManager.getConnection(url, userid, passWord);
            st = con.createStatement();

            // Create database if it doesn't exist
            if (dbName == null) {
                createDatabase();
                dbName = "collegemanagement"; 
            }
        } catch (Exception e) {
            System.out.println("Error in database connection: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error in database connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Connection getConnection() throws SQLException {
        if (con != null && !con.isClosed()) {
            return con;
        }
        throw new SQLException("Connection is not established.");
    }

    private void loadCredentials() {
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                userid = reader.readLine();
                passWord = reader.readLine();
                dbName = "collegemanagement"; 
            } catch (IOException e) {
                System.out.println("Failed to read credentials: " + e.getMessage());
            }
        } else {
            setCredentials();
        }
    }

    private void setCredentials() {
        Scanner scanner = new Scanner(System.in);

        if (userid == null || userid.isEmpty()) userid = "root";
        if (passWord == null || passWord.isEmpty()) passWord = "12345678";

        // Save credentials securely
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(userid);
            writer.newLine();
            writer.write(passWord);
        } catch (IOException e) {
            System.out.println("Failed to save credentials: " + e.getMessage());
        }

        System.out.println("Credentials saved successfully.");
        secureFile(); // Secure after writing
    }

    public static void putCredential(String username, String password) {
        userid = username;
        passWord = password;
    }

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

    public PreparedStatement prepareStatement(String query) throws SQLException {
        if (con != null) {
            return con.prepareStatement(query);
        }
        throw new SQLException("Connection is not established.");
    }

    public void close() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Secures database.txt file from unauthorized access.
     */
    private void secureFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try {
            // Windows & Linux: Make the file readable/writable ONLY by the owner
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                file.setReadable(false, false); // No read for others
                file.setWritable(false, false); // No write for others
                file.setExecutable(false, false);
                Files.setAttribute(Paths.get(FILE_PATH), "dos:hidden", true);    // hide from normal user

            } else {
                // For Linux/macOS: Use POSIX permissions
                Path path = file.toPath();
                Files.setPosixFilePermissions(path, PosixFilePermissions.fromString("rw-------"));
            }

            System.out.println("File permissions secured: " + FILE_PATH);
        } catch (UnsupportedOperationException | IOException e) {
            System.out.println("Could not secure file: " + e.getMessage());
        }
    }

    // Uncomment for testing
    // public static void main(String[] args) { new JdbcConnection(); }
}

