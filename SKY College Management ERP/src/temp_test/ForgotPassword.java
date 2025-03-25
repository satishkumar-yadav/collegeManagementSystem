package temp_test;

import javax.swing.*;

import collegeManagement.JdbcConnection;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class ForgotPassword extends JFrame {
    private JTextField emailField, otpField;
    private JPasswordField newPasswordField;
    private JButton sendOtpButton, verifyOtpButton, resetPasswordButton;

    private String generatedOTP;
    private String userEmail;

    public ForgotPassword() {
        setTitle("Forgot Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 2));

        JLabel emailLabel = new JLabel("Enter Registered Email:");
        emailField = new JTextField();

        sendOtpButton = new JButton("Send OTP");
        sendOtpButton.addActionListener(e -> sendOtp());

        JLabel otpLabel = new JLabel("Enter OTP:");
        otpField = new JTextField();
        otpField.setEnabled(false);

        verifyOtpButton = new JButton("Verify OTP");
        verifyOtpButton.setEnabled(false);
        verifyOtpButton.addActionListener(e -> verifyOtp());

        JLabel passwordLabel = new JLabel("New Password:");
        newPasswordField = new JPasswordField();
        newPasswordField.setEnabled(false);

        resetPasswordButton = new JButton("Reset Password");
        resetPasswordButton.setEnabled(false);
        resetPasswordButton.addActionListener(e -> resetPassword());

        add(emailLabel);
        add(emailField);
        add(sendOtpButton);
        add(new JLabel()); // Empty space
        add(otpLabel);
        add(otpField);
        add(verifyOtpButton);
        add(new JLabel());
        add(passwordLabel);
        add(newPasswordField);
        add(resetPasswordButton);

        setLocationRelativeTo(null);
    }

    private void sendOtp() {
        userEmail = emailField.getText().trim();

        if (userEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your registered email!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try // (Connection con = JdbcConnection.getConnection();
        {
        	 JdbcConnection con = new JdbcConnection();
        
             PreparedStatement pst = con.prepareStatement("SELECT * FROM users WHERE email=?") ;// ) {
            pst.setString(1, userEmail);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "Email not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            generatedOTP = generateOTP();
            EmailSender.sendEmail(userEmail, "Password Reset OTP", "Your OTP for password reset is: " + generatedOTP);
            JOptionPane.showMessageDialog(this, "OTP sent to your email!", "Success", JOptionPane.INFORMATION_MESSAGE);

            otpField.setEnabled(true);
            verifyOtpButton.setEnabled(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error sending OTP: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void verifyOtp() {
        if (otpField.getText().trim().equals(generatedOTP)) {
            JOptionPane.showMessageDialog(this, "OTP verified! Enter a new password.", "Success", JOptionPane.INFORMATION_MESSAGE);
            newPasswordField.setEnabled(true);
            resetPasswordButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Invalid OTP!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resetPassword() {
        String newPassword = new String(newPasswordField.getPassword()).trim();

        if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a new password!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String hashedPassword = PasswordUtil.hashPassword(newPassword);

        try  // (Connection con = JdbcConnection.getConnection();
        		
        		{	
        	 JdbcConnection con = new JdbcConnection();
             PreparedStatement pst = con.prepareStatement("UPDATE users SET password=? WHERE email=?") ; // ) {
            pst.setString(1, hashedPassword);
            pst.setString(2, userEmail);

            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error resetting password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generates a 6-digit OTP
        return String.valueOf(otp);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ForgotPassword().setVisible(true));
    }
}
