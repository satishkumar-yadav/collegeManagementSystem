package temp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import admin.AddStudent;
import admin.AddTeacher;
import collegeManagement.JdbcConnection;
import collegeManagement.Login;

public class Credential extends JFrame implements ActionListener
{
	JButton bsubmit, breset, bback ;
	JTextField tfusername, tfsecAns;
	JPasswordField tfpassword1, tfpassword2;
	JComboBox jcbrole, jcbrole2, jcsecQues;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8, lb9, lb10, lblrole,lblid ;
	String role = "";
	String id = "";
	String phone = "";
	
	public Credential(String Role, String id,String phone)
	{
		role = Role;
		this.id=id;
		this.phone=phone;
		
        setTitle("Login Credential");
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
                 
        lb1 = new JLabel("Create Login Credential : ");
        lb1.setBounds(50, 40, 450, 35);
        lb1.setBackground(Color.BLACK);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(lb1);
                       
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login3.jpg"));
        Image img2 = img.getImage().getScaledInstance(120, 135, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb2 = new JLabel(img3);
        lb2.setBounds(500, 10, 120, 135);
        add(lb2);
        
        JLabel labelid = new JLabel("ID                                :");
        labelid.setBounds(50, 140, 400, 35);
        labelid.setForeground(Color.pink);
        labelid.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(labelid);
        
        lblid = new JLabel(id);
        lblid.setBounds(380, 140, 400, 35);
        lblid.setForeground(Color.ORANGE);
        lblid.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(lblid);
        
        lb4 = new JLabel("USER ROLE                       :");
        lb4.setBounds(50, 180, 300, 30);
        lb4.setForeground(Color.pink);
        lb4.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb4);
        
        lblrole = new JLabel(role);
        lblrole.setBounds(380, 180, 300, 30);
        lblrole.setForeground(Color.orange);
        lblrole.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblrole);
             
        lb5 = new JLabel("ENTER  USERNAME           :");
        lb5.setBounds(50, 220, 300, 30);
        lb5.setForeground(Color.RED);
        lb5.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb5);
        
        tfusername = new JTextField("enter username or userid");
        tfusername.setBounds(380, 220, 240, 30);
        tfusername.setFont(new Font("Arial", Font.BOLD, 15));
        tfusername.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfusername.getText().equals("enter username or userid")) {
                    tfusername.setText("");
                }
            }
        });
        add(tfusername);
        
        lb9 = new JLabel("Select Security Question    :");
        lb9.setBounds(50, 260, 300, 30);
        lb9.setForeground(Color.RED);
        lb9.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb9);
        
        String secQues[] = {"Select","Pet Name","Favourite Food","Favourite Place", "Place of Birth", "First School Attended" };
        
        jcsecQues = new JComboBox(secQues);
        jcsecQues.setBounds(380,260,240,30);
        add(jcsecQues);
        
        lb10 = new JLabel("Enter Security Answer        :");
        lb10.setBounds(50, 300, 300, 30);
        lb10.setForeground(Color.RED);
        lb10.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb10);
        
        tfsecAns = new JTextField("enter Sec Ans ");
        tfsecAns.setBounds(380, 300, 240, 30);
        tfsecAns.setFont(new Font("Arial", Font.BOLD, 15));
        tfsecAns.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfsecAns.getText().equals("enter Sec Ans ")) {
                	tfsecAns.setText("");
                }
            }
        });
        add(tfsecAns);
        
        lb6 = new JLabel("Enter Password                  :");
        lb6.setBounds(50, 340, 300, 30);
        lb6.setForeground(Color.RED);
        lb6.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb6);
        
        tfpassword1 = new JPasswordField("enter password");
        tfpassword1.setBounds(380, 340, 240, 30);
        tfpassword1.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        tfpassword1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfpassword1.getText().equals("enter password")) {
                	tfpassword1.setText("");
                }
            }
        });
        add(tfpassword1);
        
        lb7 = new JLabel("Enter Password Again        :");
        lb7.setBounds(50, 380, 300, 30);
        lb7.setForeground(Color.RED);
        lb7.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb7);
        
        tfpassword2 = new JPasswordField("enter password");
        tfpassword2.setBounds(380, 380, 240, 30);
        tfpassword2.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        tfpassword2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfpassword2.getText().equals("enter password")) {
                	tfpassword2.setText("");
                }
            }
        });
        add(tfpassword2); 
                 
        breset =new JButton("Reset");
        breset.setBounds(280, 450, 100, 40);
        breset.setBackground(Color.RED);
        breset.setForeground(Color.WHITE);
        breset.setFont(new Font("Tahoma", Font.BOLD, 18));
        breset.addActionListener(this);
        add(breset);
                
        bsubmit =new JButton("Submit");
        bsubmit.setBounds(520, 450, 100, 40);
        bsubmit.setBackground(Color.green);
        bsubmit.setForeground(Color.BLACK);
        bsubmit.setFont(new Font("Tahoma", Font.BOLD, 15));
        bsubmit.addActionListener(this);
        add( bsubmit);
        
        bback =new JButton("Back");
        bback.setBounds(50, 450, 100, 40);
        bback.setBackground(Color.blue);
        bback.setForeground(Color.white);
        bback.setFont(new Font("Tahoma", Font.BOLD, 15));
        bback.addActionListener(this);
        add( bback);
       
       
        lb8 = new JLabel("Copyright @ BYTE CODERS. All rights reserved. ");
        lb8.setBounds(50, 520, 680, 30);
        lb8.setForeground(Color.BLACK);
        lb8.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb8);
        
     // Handle window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	setDefaultCredential();
            	redirectToLogin();
            }

        });
     
        setSize(700,600);
        setLocation(300,20);
       // setLocationRelativeTo(null);
        setVisible(true);
		
	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		 if ( ae.getSource() == breset )
		{
			resetUI();
		}
		else if (ae.getSource() == bsubmit )
		{
			 handleCheck();
		}
		else if ( ae.getSource() == bback )
		{
			setDefaultCredential();
			 redirectToPrevPage();
		}
		
	}
	
	
	 private void redirectToPrevPage() {
		 if(role.equals("Student") ) {
			  setVisible(false); 
		        new AddNewStudent();
		 }
		 else if(role.equals("Teacher")){
			  setVisible(false); 
		        new AddNewTeacher();
		 }
		 else {
	        setVisible(false); 
	        new Login();
		 }	 
	    }
	
	
	 private void handleCheck() {
		 String userName = tfusername.getText().trim();
			String passWord = tfpassword1.getText().trim();
			String passWord2 = tfpassword2.getText().trim();
			String secAns = tfsecAns.getText().trim();
		    String secQues = (String)jcsecQues.getSelectedItem(); 		   				
            if (  !( userName.isEmpty())  && !( userName.equals("enter username or userid") ) && !("Select".equals(secQues)) && !( secAns.isEmpty())  && !( secAns.equals("enter Sec Ans ") ) && !(passWord.isBlank() ) && !(passWord2.isBlank() ) && !(passWord.equals("enter password") ) ) 
             {   handleInsert();       } 
            else {  JOptionPane.showMessageDialog(this, "Enter All Details First ");     return;  }  
          }
	 
	 
	 private void handleInsert() {
		    String Id = id;
		    String userName = tfusername.getText().trim();
		   // String passWord = String.valueOf(tfpassword1.getPassword()).trim();    // better way a/c chatgpt

			String passWord = tfpassword1.getText().trim();
			String passWord2 = tfpassword2.getText().trim();
			String secAns = tfsecAns.getText().trim();
			// String secQues = jcsecQues.getSelectedItem() != null ? jcsecQues.getSelectedItem().toString() : "";

		    String secQues = (String)jcsecQues.getSelectedItem();
			String Role = role;
			
			if ( passWord.equals(passWord2)  )
			{
				SwingUtilities.invokeLater(() -> {
		            JOptionPane.showMessageDialog(this, "Creating Credential... Please Wait....");
		        });
				
//								 
		            try {
		                  JdbcConnection con = new JdbcConnection();
		                  //JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
		                  String query = null;
		                  if ("student".equalsIgnoreCase(Role)) 
		                  {
		                	  query = "insert into temp_login ( role, username, password, sec_ques, sec_ans, id) values ( ?, ?, ?, ?, ?, (SELECT rollno FROM temp_student WHERE rollno = ?) ) ";
		                  } else if ("teacher".equalsIgnoreCase(Role)) 
		                  {
		                	  query = "insert into temp_login ( role, username, password, sec_ques, sec_ans, id) values ( ?, ?, ?, ?, ?, (SELECT empid FROM temp_teacher WHERE empid = ?) ) ";
		                  }
		                  else if ("faculty".equalsIgnoreCase(Role)) 
		                  {
		                	  query = "insert into temp_login ( role, username, password, sec_ques, sec_ans, id) values ( ?, ?, ?, ?, ?, (SELECT empid FROM temp_faculty WHERE empid = ?) ) ";
		                  }
		                  
		                  PreparedStatement ps = con.prepareStatement(query);
		                  ps.setString(1, Role);
		                  ps.setString(2, userName);
		                  ps.setString(3, passWord);
		                  ps.setString(4, secQues);
		                  ps.setString(5, secAns);
		                  ps.setString(6, Id);
		                  // JOptionPane.showMessageDialog(this, "Executing Query : "+queryupdate);   
		                  
		                  int flag = ps.executeUpdate();
		    		     // ps.close();
		                
		    		      if( flag == 1 )
		    				{
		    		    	  JOptionPane.showMessageDialog(this, "Credential Created Successfully.");
		    		    	  resetUI();
		    		    	  redirectToLogin();
		    		    	  
		    				}
		    				else
		    				{
		    					JOptionPane.showMessageDialog(this, "Credential Creation Failed !");
		    					return;
		    				}
		              } 
		            catch (Exception e) 
		            {   
		                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
		            }
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Password not Matches ");
			}  
	    }
	 
	 private void setDefaultCredential() {
			
		    String Id = id;
		    String userName = Id;
			String passWord = phone;
			String Role = role;
		 
		 try {
             JdbcConnection dfcon = new JdbcConnection();
             //JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
             String dquery = null;
             if ("student".equalsIgnoreCase(Role)) 
             {
           	  dquery = "insert into temp_login ( role, username, password, id) values ( ?, ?, ?, (SELECT rollno FROM temp_student WHERE rollno = ?) ) ";
             } else if ("teacher".equalsIgnoreCase(Role)) 
             {
           	  dquery = "insert into temp_login ( role, username, password, id) values ( ?, ?, ?, (SELECT empid FROM temp_teacher WHERE empid = ?) ) ";
             }
             else if ("faculty".equalsIgnoreCase(Role)) 
             {
           	  dquery = "insert into temp_login ( role, username, password, id) values ( ?, ?, ?, (SELECT empid FROM temp_faculty WHERE empid = ?) ) ";
             }
             
             PreparedStatement dps = dfcon.prepareStatement(dquery);
             dps.setString(1, Role);
             dps.setString(2, userName);
             dps.setString(3, passWord);
             dps.setString(4, Id);
             // JOptionPane.showMessageDialog(this, "Executing Query : "+queryupdate);   
             
             int dflag = dps.executeUpdate();
		     // ps.close();
           
		      if( dflag == 1 )
				{
		    	  JOptionPane.showMessageDialog(this, "Default Credential Created.");
		    	  resetUI();
		    	 // redirectToLogin();
		    	  
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Default Credential Creation Failed !");
					return;
				}
         } 
       catch (Exception e) 
       {   
           JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
       }
		 
  }
		 
	private void resetUI() {
	    tfusername.setText("enter username or userid");
	    tfsecAns.setText("enter Sec Ans (Case Sensitive)");
	    jcsecQues.setSelectedIndex(0);
	    tfpassword1.setText("enter password");
	    tfpassword2.setText("enter password");
		
	}

	 private void redirectToLogin() {
	        setVisible(false); 
	        new Login(); 
	    }
	
	// public static void main (String args[])  {  new Credential() ;  }
}
