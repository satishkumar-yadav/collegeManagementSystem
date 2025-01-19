package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;

import collegeManagement.JdbcConnection;

import java.awt.event.*;

public class StudentLeave extends JFrame implements ActionListener {

    Choice crollno, ctime;
    JTextField tfrollno;
    JDateChooser dcdate;
    JButton submit, cancel, reset;
    
    public StudentLeave() {
        
        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollno = new JLabel("Search by Roll Number");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);
        
        crollno = new Choice();
        crollno.setBounds(60, 130, 100, 20);
        crollno.add("Select RollNo");
        add(crollno);
        
        tfrollno = new JTextField();
        tfrollno.setBounds(170, 130, 100, 20);
        add(tfrollno);
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from student");
            while(rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 180, 200, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(60, 210, 200, 25);
        add(dcdate);
        
        JLabel lbltime = new JLabel("Time Duration");
        lbltime.setBounds(60, 260, 200, 20);
        lbltime.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbltime);
        
        ctime = new Choice();
        ctime.setBounds(60, 290, 200, 20);
        ctime.add("Select Duration");
        ctime.add("Full Day");
        ctime.add("Half Day");
        add(ctime);
        
        submit = new JButton("Submit");
        submit.setBounds(60, 350, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(200, 350, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        reset = new JButton("Reset");
        reset.setBounds(320, 350, 100, 25);
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
        	String roll1 = tfrollno.getText().trim();
            String roll2 = crollno.getSelectedItem();
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String duration = ctime.getSelectedItem();
            String rollno = " ";
            
            if (!(roll1.isBlank()) && roll2.equals("Select RollNo"))
        	{
        		rollno = roll1;
        	}
        	else if (roll1.isBlank() && !(roll2.equals("Select RollNo")))
        	{
        		rollno = roll2;
        	}
        	else if (!(roll1.isBlank()) && !(roll2.equals("Select RollNo")))
        	{
        		JOptionPane.showMessageDialog(null, "Please Use Any One Field Either Select or Enter Roll No in TextBox.");
        		return;
        	}
        	else{
        		 JOptionPane.showMessageDialog(null, "Please Select or Enter Roll Number First.");
                 return;
        	}
            
            String query = "insert into studentleave values('"+rollno+"', '"+date+"', '"+duration+"')";
            
            try {
            	JdbcConnection c1 = new JdbcConnection();
                c1.st.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else if (ae.getSource() == reset)
        {
        	tfrollno.setText(" ");
        	crollno.select(0);
        	dcdate.setDate(null);
        	ctime.select(0);
        }
        else 
        {
            setVisible(false);
        }
    }

  //  public static void main(String[] args) {    new StudentLeave();   }
}
