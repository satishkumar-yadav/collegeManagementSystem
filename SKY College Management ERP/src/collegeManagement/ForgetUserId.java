
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

public class ForgetUserId extends JFrame implements ActionListener
{
	JButton bcheck, bcancel, bupdate, breset, back ;
	JTextField tfId, tfsecAns;
	JComboBox jcbrole, jcbrole2, jcsecQues;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8, lb9, lb10, lbUserName ;
	
	ForgetUserId()
	{
        setTitle("Forget UserId");
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
                 
        lb1 = new JLabel("Forgot UserId or UserName ??");
        lb1.setBounds(50, 40, 480, 35);
        lb1.setBackground(Color.BLACK);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(lb1);
                       
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login3.jpg"));
        Image img2 = img.getImage().getScaledInstance(140, 150, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb2 = new JLabel(img3);
        lb2.setBounds(480, 12, 140, 150);
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
        
        lb5 = new JLabel("ENTER  RollNo or Employee ID:");
        lb5.setBounds(50, 220, 350, 30);
        lb5.setForeground(Color.RED);
        lb5.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb5);
        
        tfId = new JTextField("enter your id");
        tfId.setBounds(380, 220, 240, 30);
        tfId.setFont(new Font("Arial", Font.BOLD, 15));
        tfId.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfId.getText().equals("enter your id")) {
                	tfId.setText("");
                }
            }
        });
        add(tfId);
        
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
        
        lb6 = new JLabel("Your UserName is          :");
        lb6.setBounds(50, 450, 300, 30);
        lb6.setForeground(Color.RED);
        lb6.setFont(new Font("Arial", Font.BOLD, 20));
      //  lb6.disable();
        add(lb6);
        
        lbUserName = new JLabel("");
        lbUserName.setBounds(380, 450, 240, 30);
        lbUserName.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        lbUserName.setForeground(Color.MAGENTA);
        add(lbUserName);
        
        back =new JButton("Back");
        back.setBounds(250, 490, 100, 40);
        back.setBackground(Color.blue);
        back.setForeground(Color.white);
        back.setFont(new Font("Tahoma", Font.BOLD, 15));
        back.addActionListener(this);
        add( back);
       
       
        lb8 = new JLabel("Copyright @ BYTE CODERS. All rights reserved. ");
        lb8.setBounds(50, 550, 680, 30);
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
      
        setSize(700,650);
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
		    String id = tfId.getText().trim();
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
				
            if (  !( id.isBlank())  && !( id.equals("enter your id") ) && !("Select".equals(Role)) && !("Select".equals(secQues)) && !( secAns.isEmpty())  && !( secAns.equals("enter Sec Ans ") ) ) 
             {   
		    SwingUtilities.invokeLater(() -> {
	            JOptionPane.showMessageDialog(this, "Checking... Please Wait....");
	        });
		    
		    // String querycheck = "select * from login where role = '"+Role+"' and username= '"+UserName+"'";
               try {
               JdbcConnection con = new JdbcConnection();
             //  JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
               
               String querycheck = "select username from login where role = ? and id = ? and sec_ques = ? and sec_ans = ?";
               
               PreparedStatement ps = con.prepareStatement(querycheck);
               ps.setString(1, Role);
               ps.setString(2, id);
               ps.setString(3, secQues);
               ps.setString(4, secAns);
              
               ResultSet rs = ps.executeQuery();
             if(rs.next() )
             {               	
     			JOptionPane.showMessageDialog(this, " Verification Successful !! , Below is your User Id. ");
     			String username = rs.getString("username");
     			showButton();
     			lbUserName.setText(username);
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
 
	
	 private void hideButton() {
		    back.setVisible(false);
		    lb6.setVisible(false);
		    lbUserName.setVisible(false);
	    }
	 
	 private void showButton() {
		    back.setVisible(true);
		    lb6.setVisible(true);
		    lbUserName.setVisible(true);
	    }
	 
	private void resetUI() {
	    tfId.setText("enter your id");
	    tfsecAns.setText("enter Sec Ans ");
	    jcsecQues.setSelectedIndex(0);
	    jcbrole.setSelectedIndex(0);
	    jcbrole2.setSelectedIndex(0);
	    jcbrole2.setEnabled(true);
	   
	    hideButton();
	}

	
 //	public static void main (String args[])  {  new ForgetUserId() ;  }
}
