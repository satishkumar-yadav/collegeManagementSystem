package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentLeaveDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTextField tfrollno;
    JTable table;
    JButton search, print, cancel, reset;
    
    StudentLeaveDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);
        
        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 20);
        crollno.add("Select RollNo");
        add(crollno);
        
        tfrollno = new JTextField();
        tfrollno.setBounds(340, 20, 150, 20);
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
        
        table = new JTable();
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from studentleave");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);
        
        search = new JButton("Search");
        search.setBounds(20, 70, 80, 20);
        search.addActionListener(this);
        add(search);
        
        print = new JButton("Print");
        print.setBounds(120, 70, 80, 20);
        print.addActionListener(this);
        add(print);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(220, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        reset = new JButton("Reset");
        reset.setBounds(320, 70, 80, 20);
        reset.addActionListener(this);
        add(reset);
        
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == search) {
        	String roll1 = tfrollno.getText().trim();
            String roll2 = crollno.getSelectedItem();
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
            
            String query = "select * from studentleave where rollno = '"+rollno+"'";
            try {
            	JdbcConnection c1 = new JdbcConnection();
                ResultSet rs = c1.st.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == reset)
        {
        	tfrollno.setText(" ");
        	crollno.select(0);
        }
        else 
        {
            setVisible(false);
        }
    }

   // public static void main(String[] args) {    new StudentLeaveDetails();   }
}
