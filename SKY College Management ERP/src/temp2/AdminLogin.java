package temp2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminLogin extends JFrame {
    public AdminLogin() {
        setTitle("Admin Login");
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 50, 200, 30);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 100, 200, 30);
        add(passField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 40);
        add(loginButton);

        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if ("admin".equals(username) && "123".equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                dispose();
                new AdminDashboard();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Credentials");
            }
        });

        setVisible(true);
    }
}
