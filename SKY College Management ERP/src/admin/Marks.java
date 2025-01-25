package admin;

import java.awt.*;
import javax.swing.*;

import collegeManagement.JdbcConnection;
import net.proteanit.sql.DbUtils;

import java.sql.*;
import java.awt.event.*;

public class Marks extends JFrame implements ActionListener {
    
	Choice crollno;
	//String rollno ="2501";
	JTextField tfrollno;
	JLabel sub1, sub2, sub3, sub4, sub5, lblsemester ;
    JButton cancel, search, print, update, add,  reset, viewAll ;
    
    public Marks() {
       
        
        setSize(600, 500);
        setLocation(500, 100);
        setLayout(null);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Bihar Engineering Univeristy");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);
        
        JLabel subheading = new JLabel("Result of Examination 2024");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);
        
        JLabel lblrollno = new JLabel("Select Roll No:");
        lblrollno.setBounds(50, 100, 120, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);
        
        crollno = new Choice();
        crollno.setBounds(180, 100, 100, 20);
        crollno.add("Select RollNo");
        add(crollno);
        
        tfrollno = new JTextField();
        tfrollno.setBounds(290, 100, 100, 20);
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
        
        lblsemester = new JLabel();
        lblsemester.setBounds(100, 150, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lblsemester);
        
        sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);
        
        sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);
        
        sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);
        
        sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);
        
        sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);
        
//        try (JdbcConnection c1 = new JdbcConnection()) {
//            String query1 = "SELECT * FROM subject WHERE rollno = ?";
//            try (PreparedStatement pstmt1 = c1.st.prepareStatement(query1)) {
//                pstmt1.setString(1, rollno);
//                ResultSet rs1 = pstmt1.executeQuery();
//                while (rs1.next()) {
//                    sub1.setText(rs1.getString("subject1"));
//                    sub2.setText(rs1.getString("subject2"));
//                    sub3.setText(rs1.getString("subject3"));
//                    sub4.setText(rs1.getString("subject4"));
//                    sub5.setText(rs1.getString("subject5"));
//                }
//            }
//            
//            String query2 = "SELECT * FROM marks WHERE rollno = ?";
//            try (PreparedStatement pstmt2 = c1.st.prepareStatement(query2)) {
//                pstmt2.setString(1, rollno);
//                ResultSet rs2 = pstmt2.executeQuery();
//                while (rs2.next()) {
//                    sub1.setText(sub1.getText() + "------------" + rs2.getString("marks1"));
//                    sub2.setText(sub2.getText() + "------------" + rs2.getString("marks2"));
//                    sub3.setText(sub3.getText() + "------------" + rs2.getString("marks3"));
//                    sub4.setText(sub4.getText() + "------------" + rs2.getString("marks4"));
//                    sub5.setText(sub5.getText() + "------------" + rs2.getString("marks5"));
//                    lblsemester.setText("Semester " + rs2.getString("semester"));
//                }
//            }
//        }
        
        
        search = new JButton("Search");
        search.setBounds(400, 100, 100, 20);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        search.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(search);
        
        cancel = new JButton("Back");
        cancel.setBounds(400, 140, 100, 20);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        print = new JButton("Print");
        print.setBounds(400, 180, 100, 20);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        print.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(print);
        
//        cancel.addActionListener(e -> {
//            setVisible(false);
//            new PreviousWindow();  // Open the previous window instead of just hiding this one
//        });

        
        
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
         	
         	showMarks(rollno) ;   
             
         }
    	 else if (ae.getSource() == print)
    	 {
    		 try {
                 //table.print();
                // this.print(JFrame);
                // JFrame.print(this);
               //  JFrame.print(this);
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
    	 }
    	 else
    	 {
    		 setVisible(false);
    	 }
        
    }
    
    
    public void showMarks(String rollno) 
    {
 	 //  JOptionPane.showMessageDialog(null, " UserName : < "+username+"  >, PassWord : < "+passWord+" >");
    	try {
        	JdbcConnection c1 = new JdbcConnection();
        	
            ResultSet rs1 = c1.st.executeQuery("select * from subject where rollno = '"+rollno+"'");
            while(rs1.next()) {
                sub1.setText(rs1.getString("sub1"));
                sub2.setText(rs1.getString("sub2"));
                sub3.setText(rs1.getString("sub3"));
                sub4.setText(rs1.getString("sub4"));
                sub5.setText(rs1.getString("sub5"));
            }
            
            ResultSet rs2 = c1.st.executeQuery("select * from marks where rollno = '"+rollno+"'");
            while(rs2.next()) {
                sub1.setText(sub1.getText() + "------------" + rs2.getString("msub1"));
                sub2.setText(sub2.getText() + "------------" + rs2.getString("msub2"));
                sub3.setText(sub3.getText() + "------------" + rs2.getString("msub3"));
                sub4.setText(sub4.getText() + "------------" + rs2.getString("msub4"));
                sub5.setText(sub5.getText() + "------------" + rs2.getString("msub5"));
                lblsemester.setText("Semester " + rs2.getString("semester"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
 	  
 	}
    
  //  public static void main(String[] args) {      new Marks();    }
}



/*
  
   UI Enhancements:
You could add more interactivity by providing a loading indicator when fetching data from the database to improve the user experience, especially 
when there are many records.
For better readability, consider adding borders, padding, and consistent font styles to improve the appearance of the text and buttons.
You might also want to center-align the content to make the form look more polished.


 
 
  
 
 */
