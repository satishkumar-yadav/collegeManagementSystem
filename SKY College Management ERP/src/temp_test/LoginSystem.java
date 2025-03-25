package temp_test;

import javax.swing.*;

import collegeManagement.JdbcConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSystem extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginSystem() {
        setTitle("Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        JButton loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticateUser();
            }
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel()); // Empty space
        add(loginButton);

        setLocationRelativeTo(null);
    }

    private void authenticateUser() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

    //    try (Connection con = JdbcConnection.getConnection();
     //        PreparedStatement pst = con.prepareStatement("SELECT role FROM users WHERE username=? AND password=?")) {
        	
        String hashedPassword = PasswordUtil.hashPassword(password); // Hash entered password
        	
        	try {
        	
        	 JdbcConnection con = new JdbcConnection();
             //  JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
               
        	 PreparedStatement pst = con.prepareStatement("SELECT role FROM login WHERE username=? AND password=?");
               
        	
            pst.setString(1, username);
            pst.setString(2, hashedPassword);  // Compare hashed password

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                JOptionPane.showMessageDialog(this, "Login successful as " + role);

                // Redirect to respective dashboard
                if ("student".equals(role)) {
                    new StudentDashboard(username);
                } else if ("faculty".equals(role)) {
                    new FacultyDashboard(username);
                } else if ("admin".equals(role)) {
                    new AdminDashboard(username);
                }

                dispose(); // Close login window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginSystem().setVisible(true));
    }
}
