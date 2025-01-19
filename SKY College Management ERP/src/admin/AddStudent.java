package admin;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Caret;

import java.awt.*;
import java.util.*;
import java.util.regex.Pattern;

import com.toedter.calendar.JDateChooser;

import collegeManagement.Checks;
import collegeManagement.Credential;
import collegeManagement.Generator;
import collegeManagement.JdbcConnection;
import collegeManagement.Validate;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddStudent extends JFrame implements ActionListener{
    
	private Generator generator = new Generator();
	PreparedStatement ps;
    JTextField tfname, tffname, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JTextArea tfaddress ;
    JLabel labelrollno ,picLabel ;
    JDateChooser dcdob, dcdoa;
    JComboBox cbsem ,cbqualification, cbcourse, cbbranch ;
    // private JComboBox<String>  cbbranch1;
    JButton next, cancel , upload, get;
    JRadioButton jrb1, jrb2 ;
    FileInputStream fis;
    File selectedFile ;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    long first2 = Math.abs((ran.nextLong() % 90L) + 10L);
    
    public AddStudent() {
        
        setSize(900, 700);
        setLocation(250, 20);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("New Student Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lblpic = new JLabel("Profile Picture");
        lblpic.setBounds(50, 150, 125, 30);
        lblpic.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpic);
        
        JLabel pp = new JLabel("*");
        pp.setBounds(175, 150, 10, 20);
        pp.setForeground(Color.red);
        pp.setFont(new Font("Arial", Font.BOLD, 20));
        add(pp);
        
        upload = new JButton("Upload");
        upload.setBounds(240, 150, 100, 30);
        upload.addActionListener(this);
        upload.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(upload);
        
        picLabel = new JLabel("          No File Chosen  ");
        picLabel.setBounds(50, 12, 150, 135);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(picLabel);

        JLabel lblsem = new JLabel("Semester             :");
        lblsem.setBounds(420, 100, 200, 30);
        lblsem.setFont(new Font("serif", Font.BOLD, 20));
        add(lblsem);
        
        JLabel sm = new JLabel("*");
        sm.setBounds(500, 100, 10, 20);
        sm.setForeground(Color.red);
        sm.setFont(new Font("Arial", Font.BOLD, 20));
        add(sm);
        
        String semester[] = {"Select Semester", "1st Semester", "2nd Semester", "3rd Semester", "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};
        cbsem = new JComboBox(semester);
        cbsem.setBounds(610, 100, 180, 30);
        cbsem.setBackground(Color.WHITE);
        add(cbsem);
        
        JLabel lblname = new JLabel("Name                    :");
        lblname.setBounds(50, 250, 200, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        JLabel nm = new JLabel("*");
        nm.setBounds(100, 250, 10, 20);
        nm.setForeground(Color.red);
        nm.setFont(new Font("Arial", Font.BOLD, 20));
        add(nm);
        
        tfname = new JTextField();
        tfname.setBounds(240, 250, 150, 30);
        add(tfname);
        
        JLabel lblfname = new JLabel("Father's Name    :");
        lblfname.setBounds(420, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        JLabel fnm = new JLabel("*");
        fnm.setBounds(545, 150, 10, 20);
        fnm.setForeground(Color.red);
        fnm.setFont(new Font("Arial", Font.BOLD, 20));
        add(fnm);
        
        tffname = new JTextField();
        tffname.setBounds(610, 150, 180, 30);
        add(tffname);
        
        JLabel lblrollno = new JLabel("Roll Number         :");
        lblrollno.setBounds(50, 200, 200, 30);
        lblrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrollno);
        
        labelrollno = new JLabel(" - - - - - - - ");        // earlier labelrollno = new JLabel("20101"+first2);
        labelrollno.setBounds(240, 200, 200, 30);
        labelrollno.setFont(new Font("serif", Font.BOLD, 20));
        add(labelrollno);
        
        JLabel lbldob = new JLabel("Date of Birth      :");
        lbldob.setBounds(420, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        JLabel db = new JLabel("*");
        db.setBounds(530, 200, 10, 20);
        db.setForeground(Color.red);
        db.setFont(new Font("Arial", Font.BOLD, 20));
        add(db);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(610, 200, 180, 30);
        add(dcdob);
        
        JLabel lblgender = new JLabel("Gender                 :");
        lblgender.setBounds(50, 300, 200, 30);
        lblgender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgender);
        
        JLabel gn = new JLabel("*");
        gn.setBounds(115, 300, 10, 20);
        gn.setForeground(Color.red);
        gn.setFont(new Font("Arial", Font.BOLD, 20));
        add(gn);
        
        jrb1 = new JRadioButton("Male");
        jrb1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jrb1.setBackground(Color.WHITE);
        jrb1.setBounds(240, 300, 75, 30);
        add(jrb1);
        
        jrb2 = new JRadioButton("Female");
        jrb2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jrb2.setBackground(Color.WHITE);
        jrb2.setBounds(315, 300, 75, 30);
        add(jrb2);
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);
        
        JLabel lblphone = new JLabel("Phone                  :");
        lblphone.setBounds(420, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        JLabel ph = new JLabel("*");
        ph.setBounds(470, 250, 10, 20);
        ph.setForeground(Color.red);
        ph.setFont(new Font("Arial", Font.BOLD, 20));
        add(ph);
        
        tfphone = new JTextField();
        tfphone.setBounds(610, 250, 180, 30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email Id                :");
        lblemail.setBounds(50, 350, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        JLabel em = new JLabel("*");
        em.setBounds(120, 350, 10, 20);
        em.setForeground(Color.red);
        em.setFont(new Font("Arial", Font.BOLD, 20));
        add(em);
        
        tfemail = new JTextField();
        tfemail.setBounds(240, 350, 150, 30);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("Aadhar Number   :");
        lblaadhar.setBounds(50, 400, 200, 30);
        lblaadhar.setFont(new Font("serif", Font.BOLD, 20));
        add(lblaadhar);
        
        JLabel ad = new JLabel("*");
        ad.setBounds(185, 400, 10, 20);
        ad.setForeground(Color.red);
        ad.setFont(new Font("Arial", Font.BOLD, 20));
        add(ad);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(240, 400, 150, 30);
        add(tfaadhar);
        
        JLabel lblcourse = new JLabel("Course                 :");
        lblcourse.setBounds(420, 300, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        JLabel cr = new JLabel("*");
        cr.setBounds(480, 300, 10, 20);
        cr.setForeground(Color.red);
        cr.setFont(new Font("Arial", Font.BOLD, 20));
        add(cr);
        
        String course[] = {"Select Course", "B-Tech", "Diploma", "BBA", "BCA", "Bsc", "BA" };
        cbcourse = new JComboBox(course);
        cbcourse.setBounds(610, 300, 180, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);     
       
        JLabel lblbranch = new JLabel("Branch                 :");
        lblbranch.setBounds(420, 350, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);
        
        JLabel br = new JLabel("*");
        br.setBounds(480, 350, 10, 20);
        br.setForeground(Color.red);
        br.setFont(new Font("Arial", Font.BOLD, 20));
        add(br);
        
        cbbranch = new JComboBox(new String[]{"Select Branch"});     //or    // cbbranch = new JComboBox<>(new String[]{"Select Branch"});
        cbbranch.setBounds(610, 350, 180, 30);
        cbbranch.setBackground(Color.WHITE);
        cbbranch.setEnabled(false); // Initially disabled
        add(cbbranch);
        
        String branch1[] = {"Select Branch", "Computer Science", "ECE", "EEE", "Mechanical", "Civil", "IT"};
        String branch2[] = {"Select Branch", "BBA"};
        String branch3[] = {"Select Branch", "Computer Science", "IT"};
        String branch4[] = {"Select Branch", "Computer Science", "IT", "Science" };
        String branch5[] = {"Select Branch", "Economics", "Psychology", "Political Science", "Philosophy", "History", "Literature"};
        
        cbcourse.addActionListener(e -> {
            String selectedCourse = (String) cbcourse.getSelectedItem();
            cbbranch.removeAllItems(); // Clear previous items

            if ("B-Tech".equals(selectedCourse) || "Diploma".equals(selectedCourse)) {
                for (String branch : branch1) cbbranch.addItem(branch);
                cbbranch.setEnabled(true);
            } else if ("BBA".equals(selectedCourse)) {
                for (String branch : branch2) cbbranch.addItem(branch);
                cbbranch.setEnabled(true);
            } else if ("BCA".equals(selectedCourse)) {
                for (String branch : branch3) cbbranch.addItem(branch);
                cbbranch.setEnabled(true);
            } else if ("Bsc".equals(selectedCourse)) {
                for (String branch : branch4) cbbranch.addItem(branch);
                cbbranch.setEnabled(true);
            } else if ("BA".equals(selectedCourse)) {
                for (String branch : branch5) cbbranch.addItem(branch);
                cbbranch.setEnabled(true);
            } else {
                cbbranch.addItem("Select Branch");
                cbbranch.setEnabled(false);
            }
        });

        JLabel lbladdress = new JLabel("Address                :");
        lbladdress.setBounds(50, 450, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        JLabel adr = new JLabel("*");
        adr.setBounds(118, 450, 10, 20);
        adr.setForeground(Color.red);
        adr.setFont(new Font("Arial", Font.BOLD, 20));
        add(adr);
        
        tfaddress = new JTextArea();
        tfaddress.setBounds(240, 450, 150, 80);
        tfaddress.setLineWrap(true);  // setLineWrap(true) ensures text doesn't go beyond the visible area horizontally, making horizontal scrolling unnecessary.
        tfaddress.setWrapStyleWord(true);
        add(tfaddress);
        
        JScrollPane scrollPane = new JScrollPane(tfaddress);   // JScrollPane: This component provides a scrollable view of another component (in this case, tfaddress).
        scrollPane.setBounds(240, 450, 150, 80); // Adjust size and position
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        
        JLabel lbldoa = new JLabel("Date of Admission:");
        lbldoa.setBounds(50, 540, 200, 30);
        lbldoa.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoa);
        
        JLabel da = new JLabel("*");
        da.setBounds(205, 540, 10, 20);
        da.setForeground(Color.red);
        da.setFont(new Font("Arial", Font.BOLD, 20));
        add(da);
        
        dcdoa = new JDateChooser();
        dcdoa.setBounds(240, 540, 150, 30);
        add(dcdoa);
        
        JLabel lblqualification = new JLabel("Qualification       : ");
        lblqualification.setBounds(420, 400, 200, 30);
        lblqualification.setFont(new Font("serif", Font.BOLD, 20));
        add(lblqualification);
        
        JLabel ql = new JLabel("*");
        ql.setBounds(530, 400, 10, 20);
        ql.setForeground(Color.red);
        ql.setFont(new Font("Arial", Font.BOLD, 20));
        add(ql);
        
        String qualifctn[] = {"Select Qualification", "10th or Matriculation Equivalent", "12th or Intermediate Equivalent"};
        cbqualification = new JComboBox(qualifctn);
        cbqualification.setBounds(610, 400, 180, 30);
        cbqualification.setBackground(Color.WHITE);
        add(cbqualification);  
        
        JLabel lblx = new JLabel("Class X (%)*       :");
        lblx.setBounds(420, 450, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);
        
        tfx = new JTextField();
        tfx.setBounds(610, 450, 180, 30);
        add(tfx);
        
        JLabel lblxii = new JLabel("Class XII (%)*     :");
        lblxii.setBounds(420, 500, 200, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);
        lblxii.setVisible(false);
       	lblxii.setEnabled(false);
        
        tfxii = new JTextField();
        tfxii.setBounds(610, 500, 180, 30);
        add(tfxii);
        tfxii.setVisible(false);
       	tfxii.setEnabled(false);
        
        cbqualification.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String selectedQualification = (String) cbqualification.getSelectedItem();
                
               if ("12th or Intermediate Equivalent".equals(selectedQualification)) {
            	   lblxii.setVisible(true);
               	   lblxii.setEnabled(true);
               	   tfxii.setVisible(true);
               	   tfxii.setEnabled(true);
                } 
                else {
                	lblxii.setVisible(false);
                   	lblxii.setEnabled(false);
                   	tfxii.setVisible(false);
                   	tfxii.setEnabled(false);
                   	tfxii.setText("");
                }
            }
        });
        
        next = new JButton("Next");
        next.setBounds(250, 590, 120, 30);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        next.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(next);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 590, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        get = new JButton("Get");
        get.setBounds(330, 200, 60, 30);
    //    get.setBackground(Color.BLACK);
      //  get.setForeground(Color.WHITE);
        get.addActionListener(this);
        get.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(get);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) 
    {
    	if (ae.getSource() == upload )
    	{
    		 JFileChooser fileChooser = new JFileChooser();
             fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
             int result = fileChooser.showOpenDialog(this);
             if (result == JFileChooser.APPROVE_OPTION) {
                 selectedFile = fileChooser.getSelectedFile();
                 ImageIcon icon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH));
                 picLabel.setIcon(icon);
             }
             
    	}
    	
    	else if (ae.getSource() == next) {
            String name = tfname.getText().trim();     // trim function to remove leading and trailinng space
            String fname = tffname.getText().trim();
            String rollno = labelrollno.getText().trim();
           // int rollno = Integer.parseInt(roll);
            String address = tfaddress.getText().trim();
            String phone = tfphone.getText().trim();
            String email = tfemail.getText().trim();
            String x = tfx.getText().trim(); 
            String aadhar = tfaadhar.getText().trim();
            String course = (String) cbcourse.getSelectedItem();
            String branch = (String) cbbranch.getSelectedItem();
            String qualification = (String) cbqualification.getSelectedItem();
            String semester = (String) cbsem.getSelectedItem();
            
            String xii = null;
            if (tfxii.isEnabled()) {
	            xii = tfxii.getText().trim();
	        }
            
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dob = "";
//            if (dcdob.getDate() != null) {
//                dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
//            } else {
//                JOptionPane.showMessageDialog(null, "Please select a date of birth.");
//                return; // Exit the method if no date is selected
//            }
            try {
                dob = dateFormat.format(dcdob.getDate());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please Select a Valid Date of Birth.", "Invalid DOB", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String doa = "";
            String yoa = "";
            try {
            	   doa = dateFormat.format(dcdoa.getDate());  // Get the full doa in "yyyy-MM-dd" format
            	// Extract only the year from the date
                   SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                   yoa = yearFormat.format(dcdoa.getDate());
                } catch (Exception e) 
                {
                   JOptionPane.showMessageDialog(this, "Please Select a Valid Date of Admission.", "Invalid DOA", JOptionPane.ERROR_MESSAGE);
                   return;
                }
            
            String gender = "";
            if (jrb1.isSelected()) {
            	gender = "Male";
            }
            else if (jrb2.isSelected()){
            	gender = "Female";
            }
            
            boolean success1 = validateCheck( semester , dob );
            boolean success = Checks.validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, qualification, selectedFile, course, branch, tfxii , rollno, doa );
            
         // add student only if validation gets successful
            if (success && success1 )
            {
              //  JOptionPane.showMessageDialog(this, "Roll : "+rollno+" , Name : "+name+" , Fname : "+fname+" , Phone : "+phone+" , Email : "+email+" , Aadhaar : "+aadhar+" , DOB : "+dob+" , Address : "+address+" , Pic : "+selectedFile+" , Course : "+course+" , Branch : "+branch+" , Qualification : "+qualification+" , ClassX : "+x+" , ClassXII : "+xii+" , Gender : "+gender+" , DOA : "+doa+" ,  > " );

            	 boolean added = addDetails(name,fname,phone,aadhar,email,x,xii,gender,address,dob,course,branch,qualification,selectedFile, semester, rollno, doa );
              //  JOptionPane.showMessageDialog(this, "Student added successfully! bool  " , "Success", JOptionPane.INFORMATION_MESSAGE);
                
                // Update roll number counter if addition is successful
                if (added) {
                	String type = "course";
                    String role = "Student";
                	generator.updateCounter(yoa, course , branch, type);
                   // JOptionPane.showMessageDialog(this, "Counter Updated ! Roll No: " , "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Credential(role, rollno);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Add Student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else 
            {
              //  JOptionPane.showMessageDialog(this, "Please Fill all details first. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        } 
    	else if (ae.getSource() == get)
    	{
    		 String course = (String) cbcourse.getSelectedItem();
             String branch = (String) cbbranch.getSelectedItem();
    		 String yoa = "";
             try {          	
             	     // Extract only the year from the date
                     SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                     yoa = yearFormat.format(dcdoa.getDate());
                 } catch (Exception e) 
                 {
                    JOptionPane.showMessageDialog(this, "Please Select a Valid Year of Admission.", "Invalid DOA", JOptionPane.ERROR_MESSAGE);
                    return;
                 }
    		
    	    // Check if the selected course or branch is invalid
    	    if (cbcourse.getSelectedItem().equals("Select Course") || cbbranch.getSelectedItem().equals("Select Branch") || dcdoa.getDate() == null) 
    	    {
    	        JOptionPane.showMessageDialog(this, "Please Enter DOA & Select Course and Branch First.", "Error", JOptionPane.ERROR_MESSAGE);
    	    } else 
    	    {
    	        // JOptionPane.showMessageDialog(this, "Get Button Added.", "Success", JOptionPane.INFORMATION_MESSAGE);
    	        String rollno = labelrollno.getText();
            	if (rollno.equals(" - - - - - - - ")) 
            	{
            		try {
            			String type = "course";
                        String rollNo = generator.generateID(yoa, course , branch, type);
                        labelrollno.setText(rollNo);
                        JOptionPane.showMessageDialog(null, "RollNo Generated Successfully! Roll No: " + rollNo);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Failed to Generate RollNo. Error: " + e.getMessage());
                    }
            		
            	//	labelrollno.setText(rollNo);    // labelrollno.setText("20103"+first2); 
                } 
    	    }
    	}

    	else
        {
            setVisible(false);
        }
    	
    }
    
    private Validate valid = new Validate();
    private boolean validateCheck(String semester, String dob ) 
    {
    	if ( "Select Semester".equals(semester) )
        {
            JOptionPane.showMessageDialog(null, "Please Select Semester !");
            return false ;
        }
    	
    	
    	try {
	        // Validate age is between 18 and 40
	        int age = valid.calculatedAge(dob);
	        if (age < 15 || age > 30)
	            {
	            JOptionPane.showMessageDialog(null, "Age Must Be Between 15 and 30 Years.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false ;
	            }
    	    }
	        catch ( ParseException e) 
	        {
		        JOptionPane.showMessageDialog(null, "Select Valid Date of Birth.", "Error", JOptionPane.ERROR_MESSAGE);
		        return false ;
		    }
    	
    	return true;
    }
    
    
    private boolean addDetails( String name,String fname,String phone,String aadhar,String email,String x,String xii,String gender,String address,String dob,String course,String branch,String qualification,File selectedFile, String semester, String rollno , String doa) 
    {
        try  {        // try (  FileInputStream fis = new FileInputStream(selectedFile)) {    - this method auto closes fis to prevent resource leakage
        	
        	fis = new FileInputStream(selectedFile);
        	// JOptionPane.showMessageDialog(this, "Fis : "+fis);
        	
            JdbcConnection jdbc = new JdbcConnection();
           // JOptionPane.showMessageDialog(this, "JDBC : "+jdbc);
            
            String query = "INSERT INTO student (rollno, name, fname, phone, email, aadhar, dob, address, pic, branch, course, qualification, classx, classxii, semester, gender, doa ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
            ps = jdbc.prepareStatement(query);
            ps.setString(1, rollno);
            ps.setString(2, name);
            ps.setString(3, fname);
            ps.setString(4, phone);
            ps.setString(5, email);
            ps.setString(6, aadhar);
            ps.setString(7, dob);
            ps.setString(8, address);
            ps.setBinaryStream(9, fis, (int) selectedFile.length());
            ps.setString(10, branch);
            ps.setString(11, course);
            ps.setString(12, qualification);
            ps.setDouble(13, Double.parseDouble(x));
            ps.setString(14, xii);
            ps.setString(15, semester);
            ps.setString(16, gender);
            ps.setString(17, doa);

           // JOptionPane.showMessageDialog(this, "Querry : "+query);
          //  JOptionPane.showMessageDialog(this, "Roll : "+rollno+" , Name : "+name+" , Fname : "+fname+" , Phone : "+phone+" , Email : "+email+" , Aadhaar : "+aadhar+" , DOB : "+dob+" , Address : "+address+" , Pic : "+selectedFile+" , Course : "+course+" , Branch : "+branch+" , Qualification : "+qualification+" , ClassX : "+x+" , ClassXII : "+xii+" , Gender : "+gender+" , DOA : "+doa+" ,  > " );

            
            int flag = ps.executeUpdate();
            if (flag > 0) {
                JOptionPane.showMessageDialog(this, "Student Details Added Successfully! ");
               // return true;
                // setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to Add Student Details.");
              //  return false;
            }
            
            return flag >0;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
        
        return false;
    }
    
    
    public static void main(String[] args) {   new AddStudent();  }
}    
    










    
//    private void validateCheck(String name,String fname,String phone,String aadhar,String email,String x,String xii,String gender,String address,String dob,String course,String branch,String qualification,File selectedFile, String semester ) 
//    {
//        if (name.isBlank() || gender.isEmpty() || aadhar.isBlank() || address.isBlank() || fname.isBlank() || dob.isBlank() || phone.isBlank() || "Select Semester".equals(semester) || "Select Course".equals(course) || "Select Branch".equals(branch) || "Select Qualification".equals(qualification) || x.isBlank() || selectedFile == null ) {
//            JOptionPane.showMessageDialog(this, "All * Marked Fields are Mandatory!, Including Profile Picture.");
//            return;
//        }
//                           
//         if (!Pattern.matches("[a-z A-Z]{2,}", name)) {
//              JOptionPane.showMessageDialog(this, "Please Enter Valid Name. It Must be Alphabet Only !", "Error", JOptionPane.ERROR_MESSAGE);
//               return;
//          }
//         
//         if (!Pattern.matches("[a-z A-Z]{2,}", fname)) {
//             JOptionPane.showMessageDialog(this, "Please Enter Valid Father's Name. It Must be Alphabet Only !", "Error", JOptionPane.ERROR_MESSAGE);
//              return;
//         }
//
//        if (!Pattern.matches("\\d{10}", phone)) {
//            JOptionPane.showMessageDialog(this, "Phonse Number Must be 10 Digits and Only Numeric !", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        
//     // Validate phone number for continuous digits or repeated digits
//        if (containsContinuousDigits(phone,aadhar) || containsRepeatedDigits(phone) || containsRepeatedAadhar(aadhar) ) {
//            JOptionPane.showMessageDialog(this, "Phone Number And Aadhar Number Cannot Contain Continuous Digits or More Than 3 Repeated Digits!", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if (!Pattern.matches("\\d{12}", aadhar)) {
//            JOptionPane.showMessageDialog(this, "Aadhar Number Must be 12 Digits Numeric Only !", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if (!Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", email)) {
//            JOptionPane.showMessageDialog(this, "Please Enter a Valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        
//        try {
//        	
//        	// Validate age is between 18 and 40
//            int age = calculateAge(dob);
//            if (age < 18 || age > 40) {
//                JOptionPane.showMessageDialog(this, "Age Must Be Between 18 and 40 Years.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//        	
//            double percentageX = Double.parseDouble(x);
//            if (percentageX < 35 || percentageX > 100 ) 
//        	{
//                JOptionPane.showMessageDialog(this, "Class X Percentages Must be Between 35 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            
//            if (tfxii.isEnabled()) 
//            { 
//            	double percentageXII = Double.parseDouble(xii); 
//            	 if ( percentageXII < 35 || percentageXII > 100) 
//            	 {
//                     JOptionPane.showMessageDialog(this, "Class XII Percentages Must be Between 35 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
//                     return;
//                 }
//            }
//                
//        } catch (NumberFormatException | ParseException e) {
//            JOptionPane.showMessageDialog(this, "Class X and XII Percentages Must be Numeric.", "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//    }
    
     
    
 // Helper method to calculate age
//    private int calculateAge(String dob) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        Date birthDate = dateFormat.parse(dob);
//        Calendar birthCalendar = Calendar.getInstance();
//        birthCalendar.setTime(birthDate);
//
//        Calendar today = Calendar.getInstance();
//        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
//
//        if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
//            age--; // Adjust if birthday hasn't occurred yet this year
//        }
//
//        return age;
//    }

    // Helper method to check for continuous digits in the phone number
//    private boolean containsContinuousDigits(String phone, String aadhar) {
//        for (int i = 0; i < phone.length() - 3; i++) {
//            if ( ( phone.charAt(i) + 1 == phone.charAt(i + 1) &&
//                   phone.charAt(i + 1) + 1 == phone.charAt(i + 2) &&
//                   phone.charAt(i + 2) + 1 == phone.charAt(i + 3) ) || 
//            		( aadhar.charAt(i) + 1 == aadhar.charAt(i + 1) &&
//            		  aadhar.charAt(i + 1) + 1 == aadhar.charAt(i + 2) &&
//            		  aadhar.charAt(i + 2) + 1 == aadhar.charAt(i + 3) ) ) 
//                {
//                return true; // Found 4 continuous digits
//                }
//        }
//        return false;
//    }

    // Helper method to check for repeated digits in the phone number
//    private boolean containsRepeatedDigits(String phone) {
//        for (int i = 0; i < phone.length() - 3; i++) {
//            if (phone.charAt(i) == phone.charAt(i + 1) &&
//                phone.charAt(i + 1) == phone.charAt(i + 2) &&
//                phone.charAt(i + 2) == phone.charAt(i + 3)) {
//                return true; // Found 4 repeated digits
//            }
//        }
//        return false;
//    }
//    
//    private boolean containsRepeatedAadhar(String aadhar) {
//        for (int i = 0; i < aadhar.length() - 3; i++) {
//            if (aadhar.charAt(i) == aadhar.charAt(i + 1) &&
//            	aadhar.charAt(i + 1) == aadhar.charAt(i + 2) &&
//            	aadhar.charAt(i + 2) == aadhar.charAt(i + 3)) {
//                return true; // Found 4 repeated digits
//            }
//        }
//        return false;
//    }
    

/*

File Upload:

  Validate the file format and size before allowing submission.
  Ensure selectedFile is not null and implement exception handling for FileInputStream.

UI Enhancements:

   Provide placeholder text or tooltips for fields like phone, email, etc., to guide the user.
   Ensure better layout alignment for a clean UI appearance.
   
   help modularizing this code or enhancing a specific functionality?

*/


//upload.addActionListener(e -> {
//JFileChooser fileChooser = new JFileChooser();
//fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png"));
//int result = fileChooser.showOpenDialog(this);
//if (result == JFileChooser.APPROVE_OPTION) {
//  selectedFile = fileChooser.getSelectedFile();
//  ImageIcon icon = new ImageIcon(new ImageIcon(selectedFile.getAbsolutePath()).getImage().getScaledInstance(140, 130, Image.SCALE_SMOOTH));
//  picLabel.setIcon(icon);
//}
//});



