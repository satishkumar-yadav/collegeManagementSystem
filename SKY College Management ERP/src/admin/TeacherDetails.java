package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class TeacherDetails extends JFrame implements ActionListener {

    Choice cEmpId;
    JTextField tfempid;
    JTable table;
    JButton search, print, update, add, cancel, reset ;
    
    TeacherDetails() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel heading = new JLabel("Search by Employee Id");
        heading.setBounds(20, 20, 150, 20);
        add(heading);
        
        cEmpId = new Choice();
        cEmpId.setBounds(180, 20, 100, 20);
        cEmpId.add("Select EmpID");
        add(cEmpId);
        
        tfempid = new JTextField();
        tfempid.setBounds(290, 20, 100, 20);
        add(tfempid);
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from teacher");
            while(rs.next()) {
                cEmpId.add(rs.getString("empId"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table = new JTable();
        
        /*
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from teacher");
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
        	
        	String id1 = tfempid.getText().trim();
        	String id2 = cEmpId.getSelectedItem();
        	String empid = "";
        	
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
        		JOptionPane.showMessageDialog(null, "Please Use Any One Field Either Select or Enter EmpID in TextBox.");
        		return;
        	}
        	else{
        		 JOptionPane.showMessageDialog(null, "Please Select or Enter Employee ID to Search.");
                 return;
        	}
        	
             String query = "SELECT * FROM teacher WHERE empid = ?";
            
            try  
            {   JdbcConnection c1 = new JdbcConnection();
            	PreparedStatement pstmt = c1.prepareStatement(query);
                pstmt.setString(1, empid);
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
            new AddTeacher();
        } else if (ae.getSource() == update) {
            setVisible(false);
            new UpdateTeacher();
        } else if (ae.getSource() == reset) {
            tfempid.setText(" ");
            cEmpId.select(0);
        } 
        else {
            setVisible(false);
        }
    }

  //  public static void main(String[] args) {      new TeacherDetails();    }
}
