package collegeManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class TeacherLeave extends JFrame implements ActionListener {

    Choice cEmpId, ctime;
    JTextField tfempid;
    JDateChooser dcdate;
    JButton submit, cancel, reset;
    
    TeacherLeave() {
        
        setSize(500, 550);
        setLocation(550, 100);
        setLayout(null);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Apply Leave (Teacher)");
        heading.setBounds(40, 50, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel lblrollno = new JLabel("Search by Employee Id");
        lblrollno.setBounds(60, 100, 200, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);
        
        cEmpId = new Choice();
        cEmpId.setBounds(60, 130, 100, 20);
        cEmpId.add("Select EmpID");
        add(cEmpId);
        
        tfempid = new JTextField();
        tfempid.setBounds(170, 130, 100, 20);
        add(tfempid);
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from teacher");
            while(rs.next()) {
                cEmpId.add(rs.getString("empid"));
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
        if (ae.getSource() == submit) {
        	String id1 = tfempid.getText().trim();
            String id2 = cEmpId.getSelectedItem();
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            String duration = ctime.getSelectedItem();
            String empid = " ";
            
            if (!(id1.isBlank()) && id2.equals("Select EmpID"))
        	{
            	empid = id1;
        	}
        	else if (id1.isBlank() && !(id2.equals("Select EmpID")))
        	{
        		empid = id2;
        	}
        	else if (!(id1.isBlank()) && !(id2.equals("Select EmpID")))
        	{
        		JOptionPane.showMessageDialog(null, "Please Use Any One Field Either Select or Enter Employee ID in TextBox.");
        		return;
        	}
        	else{
        		 JOptionPane.showMessageDialog(null, "Please Select or Enter Employee ID First.");
                 return;
        	}
            
            String query = "insert into teacherleave values('"+empid+"', '"+date+"', '"+duration+"')";
            
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
        	tfempid.setText(" ");
        	cEmpId.select(0);
        	dcdate.setDate(null);
        	ctime.select(0);
        }
        else
        {
            setVisible(false);
        }
    }

  //  public static void main(String[] args) {    new TeacherLeave();   }
}
