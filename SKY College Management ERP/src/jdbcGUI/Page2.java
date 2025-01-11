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


public class Page2 extends JFrame implements ActionListener {

	JButton bnext, bclear;
    JTextField tfdb, tftable;
    String userName = "";
    String passWord = "";
	
	public Page2(String userName, String passWord)
	{
		if(userName.isBlank() && passWord.isBlank())
		{
			 this.userName = "root";
			 this.passWord = "12345678";
		}
		else
		{
			this.userName = userName;
			this.passWord = passWord;
		}
		
		
		JLabel lb1,lb2,lb3,lb4,lb5,lb6;
		
		setTitle("Select Database");
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
                        
        lb4 = new JLabel("Select Database or Table :");
        lb4.setBounds(60, 160, 380, 20);
        lb4.setForeground(Color.GREEN);
        lb4.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(lb4);
        
        lb5 = new JLabel("Enter DataBase Name :");
        lb5.setBounds(60, 210, 270, 20);
        lb5.setForeground(Color.PINK);
        lb5.setFont(new Font("Arial", Font.BOLD, 17));
        add(lb5);
        
        tfdb = new JTextField(" DataBase Name ");
        tfdb.setBounds(340, 205, 200, 30);
        tfdb.setFont(new Font("Arial", Font.BOLD, 15));
        add(tfdb);
        
        lb6 = new JLabel("Enter Table Name : ");
        lb6.setBounds(60, 260, 270, 20);
        lb6.setForeground(Color.PINK);
        lb6.setFont(new Font("Arial", Font.BOLD, 17));
        add(lb6);
        
        tftable = new JTextField(" Table Name ");
        tftable.setBounds(340, 255, 200, 30);
        tftable.setFont(new Font("AvanteGarde", Font.BOLD, 15));
        add(tftable);
        
        bnext =new JButton("Next");
        bnext.setBounds(60, 320, 100, 40);
        bnext.setBackground(Color.BLACK);
        bnext.setForeground(Color.WHITE);
        bnext.setFont(new Font("Tahoma", Font.BOLD, 15));
        bnext.addActionListener(this);
        add(bnext);
        
        bclear =new JButton("Clear");
        bclear.setBounds(420, 320, 100, 40);
        bclear.setBackground(Color.BLACK);
        bclear.setForeground(Color.WHITE);
        bclear.setFont(new Font("Tahoma", Font.BOLD, 15));
        bclear.addActionListener(this);
        add(bclear);
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login2.jpg"));
        Image img2 = img.getImage().getScaledInstance(220, 280, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb3 = new JLabel(img3);
        lb3.setBounds(550, 80, 220, 280);
        add(lb3);
        
        setSize(840,470);
        setLocation(300,150);
        setVisible(true);
	}
	
	public Page2() {    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == bnext)
		{
			String dbName = tfdb.getText();
			String tableName = tftable.getText();
			
			JOptionPane.showMessageDialog(this, userName+" " +passWord+ " " +dbName+" "+tableName);
															
			JdbcConnection jdbc = new JdbcConnection(userName, passWord);
			Connection con = jdbc.connect("");
					
			if(con != null)
			{
				JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
				JOptionPane.showMessageDialog(this, "  You can now Perform Database Operation : ");
				new Operation(userName, passWord, dbName, tableName);
				setVisible(false);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "  Connection Failed !! , Wrong Credential  ");
				JOptionPane.showMessageDialog(this, "  Enter Credential Again  ");
								
			}
	
		}
		else if (e.getSource() == bclear)
		{
			tfdb.setText("");
			tftable.setText("");
		
		}
			
	}
	
	
	public static void main(String[] args) { new Page2(); }
		
}
















