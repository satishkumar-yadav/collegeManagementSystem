package jdbcGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class LoginDB extends JFrame implements ActionListener {

	JButton blogin, bcancel;
    JTextField tfusername, tfpassword;
	
	public LoginDB()
	{
		JLabel lb1,lb2,lb3,lb4,lb5,lb6;
		
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        lb1 = new JLabel("Welcome !! to CRUD Operation");
        lb1.setBounds(120, 15, 600, 60);
        lb1.setBackground(Color.BLACK);
        lb1.setForeground(Color.BLUE);
        lb1.setFont(new Font("Tahoma", Font.BOLD, 35));
        add(lb1);
        
        lb2 = new JLabel("Designed By <Tech Titans & Group>");
        lb2.setBounds(150, 80, 500, 40);
        lb2.setBackground(Color.BLACK);
        lb2.setForeground(Color.RED);
        lb2.setFont(new Font("Serif", Font.BOLD, 25));
        add(lb2);
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login.jpg"));
        Image img2 = img.getImage().getScaledInstance(290, 350, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb3 = new JLabel(img3);
        lb3.setBounds(530, 70, 290, 350);
        add(lb3);
        
        lb4 = new JLabel("Enter Database Credentials First :");
        lb4.setBounds(60, 160, 380, 20);
        lb4.setForeground(Color.GREEN);
        lb4.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb4);
        
        lb5 = new JLabel("Enter Username for DataBase :");
        lb5.setBounds(60, 210, 270, 20);
        lb5.setForeground(Color.PINK);
        lb5.setFont(new Font("Arial", Font.BOLD, 17));
        add(lb5);
        
        tfusername = new JTextField("Enter username or userid");
        tfusername.setBounds(340, 205, 200, 30);
        tfusername.setFont(new Font("Arial", Font.BOLD, 15));
        add(tfusername);
        
        lb6 = new JLabel("Enter Password for DataBase : ");
        lb6.setBounds(60, 260, 270, 20);
        lb6.setForeground(Color.PINK);
        lb6.setFont(new Font("Arial", Font.BOLD, 17));
        add(lb6);
        
        tfpassword = new JTextField("enter password");
        tfpassword.setBounds(340, 255, 200, 30);
        tfpassword.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        add(tfpassword);
        
        blogin =new JButton("Login");
        blogin.setBounds(60, 320, 100, 40);
        blogin.setBackground(Color.BLACK);
        blogin.setForeground(Color.WHITE);
        blogin.setFont(new Font("Tahoma", Font.BOLD, 15));
        blogin.addActionListener(this);
        add(blogin);
        
        bcancel =new JButton("Cancel");
        bcancel.setBounds(420, 320, 100, 40);
        bcancel.setBackground(Color.BLACK);
        bcancel.setForeground(Color.WHITE);
        bcancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        bcancel.addActionListener(this);
        add(bcancel);
        
        setSize(840,470);
        setLocation(300,150);
        setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == blogin)
		{
		//  boolean connected = false;
//          Connection con = null;
//		    String userName = null;
//		    String passWord = null;
	
			String userName = tfusername.getText();
			String passWord = tfpassword.getText();
									
			JdbcConnection jdbc = new JdbcConnection(userName, passWord);
			Connection con = jdbc.connect("");
					
			if(con != null)
			{
				//connected = true;
				JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
				JOptionPane.showMessageDialog(this, "  You can now Perform Database Operation : ");
				new Page2(userName, passWord);
				setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "  Connection Failed !! , Wrong Credential  ");
				JOptionPane.showMessageDialog(this, "  Enter Credential Again  ");
							
			}
	
		}
		else if (e.getSource() == bcancel)
		{
			tfusername.setText("");
			tfpassword.setText("");
		
		}
			
	}
	
	
	public static void main(String[] args) {  new LoginDB();  }

}
















