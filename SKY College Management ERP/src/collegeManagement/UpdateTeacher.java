package collegeManagement;

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

public class UpdateTeacher extends JFrame implements ActionListener{
    
    JTextField tfaddress, tfphone, tfemail, tfdepartment, tfrole, tfempid;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    JLabel labelEmpId, picLabel, labelname, labelfname, labelx, labelxii, labelaadhar, labelqualification, labelgender, labelgrad, labelpg, labelphd, labeldob, labeldoj ;
    JButton submit, cancel,change, search;
    Choice cEmpId;
    FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
    
    UpdateTeacher() {
        
        setSize(900, 700);
        setLocation(350, 50);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Teacher Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        tfempid = new JTextField();
        tfempid.setBounds(50, 70, 160, 30);
        add(tfempid);
        
        search = new JButton("Search");
        search.setBounds(250, 70, 100, 30);
        search.addActionListener(this);
        search.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(search);
        
        JLabel lblrollnumber = new JLabel("Select Employee Id");
        lblrollnumber.setBounds(50, 110, 200, 20);
        lblrollnumber.setFont(new Font("serif", Font.PLAIN, 20));
        add(lblrollnumber);
        
        cEmpId = new Choice();
        cEmpId.setBounds(250, 110, 100, 20);
        add(cEmpId);
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from teacher");
            while(rs.next()) {
                cEmpId.add(rs.getString("empid"));
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
        
        JLabel lblrollno = new JLabel("Employee Id");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelEmpId = new JLabel();
        labelEmpId.setBounds(200, 200, 200, 30);
        labelEmpId.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelEmpId);
        
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
        
        JLabel lblqualification = new JLabel("Qualification");
        lblqualification.setBounds(50, 400, 200, 30);
        lblqualification.setFont(new Font("serif", Font.BOLD, 20));
        add(lblqualification);
        
        labelqualification = new JLabel();
        labelqualification.setBounds(200, 400, 150, 30);
        labelqualification.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelqualification);
        
        JLabel lbldepartment = new JLabel("Department");
        lbldepartment.setBounds(400, 400, 200, 30);
        lbldepartment.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldepartment);
        
        tfdepartment = new JTextField();
        tfdepartment.setBounds(600, 400, 150, 30);
        add(tfdepartment);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(50, 450, 200, 30);
        lblgender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgender);
        
        labelgender = new JLabel();
        labelgender.setBounds(200, 450, 150, 30);
        labelgender.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelgender);
        
        JLabel lblrole = new JLabel("Role");
        lblrole.setBounds(400, 450, 200, 30);
        lblrole.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrole);
        
        tfrole = new JTextField();
        tfrole.setBounds(600, 450, 150, 30);
        add(tfrole);
        
        JLabel lblgrad = new JLabel("Grad");
        lblgrad.setBounds(50, 500, 200, 30);
        lblgrad.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgrad);
        
        labelgrad = new JLabel();
        labelgrad.setBounds(200, 500, 150, 30);
        labelgrad.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelgrad);
        
        JLabel lblpg = new JLabel("PG");
        lblpg.setBounds(400, 500, 200, 30);
        lblpg.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpg);
        
        labelpg = new JLabel();
        labelpg.setBounds(600, 500, 150, 30);
        labelpg.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelpg);
        
        JLabel lbldoj = new JLabel("Date Of Joining");
        lbldoj.setBounds(50, 550, 200, 30);
        lbldoj.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoj);
        
        labeldoj = new JLabel();
        labeldoj.setBounds(200, 550, 150, 30);
        labeldoj.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labeldoj);
        
        JLabel lblphd = new JLabel("Phd(Paper)");
        lblphd.setBounds(400, 550, 200, 30);
        lblphd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphd);
        
        labelphd = new JLabel();
        labelphd.setBounds(600, 550, 150, 30);
        labelphd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelphd);
        
      /*  
        try {
        	JdbcConnection c1 = new JdbcConnection();
            String query = "select * from teacher where empid='"+cEmpId.getSelectedItem()+"'";
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
                labelEmpId.setText(rs.getString("empid"));
                labelqualification.setText(rs.getString("qualification"));
                tfdepartment.setText(rs.getString("department"));
                tfrole.setText(rs.getString("role"));
                labelgender.setText(rs.getString("gender"));
                labelgrad.setText(rs.getString("grad"));
                labelpg.setText(rs.getString("pg"));
                labelphd.setText(rs.getString("phd"));
                
                Blob b = rs.getBlob("pic");
                is = b.getBinaryStream();
                BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaledPic);
                picLabel.setIcon(icon);
                
                String dobString = rs.getString("dob");
                String dojString = rs.getString("doj");

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
        */
        
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
            	 try {
                 	JdbcConnection c1 = new JdbcConnection();
                     String query = "select * from teacher where empid='"+cEmpId.getSelectedItem()+"'";
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
                         labelEmpId.setText(rs.getString("empid"));
                         labelqualification.setText(rs.getString("qualification"));
                         tfdepartment.setText(rs.getString("department"));
                         tfrole.setText(rs.getString("role"));
                         labelgender.setText(rs.getString("gender"));
                         labelgrad.setText(rs.getString("grad"));
                         labelpg.setText(rs.getString("pg"));
                         labelphd.setText(rs.getString("phd"));
                         
                         Blob b = rs.getBlob("pic");
                         is = b.getBinaryStream();
                         BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                         Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                         icon = new ImageIcon(scaledPic);
                         picLabel.setIcon(icon);
                         
                         String dobString = rs.getString("dob");
                         String dojString = rs.getString("doj");

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
        });
        
        
        submit = new JButton("Update");
        submit.setBounds(250, 600, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 600, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
    	
    	if (ae.getSource() == search )
    	{
    		 try {
              	JdbcConnection c1 = new JdbcConnection();
                  String query = "select * from teacher where empid='"+tfempid.getText().trim()+"'";
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
                      labelEmpId.setText(rs.getString("empid"));
                      labelqualification.setText(rs.getString("qualification"));
                      tfdepartment.setText(rs.getString("department"));
                      tfrole.setText(rs.getString("role"));
                      labelgender.setText(rs.getString("gender"));
                      labelgrad.setText(rs.getString("grad"));
                      labelpg.setText(rs.getString("pg"));
                      labelphd.setText(rs.getString("phd"));
                      
                      Blob b = rs.getBlob("pic");
                      is = b.getBinaryStream();
                      BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
                      Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
                      icon = new ImageIcon(scaledPic);
                      picLabel.setIcon(icon);
                      
                      String dobString = rs.getString("dob");
                      String dojString = rs.getString("doj");

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
    	
    else if (ae.getSource() == submit) {
            String empId = labelEmpId.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String department = tfdepartment.getText();
            String role = tfrole.getText();
            
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the student details?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // Proceed with the update
            
            try {
            	  JdbcConnection con = new JdbcConnection();
            	
                // String query = "update teacher set address='"+address+"', phone='"+phone+"', email='"+email+"', education='"+course+"', department='"+branch+"' where empId='"+empId+"'";
            	  String query = "UPDATE teacher SET address = ?, phone = ?, email = ?, department = ?, role= ?, pic = ? WHERE empid = ?";
            	  
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
                  
                  PreparedStatement pstmt = con.prepareStatement(query);
                  pstmt.setString(1, address);
                  pstmt.setString(2, phone);
                  pstmt.setString(3, email);
                  pstmt.setString(4, department);
                  pstmt.setString(5, role);
                  pstmt.setBytes(6, imageBytes);
                  pstmt.setString(7, empId);
            	  
                  int flag = pstmt.executeUpdate();
                  if (flag > 0) {
                      JOptionPane.showMessageDialog(this, "Teacher Details Updated Successfully. ");
                     // return true;
                      // setVisible(false);
                  } else {
                      JOptionPane.showMessageDialog(this, "Failed to Update Teacher Details!! ");
                    //  return false;
                  }
                  
              //  setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        } else {
            setVisible(false);
        }
    }
    
  //  public static void main(String[] args) {    new UpdateTeacher();    }
}
