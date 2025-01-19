package temp2;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("College Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JButton registerButton = new JButton("Register Student");
        registerButton.setBounds(100, 80, 200, 40);
        frame.add(registerButton);

        JButton adminButton = new JButton("Admin Login");
        adminButton.setBounds(100, 140, 200, 40);
        frame.add(adminButton);

        JButton CreateDatabase = new JButton("Database Create");
        CreateDatabase.setBounds(100, 200, 200, 40);
        frame.add(CreateDatabase);

        // ActionListener for "Register Student" button
        registerButton.addActionListener(e -> {
            frame.dispose();
            new RegisterStudent();
        });

        // ActionListener for "Admin Login" button
        adminButton.addActionListener(e -> {
            frame.dispose();
            new AdminLogin();
        });

        // ActionListener for "Database Create" button
        CreateDatabase.addActionListener(e -> {
            // Call the method to create the database schema
            DatabaseSchemaCreator.createDatabaseSchema();
        });

        frame.setVisible(true);
    }
}
