package temp_test;

import javax.swing.*;

public class AdminDashboard extends JFrame {
    public AdminDashboard(String username) {
        setTitle("Admin Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, Admin " + username + "!", SwingConstants.CENTER);
        add(welcomeLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
