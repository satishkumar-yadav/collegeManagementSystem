package collegeManagement;

import jdbcGUI.LoginDB;
import temp.AddNewStudent;
import temp.AddNewTeacher;
import temp2.DatabaseSchemaCreator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Login extends JFrame implements ActionListener {

	JButton blogin, bcancel, bforget, bdatabase, bregister, bnext, bforgetId;
    JTextField tfusername ;
    JLabel lbNewRole,lb11, lbnew ;
    JPasswordField tfpassword;
    JComboBox jcbrole, jcbrole2, jcbrole3;
    PreparedStatement ps;
    JDialog loadingDialog;
    
    // File to store the state of the "Database" button
   // private static final String FLAG_FILE = "projectRunFlag.txt";     // modified to below code to create this in current dir where program is running 
    private static final String FLAG_FILE = System.getProperty("user.dir") + "/projectRunFlag.txt";

	
	public Login()
	{
		JLabel lb0,lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,lb9,lb10;
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//	getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
     // Set icon image                                         // this address can also be used below : "  src/images/login.jpg "
        Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Satish Kumar Yada\\Desktop\\7th Sem\\Project-I\\ERP Project\\SKY College Management ERP\\src\\images\\login.jpg") ;
        setIconImage(icon);
         
     // Header Labels
        lb1 = new JLabel("Welcome !! to ERP ");
        lb1.setBounds(250, 15, 350, 40);
        lb1.setBackground(Color.BLACK);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma", Font.BOLD, 35));
        add(lb1);
        
        lb9 = new JLabel("College Management System ");
        lb9.setBounds(120, 50, 550, 40);
        lb9.setBackground(Color.BLACK);
        lb9.setForeground(Color.BLUE);
        lb9.setFont(new Font("Tahoma", Font.BOLD, 35));
        add(lb9);
        
        lb2 = new JLabel("Designed and Developed : ");
        lb2.setBounds(350, 100, 285, 30);
        lb2.setBackground(Color.BLACK);
        lb2.setForeground(Color.RED);
        lb2.setFont(new Font("Serif", Font.BOLD, 25));
        add(lb2);
        
        lb10 = new JLabel(" By < BYTE CODERS & Group >");
        lb10.setBounds(605, 122, 500, 30);
        lb10.setBackground(Color.BLACK);
        lb10.setForeground(Color.RED);
        lb10.setFont(new Font("Serif", Font.BOLD, 22));
        add(lb10);
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login_Wondershare.png"));
        Image img2 = img.getImage().getScaledInstance(290, 350, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb3 = new JLabel(img3);
        lb3.setBounds(530, 200, 290, 350);
        add(lb3);
        
     // Role Selection
        lb4 = new JLabel("Enter Login Credentials First :");
        lb4.setBounds(80, 250, 400, 35);
        lb4.setForeground(Color.red);
        lb4.setFont(new Font("Tahoma", Font.BOLD, 24));
        add(lb4);
        
        lb8 = new JLabel("SELECT USER ROLE   :");
        lb8.setBounds(80, 290, 270, 30);
        lb8.setForeground(Color.yellow);
        lb8.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb8);
        
        JLabel lb12 = new JLabel("*");
        lb12.setBounds(280, 290, 300, 30);
        lb12.setForeground(Color.red);
        lb12.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb12);
        
        String role[] = {"Select","Admin","Faculty","Student", "Database" };
        
        jcbrole = new JComboBox(role);
        jcbrole.setBounds(340,290,100,30);
       // jcbrole.addActionListener(jcbrole);
        add(jcbrole);
        
        String role2[] = {"Select","Accountant","Teacher","Librarian","LabIncharge","Driver","Guards","Other Workers"};
        jcbrole2 = new JComboBox(role2);
        jcbrole2.setBounds(460,290,100,30); 
        add(jcbrole2);
       //  jcbrole2.disable();
        
     // Add an ItemListener to jcbrole    // Enable/Disable sub-role based on main role selection
        jcbrole.addItemListener(e -> {
            if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
                String selectedRole = (String) jcbrole.getSelectedItem();
                
                // Enable jcbrole2 if "Faculty" is selected, otherwise disable it
                if ("Faculty".equals(selectedRole)) {
                    jcbrole2.setEnabled(true);
                } else {
                    jcbrole2.setEnabled(false);
                    jcbrole2.setSelectedIndex(0); // Reset the subrole to "Select"
                }
            }
        });
       
        // Username and Password Fields
        lb5 = new JLabel("ENTER  USERNAME    :");
        lb5.setBounds(80, 330, 270, 30);
        lb5.setForeground(Color.yellow);
        lb5.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb5);
        
        JLabel lb13 = new JLabel("*");
        lb13.setBounds(275, 330, 300, 30);
        lb13.setForeground(Color.red);
        lb13.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb13);
        
        tfusername = new JTextField("enter username or userid");
        tfusername.setBounds(340, 330, 220, 30);
        tfusername.setFont(new Font("Arial", Font.BOLD, 15));
   //      Use focus listeners to clear placeholder text:     
        tfusername.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfusername.getText().equals("enter username or userid")) {
                    tfusername.setText("");
                }
            }
        });
        add(tfusername);
        
        lb6 = new JLabel("ENTER  PASSWORD   : ");
        lb6.setBounds(80, 370, 270, 30);
        lb6.setForeground(Color.yellow);
        lb6.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb6);
        
        JLabel lb14 = new JLabel("*");
        lb14.setBounds(275, 370, 300, 30);
        lb14.setForeground(Color.red);
        lb14.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb14);
        
        tfpassword = new JPasswordField("enter password");
        tfpassword.setBounds(340, 370, 220, 30);
        tfpassword.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        tfpassword.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfpassword.getText().equals("enter password")) {
                    tfpassword.setText("");
                }
            }
        });
        add(tfpassword);
        
        lb11 = new JLabel("Trouble Logging in ??  ");
        lb11.setBounds(100, 500, 200, 30);
        lb11.setForeground(Color.YELLOW);
        lb11.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(lb11);
        
        lbnew = new JLabel("New Here ??  ");
        lbnew.setBounds(100, 530, 200, 30);
        lbnew.setForeground(Color.YELLOW);
        lbnew.setFont(new Font("Tahoma", Font.BOLD, 17));
        add(lbnew);
        
        lbNewRole = new JLabel("Select Your Role ");
        lbNewRole.setBounds(100, 560, 200, 30);
        lbNewRole.setForeground(Color.YELLOW);
        lbNewRole.setFont(new Font("Tahoma", Font.BOLD, 17));
      //  lbNewRole.setVisible(true);
        add(lbNewRole);
        
        String role3[] = {"Select","New Student","New Teacher","New Faculty" };
        
        jcbrole3 = new JComboBox(role3);
        jcbrole3.setBounds(340,565,100,25);
        jcbrole3.setForeground(Color.MAGENTA);
    //    jcbrole3.setVisible(true);
        add(jcbrole3);
        
     // Buttons
        blogin =new JButton("Login");
        blogin.setBounds(80, 420, 100, 40);
        blogin.setBackground(Color.GREEN);
        blogin.setForeground(Color.WHITE);
        blogin.setFont(new Font("Tahoma", Font.BOLD, 18));
        blogin.addActionListener(this);
        add(blogin);
        
        bcancel =new JButton("Cancel");
        bcancel.setBounds(460, 420, 100, 40);
        bcancel.setBackground(Color.PINK);
        bcancel.setForeground(Color.WHITE);
        bcancel.setFont(new Font("Tahoma", Font.BOLD, 17));
        bcancel.addActionListener(this);
        add(bcancel);
        
        bforget =new JButton("Forget Password !");
        bforget.setBounds(340, 505, 220, 25);
        bforget.setBackground(Color.ORANGE);
        bforget.setForeground(Color.BLACK);
        bforget.setFont(new Font("Tahoma", Font.BOLD, 16));
        bforget.addActionListener(this);
        add( bforget);
        
        bforgetId =new JButton("Forget User ID !");
        bforgetId.setBounds(570, 505, 220, 25);
        bforgetId.setBackground(Color.ORANGE);
        bforgetId.setForeground(Color.BLACK);
        bforgetId.setFont(new Font("Tahoma", Font.BOLD, 16));
        bforgetId.addActionListener(this);
        add( bforgetId);
        
        bregister =new JButton("Register Now");
        bregister.setBounds(340, 535, 220, 25);
        bregister.setBackground(Color.ORANGE);
        bregister.setForeground(Color.RED);
        bregister.setFont(new Font("Tahoma", Font.BOLD, 16));
        bregister.addActionListener(this);
        add( bregister);
        
        bnext =new JButton("Next");
        bnext.setBounds(460, 565, 100, 25);
      //  bnext.setBackground(Color.ORANGE);
        bnext.setForeground(Color.MAGENTA);
        bnext.setFont(new Font("Tahoma", Font.BOLD, 16));
        bnext.addActionListener(this);
      //  bnext.setVisible(false);
        add( bnext);
       
        bdatabase =new JButton("DataBase");
        bdatabase.setBounds(190, 420, 140, 40);
        bdatabase.setBackground(Color.ORANGE);
        bdatabase.setForeground(Color.BLACK);
        bdatabase.setFont(new Font("Tahoma", Font.BOLD, 16));
        bdatabase.addActionListener(this);
        add( bdatabase);   
        
        hideRegister();
        
     // Set visibility based on database button state
        toggleButtonsVisibility(isDatabaseVisible());
        
        lb7 = new JLabel("Copyright @ BYTE CODERS. All rights reserved. ");
        lb7.setBounds(150, 600, 680, 30);
        lb7.setForeground(Color.WHITE);
       // lb7.setBackground(Color.WHITE);
        lb7.setFont(new Font("Tahoma", Font.BOLD, 28));
        add(lb7);
        
        ImageIcon bg = new ImageIcon(ClassLoader.getSystemResource("images/third.jpg"));
        Image bg2 = bg.getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT);
        ImageIcon bg3 = new ImageIcon(bg2);
        lb0 = new JLabel(bg3);
        lb0.setBounds(00, 00, 1024, 768);
        add(lb0);
        
        setSize(950,700);
        setLocation(190,20);
        setVisible(true);
       
        if ( isDatabaseVisible() == true  ) 
        {
        	JOptionPane.showMessageDialog(null, "You are running CMS for the first time, so Set the DataBase Credential First !!");
            JOptionPane.showMessageDialog(null, "Enter DataBase UserName and PassWord, or leave it to use default UserName or PassWord..");
        }
        
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource() == blogin)
		{
			handleLogin();
		}
		else if (ae.getSource() == bcancel)
		{
			tfusername.setText("");
			tfpassword.setText("");
		
		}
		else if (ae.getSource() == bforget )
		{
			new ForgetPassword();
			setVisible(false);
		}
		else if (ae.getSource() == bforgetId )
		{
			new ForgetUserId() ;
			setVisible(false);
		}
		else if (ae.getSource() == bregister )
		{
			showRegister();
		}
		else if (ae.getSource() == bnext )
		{
			handleNext();
			hideRegister();
		//	setVisible(false);
		}
		else if (ae.getSource() == bdatabase )
		{
			 getCredential() ;
			
			Database.createDatabaseSchema();
            disableDatabaseButton(); // Disable the button after it's clicked
           // toggleButtonsVisibility(false);
            toggleButtonsVisibility(isDatabaseVisible());
            
            tfusername.setText("enter username or userid");
			tfpassword.setText("enter password");
			
			
		}
	}
	
	
    private void getCredential() 
    {
    	String username = tfusername.getText().trim();
	    String passWord = tfpassword.getText().trim();
	    
	     if (  username.isBlank() && passWord.equalsIgnoreCase("enter password")  )
		   {
			   username = "root";
			   passWord = "12345678";
	           
	          // JOptionPane.showMessageDialog(null, " From  default pass, UserName: < "+username+"  >, PassWord : < "+passWord);
	          
	       } 
	     else if (  username.isBlank() &&  ( !passWord.equalsIgnoreCase("enter password") &&  !passWord.isBlank() ) )
		   {
			   username = "root"; 
	           
	          // JOptionPane.showMessageDialog(null, " From username Blank , UserName: < "+username+"  >, PassWord : < "+passWord);
	           
	       }
	   
	   else if (  !username.isBlank() && passWord.isBlank()  )
	   {
		   passWord = "12345678";
           
          // JOptionPane.showMessageDialog(null, " From password Blank 1, UserName: < "+username+"  >, PassWord : < "+passWord);
           
       }
	   else if (  !username.isBlank() &&  passWord.equalsIgnoreCase("enter password")  )
	   {
		   passWord = "12345678";
           
         //  JOptionPane.showMessageDialog(null, " From password Blank 2, UserName: < "+username+"  >, PassWord : < "+passWord);
           
       }
      
 //      else
    //   {
	   //  JdbcConnection jdc= new JdbcConnection();
	     JdbcConnection.putCredential ( username,  passWord);
	     
	   //  new JdbcConnection();
    	
	     // JOptionPane.showMessageDialog(null, " Inside Else loop ");
	 //    return true;
 //      }
     //   JOptionPane.showMessageDialog(null, " Outside if-else loop ");
     //  return false ;
       
    }
	
	private void handleLogin() {
		String userName = tfusername.getText().trim();
	    String passWord = tfpassword.getText().trim();
		String Role = (String) jcbrole.getSelectedItem(); 
	// or	String role = (String) jcbrole.getItemAt(jcbrole.getSelectedIndex()); 
		//JOptionPane.showMessageDialog(this, "Role : < "+Role+" >, UserName : < "+userName+" >, Password : < "+passWord+" >");
		
		 // If "Faculty" is selected, get the sub-role from jcbrole2
	    if ("Faculty".equals(Role)) {
	        Role = (String) jcbrole2.getSelectedItem();
	        if ("Select".equals(Role)) {
	        	
	            JOptionPane.showMessageDialog(this, "Please select a sub-role for Faculty.");
	            return; // Stop further execution if no sub-role is selected
	        }
	    }
		
	    // Validate input fields
        if (userName.isEmpty() || passWord.isBlank() || "Select".equals(Role)) {
        	
            JOptionPane.showMessageDialog(this, "All * Marked Fields Must be Filled!");
            return;
        }	

        // Show loading indicator
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(this, "Logging in... Please wait.");
        });
    //  showLoadingIndicator();
        
        //  String querylogin = "select * from login where role = '"+Role+"' and username = '"+userName+"' and password = '"+passWord+"' ";
        // String querylogin = "select * from login where role = 'Admin' and username = 'admin' and password = '123' ";   // above replacement if no variable is used
         try {
         	// JdbcConnection con = new JdbcConnection();
         	
         	JdbcConnection c1 = new JdbcConnection();
         	
       
         	// JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
         	
         	 // Secure query with PreparedStatement
             String querylogin = "SELECT * FROM login WHERE role = ? AND username = ? AND password = ?";
            
             PreparedStatement ps = c1.prepareStatement(querylogin);
          
                       
             ps.setString(1, Role);
             ps.setString(2, userName);
             ps.setString(3, passWord);
             //  JOptionPane.showMessageDialog(this, querylogin);
             
          // Execute query
             ResultSet rs = ps.executeQuery();
            
             if (rs.next()) {
            	 // hideLoadingIndicator();
            	    String Id = rs.getString("id");
    			    JOptionPane.showMessageDialog(Login.this, "  Login Successful !! ");
    			    redirectToRolePage(Role, Id);
                    setVisible(false);
    			    
//        	String uName = rs.getString("username"); 
//			String pass = rs.getString("password");
//			String rle = rs.getString(1);
//    		JOptionPane.showMessageDialog(this, "Role : < "+rle+" >, UserName : < "+uName+" >, Password : < "+pass+" >");
            	 
//            	 SwingUtilities.invokeLater(() -> {
//                    
//                 });
             } 
             else 
             {
            	// hideLoadingIndicator();
                // loadingDialog.dispose();
                   //   loadingDialog.hide();
                    //  loadingDialog.setVisible(false);
                   
                     JOptionPane.showMessageDialog(Login.this, "  Login Failed !! , Wrong Credential  ");
    				 JOptionPane.showMessageDialog(Login.this, "  Enter Credential Again  ");
                     return ;
            	 
//            	 SwingUtilities.invokeLater(() -> {
//                    
//                 });
             }
             
         } 
         catch (Exception e) 
         {
        	// hideLoadingIndicator();
             JOptionPane.showMessageDialog(Login.this, "Database connection error: " + e.getMessage());
        	 
//        	 SwingUtilities.invokeLater(() -> {
//                 // put above code here
//             });
             e.printStackTrace();
         }
        
        // Run the login process in a separate thread
//        SwingWorker<Void, Void> worker = new SwingWorker<>() {
//            @Override
//            protected Void doInBackground() {
//            	// all try block code here 
//                return null;
//            }
//        };

      //  worker.execute();
        
	}  // handle login 
       
	
    private void redirectToRolePage(String Role, String id) {
    	  // Redirect based on role
        switch(Role)
        {
              case "Admin" :
        	                     new Admin();
        	                    // JOptionPane.showMessageDialog(this, "  Welcome Admin, Login Successful. ");
        	                     break;
        	         
              case "Accountant" :
	                                // new Accountant(id);
	                                 JOptionPane.showMessageDialog(this, "  Welcome Accountant, Login Successful.  ");
	                                 break;
	         
              case "Teacher" :
	                                 new Teacher(Role,id);
	                                // JOptionPane.showMessageDialog(this, "  Welcome Teacher, Login Successful.  ");
	                                 break;
	         
              case "Student" :
	                                 new Student(Role,id);
	                                // JOptionPane.showMessageDialog(this, "  Welcome Student, Login Successful.  ");
	                                 break;
	         
              case "Librarian" :
	                               // new Librarian(Role,id);
	                                JOptionPane.showMessageDialog(this, "  Welcome Librarian, Login Successful.  ");
	                                break;
	                                
              case "Database" :
                                  // new Database(Role,id);
            	                   JOptionPane.showMessageDialog(this, "  Welcome Database, Login Successful.  ");
            	                  // LoginDB ldb = new LoginDB();
            	                   // ldb.LoginDB();
            	                   new LoginDB();
                                   
                                  break;   
              
              case "LabIncharge" :
                                  JOptionPane.showMessageDialog(this, "  Welcome LabIncharge, Login Successful.  ");
                                  break;                      
                                  
              case "Guards" :
                                  JOptionPane.showMessageDialog(this, "  Welcome Guards, Login Successful.  ");
                                  break;                     
               
              case "Driver" :
                                 JOptionPane.showMessageDialog(this, "  Welcome Driver, Login Successful.  ");
                                 break;      
                  
              case "Other Workers" :
                                   JOptionPane.showMessageDialog(this, "  Welcome Other Workers, Login Successful.  ");
                                   break;                       
	         
              default:
                                JOptionPane.showMessageDialog(this, "Unknown Role");
	         
        }
    }
    
    private void handleNext() {
		String Role = (String) jcbrole3.getSelectedItem(); 
    	
         if ( "Select".equals(Role)) {
        	
            JOptionPane.showMessageDialog(this, "Select Your Role First !");
            return;
        }	
         
         switch(Role)
         {
               case "New Faculty" :
         	                    // new AddNewFaculty();
         	                     JOptionPane.showMessageDialog(this, " Faculty Register Open Soon.. ");
         	                     break;
 	         
               case "New Teacher" :
 	                                 new AddNewTeacher();
 	                                 setVisible(false);
 	                                // JOptionPane.showMessageDialog(this, "Register Teacher.  ");
 	                                 break;
 	         
               case "New Student" :
 	                                 new AddNewStudent();
 	                                 setVisible(false);
 	                               //  JOptionPane.showMessageDialog(this, "Register Student.  ");
 	                                 break;
 	         
               default:
                                 JOptionPane.showMessageDialog(this, "Unknown Role");
 	         
         }
    }
    
    
    // Method to check if the project is being run for the first time
    private boolean isFirstRun() {
        File file = new File(FLAG_FILE);

        // If the file does not exist, this is the first run
        if (!file.exists()) {
            try {
                // Create the file to mark that the project has been run
                FileWriter writer = new FileWriter(file);
                writer.write("This project has been run before.");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        // If the file exists, this is not the first run
        return false;
    }

    /*
    private boolean isDatabaseVisible() {
   	 // Check if this is the first time the project is run
       if (isFirstRun()) {
         //  bdatabase.setEnabled(true); // Enable the button on first run
         //  bdatabase.setVisible(true);
           return true;
       } else {
           //  bdatabase.setEnabled(false); // Disable the button afterward
         //  bdatabase.setVisible(false);
           return false;
       }
  
   }
    */
    
    private boolean isDatabaseVisible() {
        File file = new File(FLAG_FILE);
        return !file.exists();
       // return file.exists();
    }
    
    // Method to disable the "Database" button and persist the state
    private void disableDatabaseButton() {
        bdatabase.setEnabled(false);
        bdatabase.setVisible(false);

        try {
            // Update the flag file to mark that the button has been used
            FileWriter writer = new FileWriter(FLAG_FILE);
            writer.write("Database button has been used.");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    
    private void toggleButtonsVisibility(boolean isDatabaseVisible) {
        blogin.setVisible(!isDatabaseVisible);
        bcancel.setVisible(!isDatabaseVisible);
        bforget.setVisible(!isDatabaseVisible);
        bforgetId.setVisible(!isDatabaseVisible);
        bregister.setVisible(!isDatabaseVisible);
        lb11.setVisible(!isDatabaseVisible);
        lbnew.setVisible(!isDatabaseVisible);
        jcbrole.setEnabled(!isDatabaseVisible);
        jcbrole2.setEnabled(!isDatabaseVisible);
        bdatabase.setVisible(isDatabaseVisible);
    }
    
   
    
    private void showLoadingIndicator() {
        loadingDialog = new JDialog(this, "Loading", true);
        JLabel loadingLabel = new JLabel("Loading, please wait...");
        loadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loadingDialog.add(loadingLabel);
        loadingDialog.setSize(200, 100);
        loadingDialog.setLocationRelativeTo(this);
        SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));
    }

    private void hideLoadingIndicator() {
        if (loadingDialog != null) {
            loadingDialog.dispose();
        }
        //loadingDialog.dispose();
     //   loadingDialog.hide();
      //  loadingDialog.setVisible(false);
    }
    
    private void hideRegister() {
       
    	lbNewRole.setVisible(false);
		jcbrole3.setVisible(false);
		bnext.setVisible(false);
    }
    
    private void showRegister() {
        
    	lbNewRole.setVisible(true);
		jcbrole3.setVisible(true);
		bnext.setVisible(true);
    }
    
	public static void main(String[] args) {  new Login();  }

}




/*
 
1. Flag File (projectRunFlag.txt):
-A file is used to persist the state of whether the project has been run before.
-If the file does not exist, the project is considered to be running for the first time.

2.isFirstRun() Method:
-Checks if the flag file exists.
-If the file doesn't exist, it creates the file and returns true (first run).
-If the file exists, it returns false (not the first run).

3.disableDatabaseButton() Method:
-Disables the "Database" button.
-Updates the flag file to indicate that the "Database" button has been used.

4.Button State Initialization:
-During the initialization of the Login class, the isFirstRun() method is called to set the initial state of the "Database" button.

How It Works

1.On the first run, the projectRunFlag.txt file does not exist:
-The "Database" button is enabled.
-The file is created to mark the first run.

2.On subsequent runs:
-The file exists, so the "Database" button is disabled.

3.When the "Database" button is clicked:
-The button is disabled, and the state is saved to the file.

How to Test

1.Run the application for the first time. The "Database" button should be enabled.
2.Close and reopen the application. The "Database" button should now be disabled.
3.Delete the projectRunFlag.txt file and rerun the application to simulate the first run again.
 
 Key Changes
1.System.getProperty("user.dir"):
-This retrieves the current working directory of the application.
-The projectRunFlag.txt file will be created in the same directory where your project resides or where the application is run.

2.File Location:
-The file will now always be created in the project's root folder (e.g., where the .java or .class files are located).

 
 */


/*
Threading (SwingWorker):
Properly manages UI updates using SwingUtilities.invokeLater to avoid threading issues.

The issue with your loadingDialog not closing dynamically stems from how Swing manages its UI components and threads. The dialog's visibility needs 
to be updated on the Event Dispatch Thread (EDT), and sometimes, if background operations block this thread, it can prevent updates like hiding the 
dialog.

Here's how to fix the issue:

Changes to Fix the Issue:
1.Use a Background Thread for Database Queries: Avoid executing your login logic (database queries) on the EDT, as this blocks the UI updates. Use a 
SwingWorker or a separate thread for such tasks.
2.Ensure Proper Dialog Handling: Ensure hideLoadingIndicator() is always called in all execution paths (success or failure).

Here’s the updated code for handleLogin, showLoadingIndicator, and hideLoadingIndicator:

Key Improvements:
SwingWorker: Ensures database operations run in a background thread, keeping the UI responsive.
SwingUtilities.invokeLater: Ensures UI updates occur on the EDT.
Dynamic Closing: hideLoadingIndicator() is called dynamically in both success and error cases.
This approach will ensure that the loading dialog closes appropriately, regardless of whether the login is successful or fails.



*/