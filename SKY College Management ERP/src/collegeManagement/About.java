package collegeManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About extends JFrame
{
   
	 public About() 
	 {      
		    setTitle("About");
		  //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setSize(700, 500);
	        setLocation(400, 150);
	        getContentPane().setBackground(Color.WHITE);
	        
	        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/about.jpg"));
	        Image i2 = i1.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
	        ImageIcon i3 = new ImageIcon(i2);
	        JLabel image = new JLabel(i3);
	        image.setBounds(350, 0, 300, 200);
	        add(image);
	        
	        JLabel heading = new JLabel("<html>ERP<br/>College Management System</html>");
	        heading.setBounds(70, 20, 300, 130);
	        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
	        add(heading);
	        
	        JLabel name = new JLabel("Developed By: < BYTE CODERS > ");
	        name.setBounds(70, 220, 550, 40);
	        name.setFont(new Font("Tahoma", Font.BOLD, 30));
	        add(name);
	        
	        JLabel member = new JLabel("Members :");
	        member.setBounds(70, 260, 550, 40);
	        member.setFont(new Font("Tahoma", Font.PLAIN, 25));
	         add(member);
	        
	        JLabel role = new JLabel("Role:                        Name:                RollNo:");
	        role.setBounds(70, 290, 550, 40);
	        role.setFont(new Font("Tahoma", Font.PLAIN, 23));
	         add(role);
	        
	        JLabel satish = new JLabel("Lead,                    Satish Kumar Yadav,     '201025' ");
	        satish.setBounds(70, 320, 550, 40);
	        satish.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         add(satish);
	        
	        JLabel ali = new JLabel("PPT Creation,           Mohhamad Ali,          '211013' ");
	        ali.setBounds(70, 345, 550, 40);
	        ali.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         add(ali);
	        
	        JLabel ajeet = new JLabel("Gather Information,  Ajeet Kumar,            '211065' ");
	        ajeet.setBounds(70, 365, 550, 40);
	        ajeet.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         add(ajeet);
	        
	        JLabel karmjeet = new JLabel("Assisted,                  Karmjeet Singh,        '211062' ");
	        karmjeet.setBounds(70, 385, 550, 40);
	        karmjeet.setFont(new Font("Tahoma", Font.PLAIN, 20));
	         add(karmjeet);
	        
	        JLabel contact = new JLabel("Contact: satishkumar.yadav.7549@gmail.com");
	        contact.setBounds(70, 425, 550, 40);
	        contact.setFont(new Font("Tahoma", Font.PLAIN, 20));
	        add(contact);
	        
	        setLayout(null);
	        
	        setVisible(true);
	    }
	    
	 //   public static void main(String[] args) { new About();  }
	
}