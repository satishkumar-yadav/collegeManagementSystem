package testpackage;


import javax.swing.*;

import com.toedter.calendar.JDateChooser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

public class StudentForm2 extends JFrame {
    private JTextField tfName, tfFatherName, tfPhone, tfEmail, tfAadhar, tfClassX, tfClassXII;
    private JTextArea tfAddress;
    private JDateChooser dobChooser;
    private JComboBox<String> cbBranch, cbCourse, cbSemester, cbQualification;
    private JRadioButton rbMale, rbFemale, rbOther;
    private ButtonGroup genderGroup;
    private JLabel lblProfilePic;
    private File selectedFile;

    public StudentForm2() {
        setTitle("Student Registration Form");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(18, 2));

        tfName = new JTextField();
        tfFatherName = new JTextField();
        tfPhone = new JTextField();
        tfEmail = new JTextField();
        tfAadhar = new JTextField();
        tfClassX = new JTextField();
        tfClassXII = new JTextField();

        tfAddress = new JTextArea();
        tfAddress.setLineWrap(true);
        tfAddress.setWrapStyleWord(true);

        dobChooser = new JDateChooser();

        String[] branches = {"CSE", "ECE", "EEE", "Mechanical", "Civil"};
        cbBranch = new JComboBox<>(branches);

        String[] courses = {"B.Tech", "B.Sc", "M.Tech", "MBA"};
        cbCourse = new JComboBox<>(courses);

        String[] semesters = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th"};
        cbSemester = new JComboBox<>(semesters);

        String[] qualifications = {"Undergraduate", "Postgraduate"};
        cbQualification = new JComboBox<>(qualifications);

        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        rbOther = new JRadioButton("Other");

        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderGroup.add(rbOther);

        lblProfilePic = new JLabel("No file chosen");

        JButton btnUpload = new JButton("Upload Profile Picture");
        btnUpload.addActionListener(e -> chooseProfilePicture());

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.addActionListener(e -> submitForm());

        add(new JLabel("Name:"));
        add(tfName);
        add(new JLabel("Father's Name:"));
        add(tfFatherName);
        add(new JLabel("Phone:"));
        add(tfPhone);
        add(new JLabel("Email:"));
        add(tfEmail);
        add(new JLabel("Aadhar:"));
        add(tfAadhar);
        add(new JLabel("Date of Birth:"));
        add(dobChooser);
        add(new JLabel("Address:"));
        add(new JScrollPane(tfAddress));
        add(new JLabel("Branch:"));
        add(cbBranch);
        add(new JLabel("Course:"));
        add(cbCourse);
        add(new JLabel("Qualification:"));
        add(cbQualification);
        add(new JLabel("Class X %:"));
        add(tfClassX);
        add(new JLabel("Class XII %:"));
        add(tfClassXII);
        add(new JLabel("Semester:"));
        add(cbSemester);
        add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout());
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        genderPanel.add(rbOther);
        add(genderPanel);
        add(lblProfilePic);
        add(btnUpload);
        add(new JLabel());
        add(btnSubmit);

        setVisible(true);
    }

    private void chooseProfilePicture() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            if (!selectedFile.getName().toLowerCase().matches(".*\\.(jpg|jpeg|png)$")) {
                JOptionPane.showMessageDialog(this, "Please select a valid image file (JPG, JPEG, PNG).", "Invalid File", JOptionPane.ERROR_MESSAGE);
                selectedFile = null;
                return;
            }
            lblProfilePic.setText(selectedFile.getName());
        }
    }

    private void submitForm() {
        String name = tfName.getText().trim();
        String fatherName = tfFatherName.getText().trim();
        String phone = tfPhone.getText().trim();
        String email = tfEmail.getText().trim();
        String aadhar = tfAadhar.getText().trim();
        String address = tfAddress.getText().trim();
        String classX = tfClassX.getText().trim();
        String classXII = tfClassXII.getText().trim();
        String branch = (String) cbBranch.getSelectedItem();
        String course = (String) cbCourse.getSelectedItem();
        String qualification = (String) cbQualification.getSelectedItem();
        String semester = (String) cbSemester.getSelectedItem();

        String gender = "";
        if (rbMale.isSelected()) gender = "Male";
        if (rbFemale.isSelected()) gender = "Female";
        if (rbOther.isSelected()) gender = "Other";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dob = "";
        try {
            dob = dateFormat.format(dobChooser.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please select a valid date of birth.", "Invalid DOB", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validation checks
        if (name.isEmpty() || fatherName.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                aadhar.isEmpty() || address.isEmpty() || classX.isEmpty() || classXII.isEmpty() ||
                gender.isEmpty() || dob.isEmpty() || selectedFile == null) {
            JOptionPane.showMessageDialog(this, "All fields are mandatory.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("\\d{10}", phone)) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("\\d{12}", aadhar)) {
            JOptionPane.showMessageDialog(this, "Aadhar number must be 12 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double percentageX = Double.parseDouble(classX);
            double percentageXII = Double.parseDouble(classXII);

            if (percentageX < 0 || percentageX > 100 || percentageXII < 0 || percentageXII > 100) {
                JOptionPane.showMessageDialog(this, "Class X and XII percentages must be between 0 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Class X and XII percentages must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Save to database (example code)
        try (FileInputStream fis = new FileInputStream(selectedFile)) {
            JdbcConnection jdbc = new JdbcConnection();

            String query = "INSERT INTO student (name, father_name, phone, email, aadhar, dob, address, branch, course, qualification, class_x, class_xii, semester, gender, profile_pic) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = jdbc.con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, fatherName);
            pst.setString(3, phone);
            pst.setString(4, email);
            pst.setString(5, aadhar);
            pst.setString(6, dob);
            pst.setString(7, address);
            pst.setString(8, branch);
            pst.setString(9, course);
            pst.setString(10, qualification);
            pst.setDouble(11, Double.parseDouble(classX));
            pst.setDouble(12, Double.parseDouble(classXII));
            pst.setString(13, semester);
            pst.setString(14, gender);
            pst.setBinaryStream(15, fis, (int) selectedFile.length());

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Student details inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving to database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentForm2::new);
    }
}
