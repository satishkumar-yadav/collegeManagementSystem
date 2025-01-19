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

public class UpdateProfileTeacher extends JFrame implements ActionListener{
    
  //  SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // Adjust the format as needed
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    JTextField tfaddress, tfphone, tfemail;
    JLabel labelempid, picLabel, labelname, labelfname, labelx, labelxii, labelaadhar, labeldepartment, labelgender, labelqualification,
    labeldob, labeldoj , labelsubject, labelgrad, labelrole, labelpg, labelphd, labelisphd, labeldesignation ;
    JButton update, cancel, change;
    FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
    String empid = "";
    
    UpdateProfileTeacher(String empId) {
        empid = empId;
        
        setSize(900, 750);
        setLocation(310, 15);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("Update Teacher Details");
        heading.setBounds(50, 10, 500, 50);
        heading.setFont(new Font("Tahoma", Font.ITALIC, 35));
        add(heading);
        
        JLabel lblpic = new JLabel("Profile Picture");
        lblpic.setBounds(480, 80, 125, 30);
        lblpic.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpic);
        
        change = new JButton("Change");
        change.setBounds(480, 120, 100, 30);
        change.addActionListener(this);
        change.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(change);
        
        picLabel = new JLabel("  No Picture Available  ");
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
        
        update = new JButton("Update");
        update.setBounds(250, 660, 120, 30);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        update.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(update);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 660, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
         	  // new Student();
         	  setVisible(false);
            }
        });
        
        setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent ae) {
    	
    	if (ae.getSource() == change )
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
       
            String empid = labelempid.getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
           
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to update the Your details?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                // Proceed with the update
            
            try {
            	 JdbcConnection con = new JdbcConnection();
            	 //	fis = new FileInputStream(selectedFile);
            	
              //  String query = "update student set address='"+address+"', phone='"+phone+"', email='"+email+"', branch='"+branch+"' , semester= '"+semester+"' where rollno='"+rollno+"'";  
                String query = "UPDATE teacher SET address = ?, phone = ?, email = ?, pic = ? WHERE empid = ?";
                
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
                pstmt.setString(5, empid);
                pstmt.setBytes(4, imageBytes);
                //     ps.setBinaryStream(9, fis, (int) selectedFile.length());
                
                int flag = pstmt.executeUpdate();
                if (flag > 0) {
                    JOptionPane.showMessageDialog(this, "Teacher Details Updated Successfully!");
                   // return true;
                    // setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Teacher Details. ");
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
                tfaddress.setText(rsup.getString("address"));
                tfphone.setText(rsup.getString("phone"));
                tfemail.setText(rsup.getString("email"));
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

    
    
   // public static void main(String[] args) {     new UpdateProfileTeacher(); }
}


