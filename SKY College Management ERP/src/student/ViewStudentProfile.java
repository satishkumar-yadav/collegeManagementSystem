package student;

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

public class ViewStudentProfile extends JFrame {
    
  //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adjust the format as needed
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    JLabel labelrollno, picLabel, labelname, labelfname, labelx, labelxii, labelaadhar, labelcourse, labelgender, labelqualification, labeldob,
    labeldoa, labeladdress, labelphone, labelemail, labelbranch, labelsemester, labelrole ;
    FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
    String rollno = "";
    
    public ViewStudentProfile(String rollno) {
        this.rollno = rollno;
        
        setSize(900, 700);
        setLocation(350, 50);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("Student Details :");
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
        
        JLabel lblrollno = new JLabel("Roll Number");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelrollno = new JLabel();
        labelrollno.setBounds(200, 200, 200, 30);
        labelrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelrollno);
        
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
        
        JLabel lblcourse = new JLabel("Course");
        lblcourse.setBounds(50, 400, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        labelcourse = new JLabel();
        labelcourse.setBounds(200, 400, 150, 30);
        labelcourse.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelcourse);
        
        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setBounds(400, 400, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);
        
        labelbranch = new JLabel();
        labelbranch.setBounds(600, 400, 150, 30);
        labelbranch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelbranch);
        
        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(50, 450, 200, 30);
        lblsemester.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsemester);
        
        labelsemester = new JLabel();
        labelsemester.setBounds(200, 450, 150, 30);
        labelsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelsemester);
        
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
        
        JLabel lbldoa = new JLabel("Date of Admission");
        lbldoa.setBounds(400, 500, 200, 30);
        lbldoa.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoa);
        
        labeldoa = new JLabel();
        labeldoa.setBounds(600, 500, 150, 30);
        labeldoa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldoa);
        
        loadStudentDetails(rollno);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
         	  // new Student();
         	  setVisible(false);
            }
        });
        
        setVisible(true);
    }
    
    
    
    private void loadStudentDetails(String rollno) {
   	 try {       
        	JdbcConnection sup = new JdbcConnection();
        //	JOptionPane.showMessageDialog(this, "Connection Established. ");
        	// String query = "select * from student where rollno= '" + rollno + "'";
        	String q = "select * from student where rollno = ?";
        	PreparedStatement psst = sup.prepareStatement(q);
        	psst.setString(1, rollno);
        	
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
                labelrollno.setText(rsup.getString("rollno"));
                labelcourse.setText(rsup.getString("course"));
                labelbranch.setText(rsup.getString("branch"));
                labelsemester.setText(rsup.getString("semester"));
                labelgender.setText(rsup.getString("gender"));
                labelqualification.setText(rsup.getString("qualification"));
                labelrole.setText(rsup.getString("role"));
                
                Blob b = rsup.getBlob("pic");
                is = b.getBinaryStream();
                BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledPic);
                picLabel.setIcon(icon);
                
                // Parse the "dob" and "doa" as Date objects and format them
                String dobString = rsup.getString("dob");
                String doaString = rsup.getString("doa");

                if (dobString != null && !dobString.isEmpty()) {
                    Date dob = java.sql.Date.valueOf(dobString); // Converts String to SQL Date
                    labeldob.setText(dateFormat.format(dob));   // Format and set the text
                } else {
                    labeldob.setText(""); // Handle null or empty DOB
                }

                if (doaString != null && !doaString.isEmpty()) {
                    Date doa = java.sql.Date.valueOf(doaString); // Converts String to SQL Date
                    labeldoa.setText(dateFormat.format(doa));   // Format and set the text
                } else {
                    labeldoa.setText(""); // Handle null or empty DOA
                }
                
            }
           
            
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

    
    
  //  public static void main(String[] args) {     new ViewStudentProfile(); }
}


