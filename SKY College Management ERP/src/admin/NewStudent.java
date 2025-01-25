package admin;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import collegeManagement.Admin;
import collegeManagement.JdbcConnection;

public class NewStudent extends JFrame {
	
    public NewStudent() {
        setTitle("Admin Dashboard");
        setSize(1350, 700);
        setLayout(null);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"RollNo", "Name", "FName", "Course","Branch","Semester","Phone","Email","Aadhar","DOB","Qualification","X %","XII %","Address"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 1300, 500);
        add(scrollPane);

        JButton approveButton = new JButton("Approve");
        approveButton.setBounds(150, 580, 100, 40);
        add(approveButton);

        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(550, 580, 100, 40);
        add(rejectButton);
        
        JButton viewButton = new JButton("View");
        viewButton.setBounds(550, 580, 100, 40);
        add(viewButton);

        try {
        	String sql = "SELECT * FROM temp_student";
        	
         //   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");   
            
            JdbcConnection conn = new JdbcConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("rollno"), rs.getString("name"), rs.getString("fname"), rs.getString("course"), rs.getString("branch"),rs.getString("semester"),rs.getString("phone"),rs.getString("email"),rs.getString("aadhar"),rs.getDate("dob"),rs.getString("qualification"),rs.getFloat("classx"),rs.getFloat("classxii"),rs.getString("address"), });
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            String rollno = "";   
            String name = "";     
            String fname = "";  
            String phone = ""; 
            String email = ""; 
            String aadhar = "";   
            Date dob = null ;      
            String address = ""; 
            Blob pic = null;   
            String branch = "";  
            String course = "";  
            String qualification = "";   // nn
            float classx = 0 ;    // float
            String classxii = "" ;  // float
            String semester = "";   // nn
            String gender = "";   // nn
            Date doa = null ;  // date  nn
            
            if (selectedRow != -1) {
                String id =  (String) model.getValueAt(selectedRow, 0);
               // String id = "2504";
                try {
                 //   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");
                    JdbcConnection conn = new JdbcConnection();
               //   JOptionPane.showMessageDialog(null, "Selected Roll : "+id);
                    
                    String q = "select * from temp_student where rollno = ?";
                	PreparedStatement psst = conn.prepareStatement(q);
                	psst.setString(1, id);
                	ResultSet rsup = psst.executeQuery();
                    while(rsup.next()) 
                    {
                    	 rollno = rsup.getString("rollno");
                         name = rsup.getString("name");
                         fname = rsup.getString("fname");
                         phone = rsup.getString("phone");
                         email = rsup.getString("email");
                         aadhar = rsup.getString("aadhar");
                         dob = rsup.getDate("dob");
                         address = rsup.getString("address");
                         pic = rsup.getBlob("pic");
                         branch = rsup.getString("branch");
                         course = rsup.getString("course");
                         qualification = rsup.getString("qualification");
                         classx = rsup.getFloat("classx");
                         classxii = rsup.getString("classxii");
                         semester = rsup.getString("semester");
                         gender = rsup.getString("gender");
                         doa = rsup.getDate("doa");                       
                        
                    }               
                    
            //  JOptionPane.showMessageDialog(null, "Roll : "+rollno+" , Name : "+name+" , Fname : "+fname+" , Phone : "+phone+" , Email : "+email+" , Aadhaar : "+aadhar+" , DOB : "+dob+" , Address : "+address+" , Pic : "+pic+" , Course : "+course+" , Branch : "+branch+" , Qualification : "+qualification+" , ClassX : "+classx+" , ClassXII : "+classxii+" , Gender : "+gender+" , DOA : "+doa+" ,  > " );

                    
                    String query = "INSERT INTO student (rollno, name, fname, phone, email, aadhar, dob, address, pic, branch, course, qualification, classx, classxii, semester, gender, doa ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
                    PreparedStatement ps = conn.prepareStatement(query);
                    ps.setString(1, rollno);
                    ps.setString(2, name);
                    ps.setString(3, fname);
                    ps.setString(4, phone);
                    ps.setString(5, email);
                    ps.setString(6, aadhar);
                    ps.setDate(7, dob);
                    ps.setString(8, address);
                    ps.setBlob(9, pic);
                    ps.setString(10, branch);
                    ps.setString(11, course);
                    ps.setString(12, qualification);
                    ps.setFloat(13, classx);
                    ps.setString(14, classxii);
                    ps.setString(15, semester);
                    ps.setString(16, gender);
                    ps.setDate(17, doa);
                	ps.executeUpdate();
                	
                	 String deleteSql1 = "DELETE FROM temp_student WHERE rollno = ?";
                     PreparedStatement deletePs1 = conn.prepareStatement(deleteSql1);
                     deletePs1.setString(1, id);
                     deletePs1.executeUpdate();

                     String role = "";
                     String username =  "";
                     String password =  "";
                     String sec_ques = "";
                     String sec_ans =  "";
                     
                     String q2 = "select * from temp_login where id = ?";
                 	PreparedStatement psst2 = conn.prepareStatement(q2);
                 	psst2.setString(1, id);
                 	ResultSet rsup2 = psst2.executeQuery();
                     while(rsup2.next()) 
                     {
                     	 role = rsup2.getString("role");
                         username = rsup2.getString("username");
                         password = rsup2.getString("password");
                         sec_ques = rsup2.getString("sec_ques");
                         sec_ans = rsup2.getString("sec_ans");
                           
                     }       
                     
                 //  JOptionPane.showMessageDialog(null, "Role : "+role+" , UserName : "+username+" , Password : "+password+" , Sec_Ques : "+sec_ques+" , Sec_Ans : "+sec_ans+"  ,  > " );

                     
                     String query2 = "INSERT INTO login (role, username, password, sec_ques, sec_ans, id ) VALUES (?, ?, ?, ?, ?, ? )";
                     PreparedStatement ps2 = conn.prepareStatement(query2);
                     ps2.setString(1, role);
                     ps2.setString(2, username);
                     ps2.setString(3, password);
                     ps2.setString(4, sec_ques);
                     ps2.setString(5, sec_ans);
                     ps2.setString(6, rollno);                   	
                     ps2.executeUpdate();
                     
                    
                    String copySql = "INSERT INTO login (role, username, password, sec_ques, sec_ans, id ) SELECT role, username, password, sec_ques, sec_ans, id FROM temp_student WHERE id = ?";
                    PreparedStatement copyPs = conn.prepareStatement(copySql);
                  //  copyPs.setString(1, rollno);
                    copyPs.setString(1, id);
                  //  copyPs.executeUpdate();

                    String deleteSql = "DELETE FROM temp_login WHERE id = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setString(1, id);
                    deletePs.executeUpdate();

                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Student Approved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
               // int id = (int) model.getValueAt(selectedRow, 0);
                String id =  (String) model.getValueAt(selectedRow, 0);
               // JOptionPane.showMessageDialog(null, "Selected Roll : "+id);
                try {
                  //  Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");
                    JdbcConnection conn = new JdbcConnection();
                    
                    String deleteSql = "DELETE FROM temp_student WHERE rollno = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setString(1, id);
                    deletePs.executeUpdate();
                    
                    String deleteSql3 = "DELETE FROM temp_login WHERE id = ?";
                    PreparedStatement deletePs3 = conn.prepareStatement(deleteSql3);
                    deletePs3.setString(1, id);
                    deletePs3.executeUpdate();

                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Student Rejected!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        viewButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int roll = (int) model.getValueAt(selectedRow, 0);
                String rollno ;
                
            }
        });
        
        setVisible(true);
    }
    
 //   public static void main(String[] args) {     new NewStudent();    }
}


