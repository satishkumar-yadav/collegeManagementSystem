package student;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

import collegeManagement.JdbcConnection;

import java.awt.event.*;

public class StudentsLeave extends JFrame implements ActionListener {

    Choice  ctime;
    JDateChooser dcdate;
    JButton submit, cancel, reset;
    String roll;
    
    public StudentsLeave(String Roll) {
        this.roll=Roll;
    	
        setSize(500, 420);
        setLocation(550, 100);
        setLayout(null);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollno = new JLabel("Student Roll No :");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);
        
        JLabel lbrollno = new JLabel(roll);
        lbrollno.setBounds(200, 100, 100, 20);
        lbrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbrollno);
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 150, 200, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(60, 180, 200, 25);
        add(dcdate);
        
        JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60, 230, 200, 20);
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbltime);
        
        ctime = new Choice();
        ctime.setBounds(60, 260, 200, 20);
        ctime.add("Select Duration");
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        
        submit = new JButton("Submit");
        submit.setBounds(60, 320, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 320, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        reset = new JButton("Reset");
        reset.setBounds(320, 320, 100, 25);
        reset.setBackground(Color.BLACK);
        reset.setForeground(Color.WHITE);
        reset.addActionListener(this);
        reset.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(reset);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit)
         {
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String duration = ctime.getSelectedItem();
            String rollno = roll;
            
            String query = "insert into temp_studentleave values('"+rollno+"', '"+date+"', '"+duration+"')";
            
            try {
            	JdbcConnection c1 = new JdbcConnection();
                c1.st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Applied");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (ae.getSource() == reset)
        {
        	dcdate.setDate(null);
        	ctime.select(0);
        }
        else 
        {
            setVisible(false);
        }
    }

   // public static void main(String[] args) {    new StudentsLeave();   }
}
