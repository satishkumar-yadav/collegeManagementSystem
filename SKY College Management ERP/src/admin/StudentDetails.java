package collegeManagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTextField tfrollno;
    JTable table;
    JButton search, print, update, add, cancel, reset ;
    
    StudentDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);
        
        crollno = new Choice();
        crollno.setBounds(180, 20, 100, 20);
        crollno.add("Select RollNo");
        add(crollno);
        
        tfrollno = new JTextField();
        tfrollno.setBounds(290, 20, 100, 20);
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
        /*
        try {          // auto populate
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        
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
        
        add = new JButton("Add");
        add.setBounds(220, 70, 80, 20);
        add.addActionListener(this);
        add(add);
        
        update = new JButton("Update");
        update.setBounds(320, 70, 80, 20);
        update.addActionListener(this);
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(420, 70, 80, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        reset = new JButton("Reset");
        reset.setBounds(520, 70, 80, 20);
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
        	String rollno = "";
        	
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
        		 JOptionPane.showMessageDialog(null, "Please Select or Enter Roll Number to Search.");
                 return;
        	}
        	
             String query = "SELECT * FROM student WHERE rollno = ?";
            
            try  
            {   JdbcConnection c1 = new JdbcConnection();
            	PreparedStatement pstmt = c1.prepareStatement(query);
                pstmt.setString(1, rollno);
                ResultSet rs = pstmt.executeQuery();
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (ae.getSource() == print) {
            try {
                table.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == add) {
            setVisible(false);
            new AddStudent();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateStudent();
        } else if (ae.getSource() == reset) {
            tfrollno.setText(" ");
            crollno.select(0);
        } else {
            setVisible(false);
        }
    }

    
    
  //  public static void main(String[] args) {     new StudentDetails();    }
    
}


/*

Table Initialization:

Right now, the table is populated twice when the form loads: once when initializing the Choice component and again for displaying the full data. If 
you only want the table to display data when a specific search is made, you can initialize the table with no data initially and only populate it when 
the user selects a roll number or searches.

Search Button UI Update:

Before executing the search, it’s better to check if the user has selected a roll number from the Choice dropdown. If no selection is made, show a warning.
 <code > 
   		if (crollno.getSelectedItem().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please select a roll number to search.");
    return;
}
  <code> 
  
  


*/