package teacher;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import collegeManagement.JdbcConnection;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;

public class ViewTeacherProfile extends JFrame {
    
  //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adjust the format as needed
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    JLabel labelempid, picLabel, labelname, labelfname, labelx, labelxii, labelaadhar, labeldepartment, labelgender, labelqualification, labeldob,
    labeldoj, labeladdress, labelphone, labelemail, labelsubject, labelgrad, labelrole, labelpg, labelphd, labelisphd, labeldesignation ;
    FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
    String empid = "";
    
    public ViewTeacherProfile(String empId) {
         empid = empId;
        
        setSize(900, 700);
        setLocation(350, 50);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("Teacher Details :");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblpic = new JLabel("Profile Picture :");
        lblpic.setBounds(400, 80, 150, 30);
        lblpic.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpic);
        
        picLabel = new JLabel("   No Picture Available ");
        picLabel.setBounds(610, 12, 150, 135);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(picLabel);
        
        JLabel lblrole = new JLabel("Role");
        lblrole.setBounds(50, 100, 100, 30);
        lblrole.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrole);
        
        labelrole = new JLabel();
        labelrole.setBounds(200, 100, 150, 30);
        labelrole.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelrole);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        labelname = new JLabel();
        labelname.setBounds(200, 150, 150, 30);
        labelname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelname);
        
        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        labelfname = new JLabel();
        labelfname.setBounds(600, 150, 150, 30);
        labelfname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelfname);
        
        JLabel lblempid = new JLabel("Employee ID");
        lblempid.setBounds(50, 200, 200, 30);
        lblempid.setFont(new Font("serif", Font.BOLD, 20));
        add(lblempid);
        
        labelempid = new JLabel();
        labelempid.setBounds(200, 200, 200, 30);
        labelempid.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelempid);
        
        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        labeldob = new JLabel();
        labeldob.setBounds(600, 200, 150, 30);
        labeldob.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldob);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        labeladdress = new JLabel();
        labeladdress.setBounds(200, 250, 150, 30);
        labeladdress.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeladdress);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        labelphone = new JLabel();
        labelphone.setBounds(600, 250, 150, 30);
        labelphone.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelphone);
        
        JLabel lblemail = new JLabel("Email Id");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        labelemail = new JLabel();
        labelemail.setBounds(200, 300, 150, 30);
        labelemail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelemail);
        
        JLabel lblx = new JLabel("Class X (%)");
        lblx.setBounds(400, 300, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);
        
        labelx = new JLabel();
        labelx.setBounds(600, 300, 150, 30);
        labelx.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelx);
        
        JLabel lblxii = new JLabel("Class XII (%)");
        lblxii.setBounds(50, 350, 200, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);
        
        labelxii = new JLabel();
        labelxii.setBounds(200, 350, 150, 30);
        labelxii.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelxii);
        
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(400, 350, 200, 30);
        lblaadhar.setFont(new Font("serif", Font.BOLD, 20));
        add(lblaadhar);
        
        labelaadhar = new JLabel();
        labelaadhar.setBounds(600, 350, 150, 30);
        labelaadhar.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelaadhar);
        
        JLabel lbldept = new JLabel("Department");
        lbldept.setBounds(50, 400, 200, 30);
        lbldept.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldept);
        
        labeldepartment = new JLabel();
        labeldepartment.setBounds(200, 400, 150, 30);
        labeldepartment.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldepartment);
        
        JLabel lblsubject = new JLabel("Subject");
        lblsubject.setBounds(400, 400, 200, 30);
        lblsubject.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsubject);
        
        labelsubject = new JLabel();
        labelsubject.setBounds(600, 400, 150, 30);
        labelsubject.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelsubject);
        
        JLabel lblgrad = new JLabel("Grad (%)");
        lblgrad.setBounds(50, 450, 200, 30);
        lblgrad.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgrad);
        
        labelgrad = new JLabel();
        labelgrad.setBounds(200, 450, 150, 30);
        labelgrad.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelgrad);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(400, 450, 200, 30);
        lblgender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgender);
        
        labelgender = new JLabel();
        labelgender.setBounds(600, 450, 150, 30);
        labelgender.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelgender);
        
        JLabel lblqualification = new JLabel("Qualification");
        lblqualification.setBounds(50, 500, 200, 30);
        lblqualification.setFont(new Font("serif", Font.BOLD, 20));
        add(lblqualification);
        
        labelqualification = new JLabel();
        labelqualification.setBounds(200, 500, 150, 30);
        labelqualification.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelqualification);
        
        JLabel lbldoj = new JLabel("Date of Joining");
        lbldoj.setBounds(400, 500, 200, 30);
        lbldoj.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoj);
        
        labeldoj = new JLabel();
        labeldoj.setBounds(600, 500, 150, 30);
        labeldoj.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldoj);
        
        JLabel lblpg = new JLabel("PG (%)");
        lblpg.setBounds(50, 550, 200, 30);
        lblpg.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpg);
        
        labelpg = new JLabel("--");
        labelpg.setBounds(200, 550, 150, 30);
        labelpg.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelpg);
        
        JLabel lblisphd = new JLabel("Is Phd Holder ?");
        lblisphd.setBounds(400, 550, 200, 30);
        lblisphd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblisphd);
        
        labelisphd = new JLabel("--");
        labelisphd.setBounds(600, 550, 150, 30);
        labelisphd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelisphd);
        
        JLabel lbldesignation = new JLabel("Designation");
        lbldesignation.setBounds(50, 600, 200, 30);
        lbldesignation.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldesignation);
        
        labeldesignation = new JLabel("--");
        labeldesignation.setBounds(200, 600, 150, 30);
        labeldesignation.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldesignation);
        
        JLabel lblphd = new JLabel("Phd (Paper)");
        lblphd.setBounds(400, 600, 200, 30);
        lblphd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphd);
        
        labelphd = new JLabel("--");
        labelphd.setBounds(600, 600, 150, 30);
        labelphd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelphd);
        
        loadTeacherDetails(empid);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
         	  setVisible(false);
            }
        });
        
        setVisible(true);
    }
    
    
    
    private void loadTeacherDetails(String empid) {
   	 try {       
        	JdbcConnection sup = new JdbcConnection();
       
        	String q = "select * from teacher where empid = ?";
        	PreparedStatement psst = sup.prepareStatement(q);
        	psst.setString(1, empid);
        	
        	ResultSet rsup = psst.executeQuery();
            while(rsup.next()) {
                labelname.setText(rsup.getString("name"));
                labelfname.setText(rsup.getString("fname"));
                labeladdress.setText(rsup.getString("address"));
                labelphone.setText(rsup.getString("phone"));
                labelemail.setText(rsup.getString("email"));
                labelx.setText(rsup.getString("classx"));
                labelxii.setText(rsup.getString("classxii"));
                labelaadhar.setText(rsup.getString("aadhar"));
                labelempid.setText(rsup.getString("empid"));
                labeldepartment.setText(rsup.getString("department"));
                labelsubject.setText(rsup.getString("subject"));
                labelgrad.setText(rsup.getString("grad"));
                labelgender.setText(rsup.getString("gender"));
                labelqualification.setText(rsup.getString("qualification"));
                labelpg.setText(rsup.getString("pg"));
                labelphd.setText(rsup.getString("phd"));
                labelisphd.setText(rsup.getString("isphd"));
                labelrole.setText(rsup.getString("role"));
               // labeldesignation.setText(rsup.getString("designation"));
                
                Blob b = rsup.getBlob("pic");
                is = b.getBinaryStream();
                BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledPic);
                picLabel.setIcon(icon);
                
                // Parse the "dob" and "doa" as Date objects and format them
                String dobString = rsup.getString("dob");
                String dojString = rsup.getString("doj");

                if (dobString != null && !dobString.isEmpty()) {
                    Date dob = java.sql.Date.valueOf(dobString); // Converts String to SQL Date
                    labeldob.setText(dateFormat.format(dob));   // Format and set the text
                } else {
                    labeldob.setText(""); // Handle null or empty DOB
                }

                if (dojString != null && !dojString.isEmpty()) {
                    Date doj = java.sql.Date.valueOf(dojString); // Converts String to SQL Date
                    labeldoj.setText(dateFormat.format(doj));   // Format and set the text
                } else {
                    labeldoj.setText(""); // Handle null or empty DOA
                }
                
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    
    
   // public static void main(String[] args) {     new ViewTeacherProfile(); }
}


