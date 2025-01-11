package collegeManagement;

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

public class ForgetPassword extends JFrame implements ActionListener
{
	JButton bcheck, bcancel, bupdate, breset, back ;
	JTextField tfusername, tfsecAns;
	JPasswordField tfpassword1, tfpassword2;
	JComboBox jcbrole, jcbrole2, jcsecQues;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8, lb9, lb10 ;
	
	ForgetPassword()
	{
        setTitle("Forget Password");
		
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
                 
        lb1 = new JLabel("Forgot Password ?? ");
        lb1.setBounds(50, 40, 400, 35);
        lb1.setBackground(Color.BLACK);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(lb1);
                       
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login3.jpg"));
        Image img2 = img.getImage().getScaledInstance(140, 150, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb2 = new JLabel(img3);
        lb2.setBounds(380, 12, 140, 150);
        add(lb2);
        
        lb3 = new JLabel("Verify Identity First :");
        lb3.setBounds(50, 140, 400, 35);
        lb3.setForeground(Color.ORANGE);
        lb3.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(lb3);
        
        lb4 = new JLabel("SELECT USER ROLE         :");
        lb4.setBounds(50, 180, 300, 30);
        lb4.setForeground(Color.RED);
        lb4.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb4);
        
        String role[] = {"Select","Admin","Faculty","Student" };
        
        jcbrole = new JComboBox(role);
        jcbrole.setBounds(380,180,107,30);
        add(jcbrole);
        
        String role2[] = {"Select","Accountant","Teacher","Librarian","LabIncharge","Driver","Guards","Other Workers"};
        
        jcbrole2 = new JComboBox(role2);
        jcbrole2.setBounds(510,180,107,30);
        add(jcbrole2);
        
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
        
        tfsecAns = new JTextField("enter Sec Ans (Case Sensitive)");
        tfsecAns.setBounds(380, 300, 240, 30);
        tfsecAns.setFont(new Font("Arial", Font.BOLD, 15));
        tfsecAns.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfsecAns.getText().equals("enter Sec Ans (Case Sensitive)")) {
                	tfsecAns.setText("");
                }
            }
        });
        add(tfsecAns);
        
       bcheck =new JButton("Check");
       bcheck.setBounds(50, 380, 100, 40);
       bcheck.setBackground(Color.GREEN);
       bcheck.setForeground(Color.WHITE);
       bcheck.setFont(new Font("Tahoma", Font.BOLD, 20));
       bcheck.addActionListener(this);
       add(bcheck);
       
       bcancel =new JButton("Cancel");
       bcancel.setBounds(520, 380, 100, 40);
       bcancel.setBackground(Color.RED);
       bcancel.setForeground(Color.WHITE);
       bcancel.setFont(new Font("Tahoma", Font.BOLD, 18));
       bcancel.addActionListener(this);
       add(bcancel);
        
        lb6 = new JLabel("Enter New Password          :");
        lb6.setBounds(50, 450, 300, 30);
        lb6.setForeground(Color.RED);
        lb6.setFont(new Font("Arial", Font.BOLD, 20));
      //  lb6.disable();
        add(lb6);
        
        tfpassword1 = new JPasswordField("enter password");
        tfpassword1.setBounds(380, 450, 240, 30);
        tfpassword1.setFont(new Font("AvanteGarde", Font.BOLD, 15));
       // tfpassword1.disable();
        tfpassword1.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfpassword1.getText().equals("enter password")) {
                	tfpassword1.setText("");
                }
            }
        });
        add(tfpassword1);
        
        lb7 = new JLabel("Enter Password Again        :");
        lb7.setBounds(50, 490, 300, 30);
        lb7.setForeground(Color.RED);
        lb7.setFont(new Font("Arial", Font.BOLD, 20));
       // lb7.disable();
        add(lb7);
        
        tfpassword2 = new JPasswordField("enter password");
        tfpassword2.setBounds(380, 490, 240, 30);
        tfpassword2.setFont(new Font("AvanteGarde", Font.BOLD, 15));
       // tfpassword2.disable();
        tfpassword2.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfpassword2.getText().equals("enter password")) {
                	tfpassword2.setText("");
                }
            }
        });
        add(tfpassword2);

      
        breset =new JButton("Reset");
        breset.setBounds(520, 550, 100, 40);
        breset.setBackground(Color.orange);
        breset.setForeground(Color.WHITE);
        breset.setFont(new Font("Tahoma", Font.BOLD, 15));
        breset.addActionListener(this);
        add(breset);
        
        bupdate =new JButton("Update");
        bupdate.setBounds(280, 550, 100, 40);
        bupdate.setBackground(Color.yellow);
        bupdate.setForeground(Color.BLACK);
        bupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
        bupdate.addActionListener(this);
        add( bupdate);
        
        back =new JButton("Back");
        back.setBounds(50, 550, 100, 40);
        back.setBackground(Color.blue);
        back.setForeground(Color.white);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.addActionListener(this);
        add( back);
       
       
        lb8 = new JLabel("Copyright @ BYTE CODERS. All rights reserved. ");
        lb8.setBounds(50, 610, 680, 30);
        lb8.setForeground(Color.BLACK);
        lb8.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb8);
        
     // Handle window close event
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                redirectToLogin();
            }
        });
     
        hideButton();
      
        setSize(700,700);
        setLocation(300,20);
        setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == bcheck )
		{  
			handleCheck();
		}
		else if ( ae.getSource() == bcancel )
		{
			resetUI();
		}
		
		else if (ae.getSource() == bupdate )
		{
			handleUpdate();
		}
		else if ( ae.getSource() == breset )
		{
			// tfusername.setText(" ");
			tfpassword1.setText("");
			tfpassword2.setText("");
		}
		else if ( ae.getSource() == back )
		{
			redirectToLogin();
		}
		
	}
	
	
	 private void redirectToLogin() {
	        setVisible(false); 
	        new Login(); 
	    }
	
	
	 private void handleCheck() {
		    String UserName = tfusername.getText().trim();
		    String secAns = tfsecAns.getText().trim();
		    String secQues = (String)jcsecQues.getSelectedItem();
		    
		    String Role = (String) jcbrole.getSelectedItem(); 
		    // If "Faculty" is selected, get the role from jcbrole2
		    if ("Faculty".equals(Role)) {
		        Role = (String) jcbrole2.getSelectedItem();
		        if ("Select".equals(Role)) {
		            JOptionPane.showMessageDialog(this, "Please select a sub-role for Faculty.");
		            return; // Stop further execution if no sub-role is selected
		        }
		    }
				
            if (  !( UserName.isEmpty())  && !( UserName.equals("enter username or userid") ) && !("Select".equals(Role)) && !("Select".equals(secQues)) && !( secAns.isEmpty())  && !( secAns.equals("enter Sec Ans (Case Sensitive)") ) ) 
             {   
		    SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(this, "Checking... Please Wait....");
	        });
		    
		    // String querycheck = "select * from login where role = '"+Role+"' and username= '"+UserName+"'";
               try {
               JdbcConnection con = new JdbcConnection();
             //  JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
               
               String querycheck = "select * from login where role = ? and username= ? and sec_ques = ? and sec_ans = ?";
               
               PreparedStatement ps = con.prepareStatement(querycheck);
               ps.setString(1, Role);
               ps.setString(2, UserName);
               ps.setString(3, secQues);
               ps.setString(4, secAns);
              // System.out.println("Executing query: " + querycheck);
               
            // Execute query
               ResultSet rs = ps.executeQuery();
               //JOptionPane.showMessageDialog(this, "Executing Query : "+querycheck);  
              // JOptionPane.showMessageDialog(this, rs);
               //JOptionPane.showMessageDialog(this, "RS Value : "+rs.next());          // getting rs value as false
              // JOptionPane.showMessageDialog(this, rs.isBeforeFirst()); 
                
             if(rs.next() )
             {               	
     			JOptionPane.showMessageDialog(this, " Verification Successful !! , User Exists ");
     			enablePasswordFields();        			                   
             } 
             else 
             {
             	JOptionPane.showMessageDialog(this, " Verification Failed !! , User not found or Wrong Details!!");
 				JOptionPane.showMessageDialog(this, " Enter Again  ");
 				
 				  resetUI();
             }
             
             } 
               catch (Exception e) 
             {
                  JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage());
              }
            }
              else
	             {
		             JOptionPane.showMessageDialog(this, "Enter All Details First ");
		             return;
	             }  
 
          }
	 
	 
	 private void handleUpdate() {
		    String userName = tfusername.getText().trim();
			String passWord = tfpassword1.getText().trim();
			String passWord2 = tfpassword2.getText().trim();
			String secAns = tfsecAns.getText().trim();
		    String secQues = (String)jcsecQues.getSelectedItem();
			
			String Role = (String) jcbrole.getSelectedItem(); 
			if ("Faculty".equals(Role)) {
		        Role = (String) jcbrole2.getSelectedItem();
		    }
			
			if ( passWord.equals(passWord2) && !(passWord.isBlank() ) && !(passWord2.isBlank() ) && !(passWord.equals("enter password") ) )
			{
				SwingUtilities.invokeLater(() -> {
		            JOptionPane.showMessageDialog(this, "Updating... Please Wait....");
		        });
				
//				 String queryupdate = "update login set password = '"+passWord+"' where role = '"+Role+"' and username = '"+userName+"'";
//				 JOptionPane.showMessageDialog(this, queryupdate);   
				 
		            try {
		                  JdbcConnection con = new JdbcConnection();
		                  //JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
		                  
		                  String queryupdate = "update login set password = ? where role = ? and username = ? and sec_ques = ? and sec_ans = ?";
		                  
		                  PreparedStatement ps = con.prepareStatement(queryupdate);
		                  ps.setString(1, passWord);
		                  ps.setString(2, Role);
		                  ps.setString(3, userName);
		                  ps.setString(4, secQues);
		                  ps.setString(5, secAns);
		                  // JOptionPane.showMessageDialog(this, "Executing Query : "+queryupdate);   
		                  
		                  int flag = ps.executeUpdate();
		    		     // ps.close();
		                
		    		      if( flag >0 )
		    				{
		    		    	  JOptionPane.showMessageDialog(this, "Password Updated Successfully.");
		    		    	  resetUI();
		    				}
		    				else
		    				{
		    					JOptionPane.showMessageDialog(this, "Password Updation Failed !");
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
				JOptionPane.showMessageDialog(this, "Password not Matches or is Empty ");
			}  
	    }
	 
	
	 private void hideButton() {
		    tfpassword1.setEnabled(false);
		    tfpassword2.setEnabled(false);
		    bupdate.setVisible(false);
		    breset.setVisible(false);
		 
		 //  lb6.disable();
			//lb7.disable();
	        
//	        jcbrole.disable();
//			jcbrole2.disable();
//			tfusername.disable();
//	        bcheck.hide(); 
	    }
	 
	private void resetUI() {
	    tfusername.setText("enter username or userid");
	    tfsecAns.setText("enter Sec Ans (Case Sensitive)");
	    jcsecQues.setSelectedIndex(0);
	    jcbrole.setSelectedIndex(0);
	    jcbrole2.setSelectedIndex(0);
	    jcbrole2.setEnabled(false);
	    tfpassword1.setText("enter password");
	    tfpassword2.setText("enter password");
	    tfpassword1.setEnabled(false);
	    tfpassword2.setEnabled(false);
	    bupdate.setVisible(false);
	    breset.setVisible(false);
	    
	    jcsecQues.setEnabled(true);
	    jcbrole.setEnabled(true);
		jcbrole2.setEnabled(true);
		tfusername.setEnabled(true);
		tfsecAns.setEnabled(true);
		
	}

	private void enablePasswordFields() {
		jcbrole.setEnabled(false);
		jcbrole2.setEnabled(false);
		jcsecQues.setEnabled(false);
		tfusername.setEnabled(false);
		tfsecAns.setEnabled(false);
	        			
		//lb6.enable();
		//lb7.enable();
		tfpassword1.setEnabled(true);
		tfpassword2.setEnabled(true);
		bupdate.setVisible(true);
		breset.setVisible(true);
	}

	
//	public static void main (String args[])  {  new ForgetPassword() ;  }
}
