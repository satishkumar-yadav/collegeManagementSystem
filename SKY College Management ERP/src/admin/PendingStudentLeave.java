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

public class PendingStudentLeave extends JFrame {
	
    public PendingStudentLeave() {
        setTitle("Admin Dashboard");
        setSize(700, 400);
        setLayout(null);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"RollNo", "Date", "Duration" }, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 600, 200);
        add(scrollPane);

        JButton approveButton = new JButton("Approve");
        approveButton.setBounds(80, 280, 100, 40);
        add(approveButton);

        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(250, 280, 100, 40);
        add(rejectButton);
        
        JButton viewButton = new JButton("View");
        viewButton.setBounds(420, 280, 100, 40);
        add(viewButton);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");
            String sql = "SELECT * FROM temp_studentleave ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString("rollno"), rs.getString("date"), rs.getString("duration") });
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
//            String date = "";     
//            String duration = ""; 
//            String rollno ="";
            
            if (selectedRow != -1) {
                String Id =  (String) model.getValueAt(selectedRow, 0);
                String Date =  (String) model.getValueAt(selectedRow, 1);
                String Duration =  (String) model.getValueAt(selectedRow, 2);
              //  rollno = id;
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");
               //   JOptionPane.showMessageDialog(null, "Selected Roll : "+id);
                    
                    String q = "select * from temp_studentleave where rollno = ?";
                	PreparedStatement psst = conn.prepareStatement(q);
               // 	psst.setString(1, rollno);
              /*  	ResultSet rsup = psst.executeQuery();
                    while(rsup.next()) 
                    {
                    	 date = rsup.getString("date");
                         duration = rsup.getString("duration");                   
                        
                    }             */  
                    
                               
                    String query = "INSERT INTO studentleave (rollno, date, duration ) VALUES (?, ?, ? )";
                    PreparedStatement ps = conn.prepareStatement(query);
//                    ps.setString(1, rollno);
//                    ps.setString(2, date);
//                    ps.setString(3, duration);
                //	ps.executeUpdate();
                     
                    String copySql = "INSERT INTO studentleave (rollno, date, duration ) SELECT rollno, date, duration FROM temp_studentleave WHERE rollno = ? and date = ? and duration = ? ";
                    PreparedStatement copyPs = conn.prepareStatement(copySql);
                    copyPs.setString(1, Id);
                    copyPs.setString(2, Date);
                    copyPs.setString(3, Duration);
                    copyPs.executeUpdate();
                    
                    String deleteSql1 = "DELETE FROM temp_studentleave WHERE rollno = ? and date = ? and duration = ? ";
                    PreparedStatement deletePs1 = conn.prepareStatement(deleteSql1);
                    deletePs1.setString(1, Id);
                    deletePs1.setString(2, Date);
                    deletePs1.setString(3, Duration);
                    deletePs1.executeUpdate();

                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Leave Granted !");
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
                String date =  (String) model.getValueAt(selectedRow, 1);
                String duration =  (String) model.getValueAt(selectedRow, 2);
               // JOptionPane.showMessageDialog(null, "Selected Roll : "+id);
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/collegemanagement", "root", "12345678");
                    String deleteSql = "DELETE FROM temp_studentleave WHERE rollno = ? and date = ? and duration = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setString(1, id);
                    deletePs.setString(2, date);
                    deletePs.setString(3, duration);
                    deletePs.executeUpdate();
                    
                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Leave Request Rejected!");
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
    
  //  public static void main(String[] args) {     new PendingStudentLeave() ;    }
}


