package temp;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.Caret;

import java.awt.*;
import java.util.*;
import java.util.regex.Pattern;

import com.toedter.calendar.JDateChooser;

import collegeManagement.Checks;
import collegeManagement.Generator;
import collegeManagement.JdbcConnection;
import collegeManagement.Login;
import collegeManagement.Validate;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AddNewTeacher extends JFrame implements ActionListener{
    
	private Generator generator = new Generator();
	PreparedStatement ps;
    JTextField tfname, tffname, tfphone, tfemail, tfx, tfxii, tfgrad, tfpg, tfphd, tfaadhar;
    JTextArea tfaddress ;
    JLabel labelempId ,picLabel ;
    JDateChooser dcdob, dcdoj;
    JComboBox cbqualification, cbdept, cbsub , cbrole ;
    // private JComboBox<String>  cbbranch1;
    JButton submit, cancel , upload, get;
    JRadioButton jrb1, jrb2, jrbyes, jrbno ;
    FileInputStream fis;
    File selectedFile ;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    long first2 = Math.abs((ran.nextLong() % 90L) + 10L);
    
    public AddNewTeacher() {
        
        setSize(900, 700);
        setLocation(250, 20);
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(350, 15, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lblpic = new JLabel("Profile Picture* ");
        lblpic.setBounds(50, 150, 200, 30);
        lblpic.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpic);
        
        upload = new JButton("Upload");
        upload.setBounds(240, 150, 100, 30);
//        upload.setBackground(Color.BLACK);
//        upload.setForeground(Color.WHITE);
        upload.addActionListener(this);
        upload.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(upload);
        
        picLabel = new JLabel("          No File Chosen  ");
        picLabel.setBounds(50, 12, 150, 135);
        picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(picLabel);
                
        JLabel lblname = new JLabel("Name*                   :");
        lblname.setBounds(50, 250, 200, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(240, 250, 150, 30);
        add(tfname);
        
        JLabel lblphd = new JLabel("Phd* :");
        lblphd.setBounds(230, 100, 60, 30);
        lblphd.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphd);
        
        jrbyes = new JRadioButton("Yes");
        jrbyes.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jrbyes.setBackground(Color.WHITE);
        jrbyes.setBounds(290, 100, 60, 30);
        add(jrbyes);
        
        jrbno = new JRadioButton("No");
        jrbno.setFont(new Font("Tahoma", Font.PLAIN, 15));
        jrbno.setBackground(Color.WHITE);
        jrbno.setBounds(350, 100, 50, 30);
        add(jrbno);
        ButtonGroup bg2 = new ButtonGroup();
        bg2.add(jrbyes);
        bg2.add(jrbno);
        
        jrbyes.addActionListener(ye -> {
        	String yes = (String) jrbyes.getActionCommand();
        	if (yes.equals("Yes")) {
                tfname.setText("Dr."+" ");
                cbqualification.setSelectedIndex(5);
                //cbqualification.setSelectedItem(jrbyes);
            } 
        });
        
        jrbno.addActionListener(n -> {
        	String no = (String) jrbno.getActionCommand();
        	if (no.equals("No")) {
                tfname.setText("");
                cbqualification.setSelectedIndex(0);
                //cbqualification.setSelectedItem(jrbyes);
            } 
        });
        
        JLabel lbldoa = new JLabel("Date of Joining*   :");
        lbldoa.setBounds(420, 65, 200, 30);
        lbldoa.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldoa);
        
        dcdoj = new JDateChooser();
        dcdoj.setBounds(610, 65, 180, 30);
        add(dcdoj);
        
        JLabel lblfname = new JLabel("Father's Name*   :");
        lblfname.setBounds(420, 100, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        tffname = new JTextField();
        tffname.setBounds(610, 100, 180, 30);
        add(tffname);
        
        JLabel lblempId = new JLabel("Temp Employee ID:");
        lblempId.setBounds(50, 200, 200, 30);
        lblempId.setFont(new Font("serif", Font.BOLD, 20));
        add(lblempId);
        
        labelempId = new JLabel(" - - - - - - - ");
        labelempId.setBounds(240, 200, 200, 30);
        labelempId.setFont(new Font("serif", Font.BOLD, 20));
        add(labelempId);
        
        JLabel lbldob = new JLabel("Date of Birth*     :");
        lbldob.setBounds(420, 150, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(610, 150, 180, 30);
        add(dcdob);
        
        JLabel lblgender = new JLabel("Gender*                :");
        lblgender.setBounds(50, 300, 200, 30);
        lblgender.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgender);
        
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
        
        JLabel lblphone = new JLabel("Phone*                :");
        lblphone.setBounds(420, 200, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(610, 200, 180, 30);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email Id*               :");
        lblemail.setBounds(50, 350, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(240, 350, 150, 30);
        add(tfemail);
        
        JLabel lblaadhar = new JLabel("Aadhar Number*  :");
        lblaadhar.setBounds(50, 400, 200, 30);
        lblaadhar.setFont(new Font("serif", Font.BOLD, 20));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(240, 400, 150, 30);
        add(tfaadhar);
        
        JLabel lbldept = new JLabel("Department*      :");
        lbldept.setBounds(420, 250, 200, 30);
        lbldept.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldept);
        
        String dept[] = {"Select Department", "Computer Science", "ECE", "EEE", "Mechanical", "Civil", "Account", "Examination", "Library", "Mathematics", "Physics", "Literature" };
        cbdept = new JComboBox(dept);
        cbdept.setBounds(610, 250, 180, 30);
        cbdept.setBackground(Color.WHITE);
        add(cbdept);     
       
        JLabel lblrole = new JLabel("Role*                  :");
        lblrole.setBounds(420, 300, 200, 30);
        lblrole.setFont(new Font("serif", Font.BOLD, 20));
        add(lblrole);
        
        cbrole = new JComboBox(new String[]{"Select Role"});     
        cbrole.setBounds(610, 300, 180, 30);
        cbrole.setBackground(Color.WHITE);
        cbrole.setEnabled(false); // Initially disabled
        add(cbrole);
        
        String role1[] = {"Select Role", "HOD", "Sr. Professor", "Asst. Professor", "Jr. Professor", "Lab Assistant", "Assistant", "Other"};
        String role2[] = {"Select Role", "HOA", "Sr. Accountant", "Asst. Accountant", "Jr. Accountant", "Assistant", "Other" };
        String role3[] = {"Select Role", "HOD", "Sr. Librarian", "Asst. Librarian", "Jr. Librarian", "Assistant", "Other" };
        String role4[] = {"Select Role", "HOE", "Sr. Examiner", "Asst. Examiner", "Jr. Examiner", "Assistant", "Other" };
        
        cbdept.addActionListener(e -> {
            String selectedDepartment = (String) cbdept.getSelectedItem();
            cbrole.removeAllItems(); // Clear previous items

            if ("Computer Science".equals(selectedDepartment) || "ECE".equals(selectedDepartment) || "EEE".equals(selectedDepartment) || "Mechanical".equals(selectedDepartment) || "Civil".equals(selectedDepartment) || "Mathematics".equals(selectedDepartment) || "Physics".equals(selectedDepartment) || "Literature".equals(selectedDepartment)  ) 
            {
                for (String branch : role1) cbrole.addItem(branch);
                cbrole.setEnabled(true);
            } else if ("Account".equals(selectedDepartment)) {
                for (String branch : role2) cbrole.addItem(branch);
                cbrole.setEnabled(true);
            } else if ("Library".equals(selectedDepartment)) {
                for (String branch : role3) cbrole.addItem(branch);
                cbrole.setEnabled(true);
            } else if ("Examination".equals(selectedDepartment)) {
                for (String branch : role4) cbrole.addItem(branch);
                cbrole.setEnabled(true);
            } else {
            	cbrole.addItem("Select Role");
            	cbrole.setEnabled(false);
            }
        });

        
               
        JLabel lbladdress = new JLabel("Address*               :");
        lbladdress.setBounds(50, 450, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        tfaddress = new JTextArea();
        tfaddress.setBounds(240, 450, 150, 80);
       // tfaddress.setBorder(Border);
        tfaddress.setLineWrap(true);
        tfaddress.setWrapStyleWord(true);
        // add(new JScrollPane(tfaddress));
        add(tfaddress);
        
        JScrollPane scrollPane = new JScrollPane(tfaddress);   // JScrollPane: This component provides a scrollable view of another component (in this case, tfaddress).
        scrollPane.setBounds(240, 450, 150, 80); // Adjust size and position
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane);
        
        JLabel lblqualification = new JLabel("Qualification*    : ");
        lblqualification.setBounds(420, 350, 200, 30);
        lblqualification.setFont(new Font("serif", Font.BOLD, 20));
        add(lblqualification);
        
        String qualifctn[] = {"Select Qualification", "10th or Matriculation Equivalent", "12th or Intermediate Equivalent", "Graduates", "Post-Graduates","Phd"};
        cbqualification = new JComboBox(qualifctn);
        cbqualification.setBounds(610, 350, 180, 30);
        cbqualification.setBackground(Color.WHITE);
        add(cbqualification);  
        
        JLabel lblx = new JLabel("Class X (%)*      :");
        lblx.setBounds(420, 400, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);
        
        tfx = new JTextField();
        tfx.setBounds(610, 400, 180, 30);
        add(tfx);
        
        JLabel lblxii = new JLabel("Class XII (%)*   :");
        lblxii.setBounds(420, 450, 200, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);
        lblxii.setVisible(false);
       	lblxii.setEnabled(false);
        
        tfxii = new JTextField();
        tfxii.setBounds(610, 450, 180, 30);
        add(tfxii);
        tfxii.setVisible(false);
       	tfxii.setEnabled(false);
        
        JLabel lblgrad = new JLabel("Grad (%)          :");
        lblgrad.setBounds(420, 500, 200, 30);
        lblgrad.setFont(new Font("serif", Font.BOLD, 20));
        add(lblgrad);
        lblgrad.setVisible(false);
       	lblgrad.setEnabled(false);
        
        tfgrad = new JTextField();
        tfgrad.setBounds(610, 500, 180, 30);
        add(tfgrad);
        tfgrad.setVisible(false);
       	tfgrad.setEnabled(false);
        
        JLabel lblpg = new JLabel("PG (%)             :");
        lblpg.setBounds(420, 550, 200, 30);
        lblpg.setFont(new Font("serif", Font.BOLD, 20));
        add(lblpg);
        lblpg.setVisible(false);
       	lblpg.setEnabled(false);
        
        tfpg = new JTextField();
        tfpg.setBounds(610, 550, 180, 30);
        add(tfpg);
        tfpg.setVisible(false);
       	tfpg.setEnabled(false);
        
        JLabel lblphdSub = new JLabel("PHD(Paper)         :");
        lblphdSub.setBounds(50, 550, 200, 30);
        lblphdSub.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphdSub);
        lblphdSub.setVisible(false);
        lblphdSub.setEnabled(false);
        
        tfphd = new JTextField();
        tfphd.setBounds(240, 550, 150, 30);
        add(tfphd);
        tfphd.setVisible(false);
       	tfphd.setEnabled(false);
        
        cbqualification.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String selectedQualification = (String) cbqualification.getSelectedItem();
                
               if ("12th or Intermediate Equivalent".equals(selectedQualification)) {
            	   lblxii.setVisible(true);
               	   lblxii.setEnabled(true);
               	   tfxii.setVisible(true);
               	   tfxii.setEnabled(true);
               	   
               	   lblgrad.setVisible(false);
   	               lblgrad.setEnabled(false);
   	               tfgrad.setVisible(false);
   	               tfgrad.setEnabled(false);
   	   
   	               lblpg.setVisible(false);
 	               lblpg.setEnabled(false);
 	               tfpg.setVisible(false);
 	               tfpg.setEnabled(false);
 	           
 	               lblphdSub.setVisible(false);
 	               lblphdSub.setEnabled(false);
            	   tfphd.setVisible(false);
            	   tfphd.setEnabled(false);
                } 
               else if ("Graduates".equals(selectedQualification)) {
            	   lblxii.setVisible(true);
               	   lblxii.setEnabled(true);
               	   tfxii.setVisible(true);
               	   tfxii.setEnabled(true);
               	   
               	   lblgrad.setVisible(true);
            	   lblgrad.setEnabled(true);
            	   tfgrad.setVisible(true);
            	   tfgrad.setEnabled(true);
            	   
            	   lblpg.setVisible(false);
    	           lblpg.setEnabled(false);
    	           tfpg.setVisible(false);
    	           tfpg.setEnabled(false);
    	           
    	           lblphdSub.setVisible(false);
    	           lblphdSub.setEnabled(false);
               	   tfphd.setVisible(false);
               	   tfphd.setEnabled(false);
                } 
               else if ("Post-Graduates".equals(selectedQualification)) {
            	   lblxii.setVisible(true);
               	   lblxii.setEnabled(true);
               	   tfxii.setVisible(true);
               	   tfxii.setEnabled(true);
               	   
               	   lblgrad.setVisible(true);
         	       lblgrad.setEnabled(true);
         	       tfgrad.setVisible(true);
         	       tfgrad.setEnabled(true);
         	   
         	       lblpg.setVisible(true);
       	           lblpg.setEnabled(true);
       	           tfpg.setVisible(true);
       	           tfpg.setEnabled(true);
       	           
       	           lblphdSub.setVisible(false);
 	               lblphdSub.setEnabled(false);
            	   tfphd.setVisible(false);
            	   tfphd.setEnabled(false);
       	   
                } 
               else if ("Phd".equals(selectedQualification)) {
            	   lblxii.setVisible(true);
               	   lblxii.setEnabled(true);
               	   tfxii.setVisible(true);
               	   tfxii.setEnabled(true);
               	   
               	   lblgrad.setVisible(true);
      	           lblgrad.setEnabled(true);
      	           tfgrad.setVisible(true);
      	           tfgrad.setEnabled(true);
      	   
      	           lblpg.setVisible(true);
    	           lblpg.setEnabled(true);
    	           tfpg.setVisible(true);
    	           tfpg.setEnabled(true);
    	           
    	           lblphdSub.setVisible(true);
    	           lblphdSub.setEnabled(true);
               	   tfphd.setVisible(true);
               	   tfphd.setEnabled(true);
                } 
                else {
                	lblxii.setVisible(false);
                   	lblxii.setEnabled(false);
                   	tfxii.setVisible(false);
                   	tfxii.setEnabled(false);
                   	tfxii.setText("");
                   	
                   	lblgrad.setVisible(false);
       	            lblgrad.setEnabled(false);
       	            tfgrad.setVisible(false);
       	            tfgrad.setEnabled(false);
       	   
       	            lblpg.setVisible(false);
     	            lblpg.setEnabled(false);
     	            tfpg.setVisible(false);
     	            tfpg.setEnabled(false);
     	           
     	            lblphdSub.setVisible(false);
     	            lblphdSub.setEnabled(false);
                	tfphd.setVisible(false);
                	tfphd.setEnabled(false);
                }
            }
        });
        
        submit = new JButton("Submit");
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
        
        get = new JButton("Get");
        get.setBounds(330, 200, 60, 30);
    //    get.setBackground(Color.BLACK);
      //  get.setForeground(Color.WHITE);
        get.addActionListener(this);
        get.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(get);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                redirectToLogin();
            }
        });
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
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
    	
    	else if (ae.getSource() == submit) {
            String name = tfname.getText().trim();    
            String fname = tffname.getText().trim();
            String empid = labelempId.getText();
          //  int empid = Integer.parseInt(emp);
            String address = tfaddress.getText().trim();
            String phone = tfphone.getText().trim();
            String email = tfemail.getText().trim();
            String x = tfx.getText().trim();
            String xii = tfxii.getText().trim();
            String aadhar = tfaadhar.getText().trim();
            String grad = tfgrad.getText().trim();
            String pg = tfpg.getText().trim();
            String phd = tfphd.getText().trim();
            String department = (String) cbdept.getSelectedItem();
            String qualification = (String) cbqualification.getSelectedItem();
            String designation = (String) cbrole.getSelectedItem();
            
            String role = (String) cbrole.getSelectedItem();
            if ( role.equalsIgnoreCase("hod") || role.equalsIgnoreCase("hoa") ||role.equalsIgnoreCase("hoe")  )
            {
            	role = "HOD";
            }
            else if ( role.startsWith("Sr.") )
            {
            	role = "Sr.";
            }
            else if ( role.startsWith("Asst.")  )
            {
            	role = "Asst.";
            }
            else if ( role.startsWith("Jr.") )
            {
            	role = "Jr.";
            }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dob = "";
            try {
                dob = dateFormat.format(dcdob.getDate());
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Please select a valid date of birth.", "Invalid DOB", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            String doj = "";
            String yoj = "";
            try {
            	doj = dateFormat.format(dcdoj.getDate());
            	SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                yoj = yearFormat.format(dcdoj.getDate());
                } catch (Exception e) 
                {
                JOptionPane.showMessageDialog(this, "Please Select a Valid Date of Joining.", "Invalid DOJ", JOptionPane.ERROR_MESSAGE);
                return;
                }
            
            String gender = "";
            if (jrb1.isSelected()) {
            	gender = "Male";
            }
            else if (jrb2.isSelected()){
            	gender = "Female";
            }
            
            String isPhd = "";
            if (jrbyes.isSelected()) {
            	gender = "Yes";
            }
            else if (jrbno.isSelected()){
            	gender = "No";
            }
            
            boolean success1 = validateCheck(  dob );
            boolean success = Checks.validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, qualification, selectedFile, department, role, tfxii , empid, doj );
            
         // add teacher only if validation gets successful
            if (success && success1 )
            {
            	boolean added= addDetails(name,fname,phone,aadhar,email,x,xii,gender,address,dob,department,designation,qualification,selectedFile,empid,grad,pg,phd,isPhd, doj);
                JOptionPane.showMessageDialog(this, "Teacher Details added successfully!  " , "Success", JOptionPane.INFORMATION_MESSAGE);
                if (added) {
                	String type = "department";
                	generator.updateCounter(yoj, department , role, type);
                    JOptionPane.showMessageDialog(this, "Counter Updated ! Roll No: " , "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to Update Counter. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else 
            {
                JOptionPane.showMessageDialog(this, "Failed to add Teacher Details. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
         
             
        } 
    	else if (ae.getSource() == get)
    	{
    		 String department = (String) cbdept.getSelectedItem();
             String role = (String) cbrole.getSelectedItem();
             if ( role.equalsIgnoreCase("hod") || role.equalsIgnoreCase("hoa") ||role.equalsIgnoreCase("hoe")  )
             {
             	role = "HOD";
             }
             else if ( role.startsWith("Sr.") )
             {
             	role = "Sr.";
             }
             else if ( role.startsWith("Asst.")  )
             {
             	role = "Asst.";
             }
             else if ( role.startsWith("Jr.") )
             {
             	role = "Jr.";
             }
             
    		 String yoj = "";
             try {          	
             	     // Extract only the year from the date
                     SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                     yoj = yearFormat.format(dcdoj.getDate());
                 } catch (Exception e) 
                 {
                    JOptionPane.showMessageDialog(this, "Please Select a Valid Year of Joining.", "Invalid DOJ", JOptionPane.ERROR_MESSAGE);
                    return;
                 }
    		
    	    // Check if the selected course or branch is invalid
    	    if (cbdept.getSelectedItem().equals("Select Department") || cbrole.getSelectedItem().equals("Select Role") || dcdoj.getDate() == null) 
    	    {
    	        JOptionPane.showMessageDialog(this, "Please Enter DOJ & Select Department and Role First.", "Error", JOptionPane.ERROR_MESSAGE);
    	    } else 
    	    {
    	       // JOptionPane.showMessageDialog(this, "Get Button Added.", "Success", JOptionPane.INFORMATION_MESSAGE);
    	        String empId = labelempId.getText();
        	    if (empId.equals(" - - - - - - - ")) 
        	        {
        	    	    try 
        	    	      {
        	    	    	String type = "department";
        	                 String empID = generator.generateTempID(yoj, department, role, type );
        	                 labelempId.setText(empID);  
        	                 JOptionPane.showMessageDialog(null, "Temp ID generated successfully! Temp ID: " + empID);
        	              } catch (Exception e) 
        	    	      {
        	                 JOptionPane.showMessageDialog(null, "Failed to add teacher. Error: " + e.getMessage());
        	              }
        	    	
        	    	   //  labelempId.setText(empID);                        // labelempId.setText("2010"+first2);
                    }     	    
    	    }
    	}
    	else
        {
    		redirectToLogin();
        }
    }
    
    
    private Validate valid = new Validate();
    private boolean validateCheck( String dob ) 
    {
    	
    	
    	
    	try {
	        // Validate age is between 18 and 40
	        int age = valid.calculatedAge(dob);
	        if (age < 18 || age > 55)
	            {
	            JOptionPane.showMessageDialog(null, "Age Must Be Between 18 and 55 Years.", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    
    private boolean addDetails(String name, String fname, String phone, String aadhar, String email, String x, String xii,String gender,String address,String dob,String department,String designation,String qualification,File selectedFile,String empid,String grad,String pg,String phd, String isPhd, String doj ) {
    	// Saving to Database 
        try (  FileInputStream fis = new FileInputStream(selectedFile)) {        //  - this method auto closes fis to prevent resource leakage
     	
     	//fis = new FileInputStream(selectedFile);
     	// JOptionPane.showMessageDialog(this, "Fis : "+fis);
     	
         JdbcConnection jdbc = new JdbcConnection();
        // JOptionPane.showMessageDialog(this, "JDBC : "+jdbc);
         
         String query = "INSERT INTO temp_teacher (empid, name, fname, phone, email, aadhar, dob, address, pic, department, designation, qualification, classx, classxii, grad, pg, phd, gender, isphd, doj ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
         ps = jdbc.prepareStatement(query);
         ps.setString(1, empid);
         ps.setString(2, name);
         ps.setString(3, fname);
         ps.setString(4, phone);
         ps.setString(5, email);
         ps.setString(6, aadhar);
         ps.setString(7, dob);
         ps.setString(8, address);
         ps.setBinaryStream(9, fis, (int) selectedFile.length());
         ps.setString(10, department);
         ps.setString(11, designation);
         ps.setString(12, qualification);
         ps.setDouble(13, Double.parseDouble(x));
         ps.setString(14, xii);
         ps.setString(15, grad);
         ps.setString(16, pg);
         ps.setString(17, phd);
         ps.setString(18, gender);
         ps.setString(19, isPhd);
         ps.setString(20, doj);

        // JOptionPane.showMessageDialog(this, "Querry : "+query);
      //   JOptionPane.showMessageDialog(this, "ID : "+empid+" , Name : "+name+" , Fname : "+fname+" , Phone : "+phone+" , Email : "+email+" , Aadhaar : "+aadhar+" , DOB : "+dob+" , Address : "+address+" , Pic : "+selectedFile+" , Department : "+department+" , Role : "+role+" , Qualification : "+qualification+" , ClassX : "+x+" , ClassXII : "+xii+" , Grad : "+grad+" , Pg : "+pg+" , Phd : "+phd+" , Gender : "+gender+" , IsPhd : "+isPhd+" , DOJ : "+doj+" ,  > " );
         
         int flag = ps.executeUpdate();
         if (flag > 0) {
             JOptionPane.showMessageDialog(this, "Teacher Registered Successfully.");
             //setVisible(false);
         } else {
             JOptionPane.showMessageDialog(this, "Failed to Register Teacher !");
         }
         
         return flag>0;
     } catch (Exception ex) {
         JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
     }
        return false;
    }
    
    
    private void redirectToLogin() {
        setVisible(false); 
        new Login(); 
    }
    
  //  public static void main(String[] args) {   new AddTeacher();  }
}

