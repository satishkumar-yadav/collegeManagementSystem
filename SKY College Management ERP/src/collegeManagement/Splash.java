package collegeManagement;

import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame implements Runnable {
    
    Thread t;
    Splash () {
        
    	JLabel lb0,lb1,lb2,lb3,lb4,lb5;
    	
    	setTitle("Wecome to ERP");
    	//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	
    	
    	 lb1 = new JLabel("Welcome !! to ERP ");
         lb1.setBounds(250, 15, 350, 40);
         lb1.setBackground(Color.BLACK);
         lb1.setForeground(Color.WHITE);
         lb1.setFont(new Font("Tahoma", Font.BOLD, 35));
         add(lb1);
         
         lb2 = new JLabel("College Management System ");
         lb2.setBounds(120, 50, 550, 40);
         lb2.setBackground(Color.BLACK);
         lb2.setForeground(Color.WHITE);
         lb2.setFont(new Font("Tahoma", Font.BOLD, 35));
         add(lb2);
         
         lb3 = new JLabel("Designed and Developed : ");
         lb3.setBounds(350, 100, 285, 30);
         lb3.setBackground(Color.BLACK);
         lb3.setForeground(Color.RED);
         lb3.setFont(new Font("Serif", Font.BOLD, 25));
         add(lb3);
         
         lb4 = new JLabel(" By < BYTE CODERS & Group >");
         lb4.setBounds(605, 122, 500, 30);
         lb4.setBackground(Color.BLACK);
         lb4.setForeground(Color.RED);
         lb4.setFont(new Font("Serif", Font.BOLD, 22));
         add(lb4);
         
         lb5 = new JLabel("Copyright @ BYTE CODERS. All rights reserved. ");
         lb5.setBounds(150, 570, 680, 30);
         lb5.setForeground(Color.WHITE);
        // lb7.setBackground(Color.WHITE);
         lb5.setFont(new Font("Tahoma", Font.BOLD, 28));
       //  add(lb5);
    	
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/1.png"));
        Image i2 = i1.getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        t = new Thread(this);
        t.start();
        
        setVisible(true);
        
        int x = 1;
        for (int i = 2; i <= 600; i+=4, x+=1) {
            setLocation(600 - ((i + x)/2), 350 - (i/2));
            setSize(i + 3*x, i + x/2);
            
            try {
                Thread.sleep(10);  // CONTROL SPEEED
            } catch (Exception e) {}
        }        
    }
    
    public void run() {
        try {
            Thread.sleep(5500);
            setVisible(false);
            
            // Next Frame
            new Login();
        } catch (Exception e) {
            
        }
    }
    
    public static void main(String[] args) {
        new Splash();
    }
}
