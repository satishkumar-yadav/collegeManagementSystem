package admin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.text.SimpleDateFormat;

public class UpdateStudent extends JFrame implements ActionListener{
    
  //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adjust the format as needed
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    JTextField tfaddress, tfphone, tfemail, tfbranch, tfsemester, tfroll;
    JLabel labelrollno, picLabel, labelname, labelfname, labelx, labelxii, labelaadhar, labelcourse, labelgender, labelqualification, labeldob, labeldoa ;
    JButton update, cancel, change, search;
    Choice crollno;
    FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
    
    UpdateStudent() {
        
        setSize(900, 700);
        setLocation(350, 50);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Student Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        tfroll = new JTextField();
        tfroll.setBounds(50, 70, 160, 30);
        add(tfroll);
        
        search = new JButton("Search");
        search.setBounds(250, 70, 100, 30);
        search.addActionListener(this);
        search.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(search);
        
        JLabel lblrollnumber = new JLabel("Select Roll Number");
        lblrollnumber.setBounds(50, 110, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);
        
        crollno = new Choice();
        crollno.setBounds(250, 110, 100, 20);
        add(crollno);
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from student");
            while(rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JLabel lblpic = new JLabel("Profile Picture");
        lblpic.setBounds(480, 80, 125, 30);
        lblpic.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpic);
        
        change = new JButton("Change");
        change.setBounds(480, 120, 100, 30);
        change.addActionListener(this);
        change.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(change);
        
        picLabel = new JLabel("          No File Chosen  ");
        picLabel.setBounds(610, 12, 150, 135);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(picLabel);
        
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
        
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        add(tfaddress);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email Id");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        add(tfemail);
        
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
        
        tfbranch = new JTextField();
        tfbranch.setBounds(600, 400, 150, 30);
        add(tfbranch);
        
        JLabel lblsemester = new JLabel("Semester");
        lblsemester.setBounds(50, 450, 200, 30);
        lblsemester.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsemester);
        
        tfsemester = new JTextField();
        tfsemester.setBounds(200, 450, 150, 30);
        add(tfsemester);
        
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
        
        /*
        try {        // load default student details 
        	JdbcConnection c1 = new JdbcConnection();
            String query = "select * from student where rollno='"+crollno.getSelectedItem()+"'";
            ResultSet rs = c1.st.executeQuery(query);
            while(rs.next()) {
                labelname.setText(rs.getString("name"));
                labelfname.setText(rs.getString("fname"));
                tfaddress.setText(rs.getString("address"));
                tfphone.setText(rs.getString("phone"));
                tfemail.setText(rs.getString("email"));
                labelx.setText(rs.getString("classx"));
                labelxii.setText(rs.getString("classxii"));
                labelaadhar.setText(rs.getString("aadhar"));
                labelrollno.setText(rs.getString("rollno"));
                labelcourse.setText(rs.getString("course"));
                tfbranch.setText(rs.getString("branch"));
                tfsemester.setText(rs.getString("semester"));
                labelgender.setText(rs.getString("gender"));
                labelqualification.setText(rs.getString("qualification"));
                
                Blob b = rs.getBlob("pic");
                is = b.getBinaryStream();
                BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledPic);
                picLabel.setIcon(icon);
                
               // labeldob.setText(rs.getString("dob"));
              //  labeldoa.setText(rs.getString("doa"));
                
                // Parse the "dob" and "doa" as Date objects and format them
                String dobString = rs.getString("dob");
                String doaString = rs.getString("doa");

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
            
          //  String dob = "";
//            try {
//                String dob = dateFormat.format(labeldoa1);
//            } catch (Exception e) {
//                JOptionPane.showMessageDialog(this, "Please Select a Valid Date of Birth.", "Invalid DOB", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
        
        // dynamically change details of student on selected rollno
        crollno.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                try {
                	JdbcConnection c2 = new JdbcConnection();
                    String query2 = "select * from student where rollno='"+crollno.getSelectedItem()+"'";
                    ResultSet rs2 = c2.st.executeQuery(query2);
                    while(rs2.next()) {
                    	labelname.setText(rs2.getString("name"));
                        labelfname.setText(rs2.getString("fname"));
                        tfaddress.setText(rs2.getString("address"));
                        tfphone.setText(rs2.getString("phone"));
                        tfemail.setText(rs2.getString("email"));
                        labelx.setText(rs2.getString("classx"));
                        labelxii.setText(rs2.getString("classxii"));
                        labelaadhar.setText(rs2.getString("aadhar"));
                        labelrollno.setText(rs2.getString("rollno"));
                        labelcourse.setText(rs2.getString("course"));
                        tfbranch.setText(rs2.getString("branch"));
                        tfsemester.setText(rs2.getString("semester"));
                        labelgender.setText(rs2.getString("gender"));
                        labelqualification.setText(rs2.getString("qualification"));
                        
                        Blob b = rs2.getBlob("pic");
                        is = b.getBinaryStream();
                        BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                        Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                        icon = new ImageIcon(scaledPic);
                        picLabel.setIcon(icon);
                        
                       // labeldob.setText(rs.getString("dob"));
                      //  labeldoa.setText(rs.getString("doa"));
                        
                        // Parse the "dob" and "doa" as Date objects and format them
                        String dobString = rs2.getString("dob");
                        String doaString = rs2.getString("doa");

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
        });
        
        
//        crollno.addItemListener(new ItemListener() {
//            public void itemStateChanged(ItemEvent ie) {
//                loadStudentDetails(crollno.getSelectedItem());
//            }
//        });

        
        update = new JButton("Update");
        update.setBounds(250, 580, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        update.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 580, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    
    private void loadStudentDetails(String rollno) {
//        try (JdbcConnection con = new JdbcConnection();
//             PreparedStatement pstmt = con.st.prepareStatement("SELECT * FROM student WHERE rollno = ?")) {
//            pstmt.setString(1, rollno);
//            ResultSet rs = pstmt.executeQuery();
//            if (rs.next()) {
//                labelname.setText(rs.getString("name"));
//                labelfname.setText(rs.getString("fname"));
//                labeldob.setText(rs.getString("dob"));
//                tfaddress.setText(rs.getString("address"));
//                tfphone.setText(rs.getString("phone"));
//                tfemail.setText(rs.getString("email"));
//                labelx.setText(rs.getString("class_x"));
//                labelxii.setText(rs.getString("class_xii"));
//                labelaadhar.setText(rs.getString("aadhar"));
//                labelrollno.setText(rs.getString("rollno"));
//                tfcourse.setText(rs.getString("course"));
//                tfbranch.setText(rs.getString("branch"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    
    
    public void actionPerformed(ActionEvent ae) {
    	
    	if (ae.getSource() == search )
    	{
    		 try {
             	JdbcConnection c3 = new JdbcConnection();
                 String query3 = "select * from student where rollno='"+tfroll.getText().trim()+"'";
                 ResultSet rs3 = c3.st.executeQuery(query3);
                 while(rs3.next()) {
                 	labelname.setText(rs3.getString("name"));
                     labelfname.setText(rs3.getString("fname"));
                     tfaddress.setText(rs3.getString("address"));
                     tfphone.setText(rs3.getString("phone"));
                     tfemail.setText(rs3.getString("email"));
                     labelx.setText(rs3.getString("classx"));
                     labelxii.setText(rs3.getString("classxii"));
                     labelaadhar.setText(rs3.getString("aadhar"));
                     labelrollno.setText(rs3.getString("rollno"));
                     labelcourse.setText(rs3.getString("course"));
                     tfbranch.setText(rs3.getString("branch"));
                     tfsemester.setText(rs3.getString("semester"));
                     labelgender.setText(rs3.getString("gender"));
                     labelqualification.setText(rs3.getString("qualification"));
                     
                     Blob b = rs3.getBlob("pic");
                     is = b.getBinaryStream();
                     BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                     Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                     icon = new ImageIcon(scaledPic);
                     picLabel.setIcon(icon);
                     
                    // labeldob.setText(rs.getString("dob"));
                   //  labeldoa.setText(rs.getString("doa"));
                     
                     // Parse the "dob" and "doa" as Date objects and format them
                     String dobString = rs3.getString("dob");
                     String doaString = rs3.getString("doa");

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
    	
        else if (ae.getSource() == change )
    	{
    		 JFileChooser fileChooser = new JFileChooser();
             fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
             int result = fileChooser.showOpenDialog(this);
             if (result == JFileChooser.APPROVE_OPTION) {
                 selectedFile = fileChooser.getSelectedFile();
                 ImageIcon icon2 = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH));
                 picLabel.setIcon(icon2);
             }
             
    	}
    	
    	else if (ae.getSource() == update) {
       
            String rollno = labelrollno.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String branch = tfbranch.getText();
            String semester = tfsemester.getText();
           
            
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the student details?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // Proceed with the update
            
            try {
            	 JdbcConnection con = new JdbcConnection();
            	 //	fis = new FileInputStream(selectedFile);
            	
              //  String query = "update student set address='"+address+"', phone='"+phone+"', email='"+email+"', branch='"+branch+"' , semester= '"+semester+"' where rollno='"+rollno+"'";  
                String query = "UPDATE student SET address = ?, phone = ?, email = ?, branch = ?, semester= ?, pic = ? WHERE rollno = ?";
                
             // Check if there's an icon in picLabel
              //  if (picLabel.getIcon() != null) {
                    // Extract the Image from the ImageIcon
                    ImageIcon icon3 = (ImageIcon) picLabel.getIcon();
                    Image image = icon3.getImage();

                    // Convert the Image to a byte array
                    BufferedImage bufferedImage = new BufferedImage(
                        image.getWidth(null),
                        image.getHeight(null),
                        BufferedImage.TYPE_INT_RGB
                    );
                    Graphics2D g2d = bufferedImage.createGraphics();
                    g2d.drawImage(image, 0, 0, null);
                    g2d.dispose();

                    // Write the image to a ByteArrayOutputStream
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ImageIO.write(bufferedImage, "jpg", baos);
                    byte[] imageBytes = baos.toByteArray();
            //    }    
                
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, address);
                pstmt.setString(2, phone);
                pstmt.setString(3, email);
                pstmt.setString(4, branch);
                pstmt.setString(5, semester);
                pstmt.setString(7, rollno);
                pstmt.setBytes(6, imageBytes);
                //     ps.setBinaryStream(9, fis, (int) selectedFile.length());
                
            //    con.st.executeUpdate(query);
                
                int flag = pstmt.executeUpdate();
                if (flag > 0) {
                    JOptionPane.showMessageDialog(this, "Student Details Updated Successfully!");
                   // return true;
                    // setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Student Details. ");
                  //  return false;
                }
               
               // setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
            }
        } else {
            setVisible(false);
        }
    }
    
 //   public static void main(String[] args) {      new UpdateStudent();   }
}



/*
To convert the text from labeldob (which is a String) back to a Date object, you need to parse it using the same SimpleDateFormat 
you used to format it earlier. Here's how to do it:

Code to Convert labeldob Text Back to a Date Object
try {
    // Initialize the date format matching the format of labeldob's text
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    // Get the text from labeldob
    String dobText = labeldob.getText();

    // Parse the text back to a Date object
    Date dob = dateFormat.parse(dobText);

    // Now dob is a Date object that you can use elsewhere
    System.out.println("Parsed Date: " + dob);
} catch (ParseException e) {
    // Handle invalid date formats
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "Invalid date format. Please check the input.", "Error", JOptionPane.ERROR_MESSAGE);
}



Explanation:
SimpleDateFormat.parse():

Converts a String (formatted as yyyy-MM-dd) back into a Date object.
Throws a ParseException if the input string doesn't match the expected format.
Input Example:

If labeldob.getText() is "2000-01-15", then dateFormat.parse("2000-01-15") returns a Date object representing January 15, 2000.
Error Handling:

If the text in labeldob doesn't match the yyyy-MM-dd format (e.g., it's empty or incorrectly formatted), the code will catch the 
ParseException and display an error message.


*/