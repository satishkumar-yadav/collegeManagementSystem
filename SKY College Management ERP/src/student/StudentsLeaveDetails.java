package student;

import javax.swing.*;

import collegeManagement.JdbcConnection;

import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentsLeaveDetails extends JFrame implements ActionListener {

    JTable table;
    JButton search, print, cancel, reset;
    String roll;
    
    public StudentsLeaveDetails(String Roll) {
    	roll = Roll;
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel lb = new JLabel("Student Leave Details ");
        lb.setBounds(50, 20, 150, 30);
        add(lb);
        
        JLabel heading = new JLabel("Student Roll No : ");
        heading.setBounds(20, 60, 150, 20);
        add(heading);
        
        JLabel lbroll = new JLabel(roll);
        lbroll.setBounds(120, 60, 150, 20);
        add(lbroll);
        
        table = new JTable();
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from studentleave where rollno = '"+roll+"'");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 100, 500, 200);
        add(jsp);
        
        setSize(600, 400);
        setLocation(300, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
        	
            String rollno = roll;
            
            String query = "select * from studentleave where rollno = '"+rollno+"'";
            try {
            	JdbcConnection c1 = new JdbcConnection();
                ResultSet rs = c1.st.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
        else 
        {
            setVisible(false);
        }
    }

  // public static void main(String[] args) {    new StudentsLeaveDetails();   }
}
