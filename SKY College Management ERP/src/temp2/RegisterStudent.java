package temp2;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class RegisterStudent extends JFrame {
    public RegisterStudent() {
        setTitle("Register New Student");
        setSize(400, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 50, 100, 30);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 50, 200, 30);
        add(nameField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setBounds(50, 100, 100, 30);
        add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setBounds(150, 100, 200, 30);
        add(ageField);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50, 150, 100, 30);
        add(courseLabel);

        JTextField courseField = new JTextField();
        courseField.setBounds(150, 150, 200, 30);
        add(courseField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(150, 250, 100, 40);
        add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            String course = courseField.getText();

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345678");
                String sql = "INSERT INTO temp_students (name, age, course) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setInt(2, Integer.parseInt(age));
                ps.setString(3, course);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Student Registered Successfully!");
                conn.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        setVisible(true);
    }
}
