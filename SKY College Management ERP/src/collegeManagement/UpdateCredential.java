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
import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import temp.AddNewStudent;
import temp.AddNewTeacher;

public class UpdateCredential extends JFrame implements ActionListener
{
	JButton bcheck, bcancel, bupdate, breset, back ;
	JTextField tfusername, tfsecAns;
	JPasswordField tfpassword1, tfpassword2;
	JComboBox jcbrole, jcbrole2, jcsecQues;
	JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8, lb9, lb10, lbrole, lbroll ;
	String role = "";
	String id = "";
	
	UpdateCredential(String Role, String Id)
	{
		role = Role;
		id = Id;
		
        setTitle("Update Credential");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
                 
        lb1 = new JLabel("Update Credential ");
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
        
        lb3 = new JLabel("Check Your Credential :");
        lb3.setBounds(50, 140, 400, 35);
        lb3.setForeground(Color.ORANGE);
        lb3.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(lb3);
        
        lb3 = new JLabel(" Your Role            :");
        lb3.setBounds(50, 180, 400, 30);
        lb3.setForeground(Color.red);
        lb3.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb3);
        
        lbrole = new JLabel(role);
        lbrole.setBounds(380, 180, 400, 30);
        lbrole.setForeground(Color.magenta);
        lbrole.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(lbrole);
        
        lb3 = new JLabel(" Your Roll No / ID        :");
        lb3.setBounds(50, 220, 400, 30);
        lb3.setForeground(Color.red);
        lb3.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb3);
        
        lbroll = new JLabel(id);
        lbroll.setBounds(380, 220, 400, 30);
        lbroll.setForeground(Color.magenta);
        lbroll.setFont(new Font("Tahoma", Font.BOLD, 25));
        add(lbroll);
        
        lb5 = new JLabel("ENTER  USERNAME           :");
        lb5.setBounds(50, 260, 300, 30);
        lb5.setForeground(Color.RED);
        lb5.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb5);
        
        tfusername = new JTextField("enter username or userid");
        tfusername.setBounds(380, 260, 240, 30);
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
        lb9.setBounds(50, 300, 300, 30);
        lb9.setForeground(Color.RED);
        lb9.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb9);
        
        String secQues[] = {"Select","Pet Name","Favourite Food","Favourite Place", "Place of Birth", "First School Attended" };
        
        jcsecQues = new JComboBox(secQues);
        jcsecQues.setBounds(380,300,240,30);
        add(jcsecQues);
        
        lb10 = new JLabel("Enter Security Answer        :");
        lb10.setBounds(50, 340, 300, 30);
        lb10.setForeground(Color.RED);
        lb10.setFont(new Font("Arial", Font.BOLD, 20));
        add(lb10);
        
        tfsecAns = new JTextField("enter Sec Ans ");
        tfsecAns.setBounds(380, 340, 240, 30);
        tfsecAns.setFont(new Font("Arial", Font.BOLD, 15));
        tfsecAns.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (tfsecAns.getText().equals("enter Sec Ans ")) {
                	tfsecAns.setText("");
                }
            }
        });
        add(tfsecAns);
        
        lb6 = new JLabel("Enter New Password          :");
        lb6.setBounds(50, 380, 300, 30);
        lb6.setForeground(Color.RED);
        lb6.setFont(new Font("Arial", Font.BOLD, 20));
      //  lb6.disable();
        add(lb6);
        
        tfpassword1 = new JPasswordField("enter password");
        tfpassword1.setBounds(380, 380, 240, 30);
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
        lb7.setBounds(50, 420, 300, 30);
        lb7.setForeground(Color.RED);
        lb7.setFont(new Font("Arial", Font.BOLD, 20));
       // lb7.disable();
        add(lb7);
        
        tfpassword2 = new JPasswordField("enter password");
        tfpassword2.setBounds(380, 420, 240, 30);
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
        breset.setBounds(520, 480, 100, 40);
        breset.setBackground(Color.orange);
        breset.setForeground(Color.WHITE);
        breset.setFont(new Font("Tahoma", Font.BOLD, 15));
        breset.addActionListener(this);
        add(breset);
        
        bupdate =new JButton("Update");
        bupdate.setBounds(280, 480, 100, 40);
        bupdate.setBackground(Color.yellow);
        bupdate.setForeground(Color.BLACK);
        bupdate.setFont(new Font("Tahoma", Font.BOLD, 15));
        bupdate.addActionListener(this);
        add( bupdate);
        
        back =new JButton("Back");
        back.setBounds(50, 480, 100, 40);
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
            	redirectToPrevPage();
            }
        });
     
        loadDetails(role,id);
        
        setSize(700,650);
        setLocation(300,20);
        setVisible(true);
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		
		 if (ae.getSource() == bupdate )
		{
			handleUpdate();
		}
		else if ( ae.getSource() == breset )
		{
			loadDetails(role,id);
		}
		else if ( ae.getSource() == back )
		{
			redirectToPrevPage();
		}
		
	}
	
	
	
	 private void loadDetails(String role, String id) 
	 {     
          try {       
   	        	JdbcConnection ldc = new JdbcConnection();
   	        //	JOptionPane.showMessageDialog(this, "Connection Established. ");
   	            String lq = "select * from login where id = ? and role= ? ";
   	        	PreparedStatement lp = ldc.prepareStatement(lq);
   	        	lp.setString(1, id);
   	        	lp.setString(2, role);
   	        	
   	        	ResultSet lrs = lp.executeQuery();
   	            while(lrs.next()) {
   	            	tfusername.setText(lrs.getString("username"));
   	            	tfpassword1.setText(lrs.getString("password"));
   	            	tfpassword2.setText(lrs.getString("password"));
   	            	String secQues = lrs.getString("sec_ques");
   	            	String secAns = lrs.getString("sec_ans");
   	            	
   	            	if ( secQues.isEmpty() && secAns.isEmpty() )
   	            	{
   	            		tfsecAns.setText("");
   	   	            	jcsecQues.setSelectedIndex(0);;
   	            	}
   	            	else
   	            	{
   	            		tfsecAns.setText(secAns);
   	            		jcsecQues.setSelectedItem(secQues);
   	            	}
   	                
   	            }
   	           
   	            
   	        } catch (Exception e) {
   	            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
   	            e.printStackTrace();
   	        }
 
     }
	 
	 
	 private void handleUpdate() {
		    String userName = tfusername.getText().trim();
			String passWord = tfpassword1.getText().trim();
			String passWord2 = tfpassword2.getText().trim();
			String secAns = tfsecAns.getText().trim();
		    String secQues = (String)jcsecQues.getSelectedItem();
			String Id = id;
			String Role = role; 
			
			if ( !(secQues.equalsIgnoreCase("select")) && secAns.isBlank()   )
			{
				JOptionPane.showMessageDialog(this, "Enter Security Answer");
				return;
			}
			else if ( secQues.equalsIgnoreCase("select") && !(secAns.isBlank() )  )
			{
				JOptionPane.showMessageDialog(this, "Select Security Question");
				return ;
			}
			
			if ( secQues.equalsIgnoreCase("select")  )
			{
				secQues = "";
			}
			
			if ( passWord.equals(passWord2) && !(passWord.isBlank() ) && !(passWord2.isBlank() ) && !(passWord.equals("enter password") ) )
			{
				SwingUtilities.invokeLater(() -> {
		            JOptionPane.showMessageDialog(this, "Updating... Please Wait....");
		        });		
				 
		            try {
		                  JdbcConnection con = new JdbcConnection();
		                  //JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
		                  
		                  String queryupdate = "update login set password = ? , username = ? , sec_ques = ? , sec_ans = ? where role = ? and id = ? ";
		                  
		                  PreparedStatement ps = con.prepareStatement(queryupdate);
		                  ps.setString(1, passWord);
		                  ps.setString(2, userName);
		                  ps.setString(3, secQues);
		                  ps.setString(4, secAns);
		                  ps.setString(5, Role);
		                  ps.setString(6, Id);
		                  // JOptionPane.showMessageDialog(this, "Executing Query : "+queryupdate);   
		                  
		                  int flag = ps.executeUpdate();
		    		     // ps.close();
		                
		    		      if( flag >0 )
		    				{
		    		    	  JOptionPane.showMessageDialog(this, "Credential Updated Successfully.");
		    		    	  redirectToPrevPage();
		    				}
		    				else
		    				{
		    					JOptionPane.showMessageDialog(this, "Credential Updation Failed !");
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
				return ;
			}  
	    }
	

	 private void redirectToPrevPage() 
	 {
		 if(role.equals("Student") ) {
			  setVisible(false); 
		        new Student(role,id);
		 }
		 else if(role.equals("Teacher")){
			  setVisible(false); 
		        new Teacher(role,id);
		 }
		 else {
	        setVisible(false); 
	        new Login();
		 }	
	 }

	
 //	public static void main (String args[])  {  new UpdateCredential() ;  }
}
