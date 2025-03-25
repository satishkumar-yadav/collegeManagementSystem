package temp_test;

import javax.swing.*;

public class FacultyDashboard extends JFrame {
    public FacultyDashboard(String username) {
        setTitle("Faculty Dashboard");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Welcome, Faculty " + username + "!", SwingConstants.CENTER);
        add(welcomeLabel);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
