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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Operation extends JFrame implements ActionListener {

	JButton bnext, bclear;
    JTextField tfdb, tftable;
    String userName;
    String passWord;
	
	public Operation(String userName, String passWord, String dbName, String tableName)
	{
		this.userName = userName;
		this.passWord = passWord;
		
		JLabel lb1,lb2,lb3,lb4,lb5,lb6;
		
		setTitle("CRUD Operation");
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
        
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("images/login3.jpg"));
        Image img2 = img.getImage().getScaledInstance(290, 350, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        lb3 = new JLabel(img3);
        lb3.setBounds(530, 70, 290, 350);
        add(lb3);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/main.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(850, 670, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        
        JMenu cr = new JMenu("Create");
        cr.setForeground(Color.BLUE);
        mb.add(cr);
        
        JMenuItem crdb = new JMenuItem("Create DataBase");
        crdb.setBackground(Color.WHITE);
        crdb.addActionListener(this);
        cr.add(crdb);
        
        JMenuItem crtb = new JMenuItem("Create Table");
        crtb.setBackground(Color.WHITE);
        crtb.addActionListener(this);
        cr.add(crtb);
        
        JMenu sh = new JMenu("Show");
        sh.setForeground(Color.RED);
        mb.add(sh);
        
        JMenuItem shdb = new JMenuItem("View Database");
        shdb.setBackground(Color.WHITE);
        shdb.addActionListener(this);
        sh.add(shdb);
        
        JMenuItem shtb = new JMenuItem("View Table");
        shtb.setBackground(Color.WHITE);
        shtb.addActionListener(this);
        sh.add(shtb);
        
        JMenu in = new JMenu("Insert");
        in.setForeground(Color.BLUE);
        mb.add(in);
        
        JMenuItem insltb = new JMenuItem("Select Table");
        insltb.setBackground(Color.WHITE);
        insltb.addActionListener(this);
        in.add(insltb);
        
               
        JMenu rtv = new JMenu("Retrieve");
        rtv.setForeground(Color.RED);
        mb.add(rtv);
        
        JMenuItem fct = new JMenuItem("From Current Table ");
        fct.setBackground(Color.WHITE);
        fct.addActionListener(this);
        rtv.add(fct);
        
        JMenuItem cdb = new JMenuItem("Change Database or Table ");
        cdb.setBackground(Color.WHITE);
        cdb.addActionListener(this);
        rtv.add(cdb);
        
        JMenu updt = new JMenu("Update");
        updt.setForeground(Color.BLUE);
        mb.add(updt);
        
        JMenuItem usltb = new JMenuItem("Select Table");
        usltb.setBackground(Color.WHITE);
        usltb.addActionListener(this);
        updt.add(usltb);
                       
        JMenu dl = new JMenu("Delete");
        dl.setForeground(Color.RED);
        mb.add(dl);
        
        JMenuItem dlitm = new JMenuItem("Delete Item");
        dlitm.setBackground(Color.WHITE);
        dlitm.addActionListener(this);
        dl.add(dlitm);
        
        JMenuItem dltb = new JMenuItem("Delete Table ");
        dltb.setBackground(Color.WHITE);
        dltb.addActionListener(this);
        dl.add(dltb);
        
        JMenuItem dldb = new JMenuItem("Delete Database ");
        dldb.setBackground(Color.WHITE);
        dldb.addActionListener(this);
        dl.add(dldb);
        
        JMenu utility = new JMenu("Utility");
        utility.setForeground(Color.RED);
        mb.add(utility);
        
        JMenuItem notepad = new JMenuItem("Notepad");
        notepad.setBackground(Color.WHITE);
        notepad.addActionListener(this);
        utility.add(notepad);
        
        JMenuItem calc = new JMenuItem("Calculator");
        calc.setBackground(Color.WHITE);
        calc.addActionListener(this);
        utility.add(calc);
        
        JMenu about = new JMenu("About");
        about.setForeground(Color.BLUE);
        mb.add(about);
        
        JMenuItem ab = new JMenuItem("About");
        ab.setBackground(Color.WHITE);
        ab.addActionListener(this);
        about.add(ab);
        
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);
        
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);
        
        setJMenuBar(mb);
        
        setSize(900, 700);
        setVisible(true);
	}
	
	public Operation() {    }
	
	@Override
	public void actionPerformed(ActionEvent ae) 
	{		
		 String msg = ae.getActionCommand();
	        
	        if (msg.equals("Exit")) 
	        {
	            setVisible(false);
	        } 
	        else if (msg.equals("Calculator")) 
	        {
	            try {
	                Runtime.getRuntime().exec("calc.exe");
	               } 
	               catch (Exception e) 
	                 {
	                
	                 }
	        } 
	        else if (msg.equals("Notepad")) 
	        {
	            try {
	                Runtime.getRuntime().exec("notepad.exe");
	                } 
	                catch (Exception e) 
	                {
	                
	                 }
	        } 
	        else if (msg.equals("Create DataBase")) 
	        {
	           // new CreateDatabase();
	        } 
	        else if (msg.equals("Create Table")) 
	        {
	           // new CreateTable();
	        } 
	        else if (msg.equals("View Database")) 
	        {
	           // new ViewDatabase();
	        } 
	        else if (msg.equals("View Table")) 
	        {
	           // new ViewTable();
	        } 
	        else if (msg.equals("Select Table")) 
	        {
	           // new SelectTable();
	        } 
	        else if (msg.equals("From Current Table"))
	        {
	           // new CurrentTable();
	        } 
	        else if (msg.equals("Change Database or Table")) 
	        {
	           // new Change();
	        } 
	        else if (msg.equals("Delete Item")) 
	        {
	           // new Delete();
	        } 
	        else if (msg.equals("Delete Table")) 
	        {
	           // new Delete();
	        	
	        } 
	        else if (msg.equals("Delete Database")) 
	        {
	           // new Delete();
	        
	        } 
	        else if (msg.equals("About")) 
	        {
	            new About();
	        } 
		
//		if(ae.getSource() == bnext)
//		{
//			String dbName = tfdb.getText();
//			String tableName = tftable.getText();
//			
//			JOptionPane.showMessageDialog(this, userName+" " +passWord+ " " +dbName+" "+tableName);
//															
//			JdbcConnection jdbc = new JdbcConnection(userName, passWord);
//			Connection con = jdbc.connect("");
//					
//			if(con != null)
//			{
//				JOptionPane.showMessageDialog(this, "  Connection Successful !! ");
//				JOptionPane.showMessageDialog(this, "  You can now Perform Database Operation : ");
//				new Page2(userName, passWord);
//				setVisible(false);
//			}
//			else
//			{
//				JOptionPane.showMessageDialog(this, "  Connection Failed !! , Wrong Credential  ");
//				JOptionPane.showMessageDialog(this, "  Enter Credential Again  ");
//								
//			}
//	
//		}
//		else if (ae.getSource() == bclear)
//		{
//			tfdb.setText("");
//			tftable.setText("");
//		
//		}
		
		
			
	}
	
		public static void main(String args[])   {   new Operation();  } 
}