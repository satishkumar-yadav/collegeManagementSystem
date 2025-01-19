package collegeManagement;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import admin.AddStudent;
import admin.AddTeacher;
import admin.StudentDetails;
import admin.StudentLeave;
import admin.StudentLeaveDetails;
import admin.TeacherDetails;
import admin.TeacherLeave;
import admin.TeacherLeaveDetails;
import admin.UpdateStudent;
import admin.UpdateTeacher;

   //  Background Image Positioning:

     // Use a JLayeredPane or JPanel with a GridBagLayout to avoid layout issues with the image and menu components.


public class Admin extends JFrame implements ActionListener {

	Admin() {
   	 setSize(1200, 600);
   	setResizable(true);   // Responsive Design:

   	
       setTitle("Admin");
       
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/third.jpg"));
       Image i2 = i1.getImage().getScaledInstance(1350, 660, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       add(image);
      
     
       JMenuBar mb = new JMenuBar();
       
       // New Information
       JMenu newInformation = new JMenu("New Information");
       newInformation.setForeground(Color.BLUE);
       mb.add(newInformation);
       
       JMenuItem facultyInfo = new JMenuItem("New Faculty Information");
       facultyInfo.setBackground(Color.WHITE);
       facultyInfo.addActionListener(this);
       newInformation.add(facultyInfo);
       
       JMenuItem teacherInfo = new JMenuItem("New Teacher Information");
       teacherInfo.setBackground(Color.WHITE);
       teacherInfo.addActionListener(this);
       newInformation.add(teacherInfo);
       
       JMenuItem studentInfo = new JMenuItem("New Student Information");
       studentInfo.setBackground(Color.WHITE);
       studentInfo.addActionListener(this);
       newInformation.add(studentInfo);
       
       JMenu approve = new JMenu("Approve");
       approve.setForeground(Color.BLUE);
       mb.add(approve);
       
       JMenuItem newFacultyInfo = new JMenuItem("Approve New Faculty Information");
       newFacultyInfo.setBackground(Color.WHITE);
       newFacultyInfo.addActionListener(this);
       approve.add(newFacultyInfo);
       
       JMenuItem newTeacherInfo = new JMenuItem("Approve New Teacher Information");
       newTeacherInfo.setBackground(Color.WHITE);
       newTeacherInfo.addActionListener(this);
       approve.add(newTeacherInfo);
       
       JMenuItem newStudentInfo = new JMenuItem("Approve New Student Information");
       newStudentInfo.setBackground(Color.WHITE);
       newStudentInfo.addActionListener(this);
       approve.add(newStudentInfo);
       
       // Details
       JMenu details = new JMenu("View Details");
       details.setForeground(Color.RED);
       mb.add(details);
       
       JMenuItem facultydetails = new JMenuItem("View Faculty Details");
       facultydetails.setBackground(Color.WHITE);
       facultydetails.addActionListener(this);
       details.add(facultydetails);
       
       JMenuItem teacherdetails = new JMenuItem("View Teacher Details");
       teacherdetails.setBackground(Color.WHITE);
       teacherdetails.addActionListener(this);
       details.add(teacherdetails);
       
       JMenuItem studentdetails = new JMenuItem("View Student Details");
       studentdetails.setBackground(Color.WHITE);
       studentdetails.addActionListener(this);
       details.add(studentdetails);
       
       // Leave
       JMenu leave = new JMenu("Apply Leave");
       leave.setForeground(Color.BLUE);
       mb.add(leave);
       
       JMenuItem facultyleave = new JMenuItem("Teacher Leave");
       facultyleave.setBackground(Color.WHITE);
       facultyleave.addActionListener(this);
       leave.add(facultyleave);
       
       JMenuItem studentleave = new JMenuItem("Student Leave");
       studentleave.setBackground(Color.WHITE);
       studentleave.addActionListener(this);
       leave.add(studentleave);
       
       // Leave Details
       JMenu leaveDetails = new JMenu("Leave Details");
       leaveDetails.setForeground(Color.RED);
       mb.add(leaveDetails);
       
       JMenuItem facultyleavedetails = new JMenuItem("Teacher Leave Details");
       facultyleavedetails.setBackground(Color.WHITE);
       facultyleavedetails.addActionListener(this);
       leaveDetails.add(facultyleavedetails);
       
       JMenuItem studentleavedetails = new JMenuItem("Student Leave Details");
       studentleavedetails.setBackground(Color.WHITE);
       studentleavedetails.addActionListener(this);
       leaveDetails.add(studentleavedetails);
       
       // Exams
       JMenu exam = new JMenu("Examination");
       exam.setForeground(Color.BLUE);
       mb.add(exam);
       
       JMenuItem examinationdetails = new JMenuItem("Examination Results");
       examinationdetails.setBackground(Color.WHITE);
       examinationdetails.addActionListener(this);
       exam.add(examinationdetails);
       
       JMenuItem entermarks = new JMenuItem("Enter Marks");
       entermarks.setBackground(Color.WHITE);
       entermarks.addActionListener(this);
       exam.add(entermarks);
       
       // UpdateInfo
       JMenu updateInfo = new JMenu("Update Details");
       updateInfo.setForeground(Color.RED);
       mb.add(updateInfo);
       
       JMenuItem updatefacultyinfo = new JMenuItem("Update Faculty Details");
       updatefacultyinfo.setBackground(Color.WHITE);
       updatefacultyinfo.addActionListener(this);
       updateInfo.add(updatefacultyinfo);
       
       JMenuItem updateteacherinfo = new JMenuItem("Update Teacher Details");
       updateteacherinfo.setBackground(Color.WHITE);
       updateteacherinfo.addActionListener(this);
       updateInfo.add(updateteacherinfo);
       
       JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
       updatestudentinfo.setBackground(Color.WHITE);
       updatestudentinfo.addActionListener(this);
       updateInfo.add(updatestudentinfo);
       
       // fee
       JMenu fee = new JMenu("Fee Details");
       fee.setForeground(Color.BLUE);
       mb.add(fee);
       
       JMenuItem feestructure = new JMenuItem("Fee Structure");
       feestructure.setBackground(Color.WHITE);
       feestructure.addActionListener(this);
       fee.add(feestructure);
       
       JMenuItem feeform = new JMenuItem("Student Fee Form");
       feeform.setBackground(Color.WHITE);
       feeform.addActionListener(this);
       fee.add(feeform);
       
       // Utility
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
       
       // about
       JMenu about = new JMenu("About");
       about.setForeground(Color.BLUE);
       mb.add(about);
       
       JMenuItem ab = new JMenuItem("About");
       ab.setBackground(Color.WHITE);
       ab.addActionListener(this);
       about.add(ab);
       
       // exit
       JMenu exit = new JMenu("Logout");
       exit.setForeground(Color.RED);
       mb.add(exit);
       
       JMenuItem ex = new JMenuItem("Logout");
       ex.setBackground(Color.WHITE);
       ex.addActionListener(this);
       exit.add(ex);
       
       setJMenuBar(mb);
       
       // Handle window close event
       addWindowListener(new WindowAdapter() {
           @Override
           public void windowClosing(WindowEvent e) {
        	   int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to Exit ?", "Confirm", JOptionPane.YES_NO_OPTION);
               if (response == JOptionPane.YES_OPTION) {
            	   dispose(); // Close the window properly
               }
               else {
                   setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Keep the window open
               }
              
           }
       });
      
       setVisible(true);
       
       
   }
   
   public void actionPerformed(ActionEvent ae) {
       String msg = ae.getActionCommand();
       
       if (msg.equals("Exit")) {
           setVisible(false);
       } else if (msg.equals("Calculator")  || msg.equals("Notepad") ) {
    	   handleUtilityActions(msg);
       } else if (msg.equals("New Faculty Information")) {
    	   JOptionPane.showMessageDialog(this, "Registering new Faculty Information.");
       } else if (msg.equals("New Teacher Information")) {
    	   new AddTeacher();
       } else if (msg.equals("New Student Information")) {
           new AddStudent();
       } else if (msg.equals("Approve New Student Information")) {
    	   JOptionPane.showMessageDialog(this, "Approving new Student Information.");
       } else if (msg.equals("Approve New Teacher Information")) {
    	   JOptionPane.showMessageDialog(this, "Approving new Teacher Information.");
       }else if (msg.equals("Approve New Faculty Information")) {
    	   JOptionPane.showMessageDialog(this, "Approving new Faculty Information."); 
       } else if (msg.equals("View Faculty Details")) {
    	   JOptionPane.showMessageDialog(this, "Showing new Faculty Details."); 
       } else if (msg.equals("View Teacher Details")) {
    	   new TeacherDetails();
       } else if (msg.equals("View Student Details")) {
           new StudentDetails();
       } else if (msg.equals("Teacher Leave")) {
           new TeacherLeave();
       } else if (msg.equals("Student Leave")) {
           new StudentLeave();
       } else if (msg.equals("Teacher Leave Details")) {
           new TeacherLeaveDetails();
       } else if (msg.equals("Student Leave Details")) {
           new StudentLeaveDetails();
       } else if (msg.equals("Update Faculty Details")) {
    	   JOptionPane.showMessageDialog(this, "Updating new Faculty Details."); 
       } else if (msg.equals("Update Teacher Details")) {
    	   new UpdateTeacher();
       } else if (msg.equals("Update Student Details")) {
           new UpdateStudent();
       } else if (msg.equals("Enter Marks")) {
          // new EnterMarks();
       } else if (msg.equals("Examination Results")) {
          // new ExaminationDetails();
       } else if (msg.equals("Fee Structure")) {
          // new FeeStructure();
       } else if (msg.equals("About")) {
           new About();
       } else if (msg.equals("Student Fee Form")) {
         //  new StudentFeeForm();
       }
       else if (msg.equals("Logout")) {
           new Login();
           setVisible(false);
       }
   }
   
   
   private void handleUtilityActions(String msg) {
	    if (msg.equals("Calculator")) {
           try {
               Runtime.getRuntime().exec("calc.exe");
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, "Error opening Calculator");

           }
       } else if (msg.equals("Notepad")) {
           try {
               Runtime.getRuntime().exec("notepad.exe");
           } catch (Exception e) {
        	    JOptionPane.showMessageDialog(null, "Error launching application: " + e.getMessage());

           }
       }
	}

   

   public static void main(String[] args) {
       new Admin();
   }
	
}
