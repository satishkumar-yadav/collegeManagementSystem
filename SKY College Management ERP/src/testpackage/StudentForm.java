
package testpackage;

//   chatgpt test 

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.Date;

public class StudentForm {
    private JFrame frame;
    private JTextField tfname, tfmobile;
    private JRadioButton jrb1, jrb2;
    private JComboBox<String> cbcourse, cbbranch1;
    private JDateChooser dcdob;
    private JLabel picLabel;
    private File selectedFile;
    private Connection con;
    private PreparedStatement pst;

    public StudentForm() {
        frame = new JFrame("Student Registration Form");
        frame.setSize(700, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(50, 50, 100, 30);
        frame.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(150, 50, 200, 30);
        frame.add(tfname);

        JLabel lblgender = new JLabel("Gender:");
        lblgender.setBounds(50, 100, 100, 30);
        frame.add(lblgender);

        jrb1 = new JRadioButton("Male");
        jrb1.setBounds(150, 100, 80, 30);
        jrb2 = new JRadioButton("Female");
        jrb2.setBounds(240, 100, 80, 30);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        frame.add(jrb1);
        frame.add(jrb2);

        JLabel lbldob = new JLabel("Date of Birth:");
        lbldob.setBounds(50, 150, 100, 30);
        frame.add(lbldob);

        dcdob = new JDateChooser();
        dcdob.setBounds(150, 150, 200, 30);
        frame.add(dcdob);

        JLabel lblcourse = new JLabel("Course:");
        lblcourse.setBounds(50, 200, 100, 30);
        frame.add(lblcourse);

        cbcourse = new JComboBox<>(new String[] {"Select Course", "B.Tech", "BBA", "BCA", "Bsc", "BA"});
        cbcourse.setBounds(150, 200, 200, 30);
        frame.add(cbcourse);

        JLabel lblbranch = new JLabel("Branch:");
        lblbranch.setBounds(50, 250, 100, 30);
        frame.add(lblbranch);

        cbbranch1 = new JComboBox<>(new String[] {"Select Branch", "CSE", "ECE", "Mechanical", "Civil"});
        cbbranch1.setBounds(150, 250, 200, 30);
        cbbranch1.setVisible(false);
        frame.add(cbbranch1);

        cbcourse.addItemListener(e -> {
            String selectedCourse = (String) cbcourse.getSelectedItem();
            cbbranch1.setVisible("B.Tech".equals(selectedCourse));
        });

        JLabel lblmobile = new JLabel("Mobile:");
        lblmobile.setBounds(50, 300, 100, 30);
        frame.add(lblmobile);

        tfmobile = new JTextField();
        tfmobile.setBounds(150, 300, 200, 30);
        frame.add(tfmobile);

        JLabel lblpic = new JLabel("Profile Picture:");
        lblpic.setBounds(50, 350, 150, 30);
        frame.add(lblpic);

        JButton btnupload = new JButton("Upload Picture");
        btnupload.setBounds(150, 350, 150, 30);
        frame.add(btnupload);

        picLabel = new JLabel();
        picLabel.setBounds(400, 50, 200, 200);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(picLabel);

        btnupload.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                ImageIcon icon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
                picLabel.setIcon(icon);
            }
        });

        JButton btnsubmit = new JButton("Submit");
        btnsubmit.setBounds(150, 450, 100, 30);
        frame.add(btnsubmit);

        btnsubmit.addActionListener(e -> submitForm());

        frame.setVisible(true);
    }

    private void submitForm() {
        String name = tfname.getText();
        String gender = jrb1.isSelected() ? "Male" : (jrb2.isSelected() ? "Female" : "");
        String dob = "";
        if (dcdob.getDate() != null) {
            dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
        }
        String course = (String) cbcourse.getSelectedItem();
        String branch = cbbranch1.isVisible() ? (String) cbbranch1.getSelectedItem() : "";
        String mobile = tfmobile.getText();

        if (name.isBlank() || gender.isEmpty() || dob.isEmpty() || "Select Course".equals(course) || "Select Branch".equals(branch) || mobile.isBlank() || selectedFile == null) {
            JOptionPane.showMessageDialog(frame, "All fields are mandatory, including profile picture.");
            return;
        }

        JOptionPane.showMessageDialog(frame, " Name : < "+name+"  >, Gender : < "+gender+"  >, DOB : < "+dob+"  >, Course : < "+course+ "  >, Branch : < "+branch+ "  >, Mobile : < "+mobile+ "  >, Pic : < "+selectedFile+" > " );
        
        try (FileInputStream fis = new FileInputStream(selectedFile)) {
            JdbcConnection jdbc = new JdbcConnection();
            
            JOptionPane.showMessageDialog(frame, "Connection : "+ jdbc);
            
            String query = "INSERT INTO student (name, gender, dob, course, branch, mobile, profile_pic) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pst = jdbc.con.prepareStatement(query);
            pst.setString(1, name);
            pst.setString(2, gender);
            pst.setString(3, dob);
            pst.setString(4, course);
            pst.setString(5, branch);
            pst.setString(6, mobile);
            pst.setBinaryStream(7, fis, (int) selectedFile.length());

            JOptionPane.showMessageDialog(frame, "Pic : " +fis);
            
            JOptionPane.showMessageDialog(frame, "Querry : " +query);
            
//            int rows = pst.executeUpdate();
//            if (rows > 0) {
//                JOptionPane.showMessageDialog(frame, "Student registered successfully!");
//            } else {
//                JOptionPane.showMessageDialog(frame, "Failed to register student.");
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new StudentForm();
    }
}


/*
 
 String picPath = null;
    if (fis != null) {
        try {
            byte[] imageBytes = fis.readAllBytes(); // Read the image as byte array
            picPath = Base64.getEncoder().encodeToString(imageBytes); // Convert to Base64 string
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    if (picPath == null) {
        JOptionPane.showMessageDialog(null, "Please upload a profile picture.");
        return;
    }

    try {
        // SQL query to insert data into the database, including the Base64 encoded image string
        String query = String.format(
            "INSERT INTO student (rollno, name, fname, phone, email, aadhar, dob, address, profile_pic, branch, course, qualification, x, xii, semester, gender) " +
            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
            rollno, name, fname, phone, email, aadhar, dob, address, picPath, branch, course, qualification, x, xii, semester, gender
        );

        JdbcConnection con = new JdbcConnection();
        con.st.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Student Details Inserted Successfully.");
        setVisible(false);
 
 */ 
