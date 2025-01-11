package collegeManagement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



public class Teacher extends JFrame implements ActionListener {

	String empid = "";
	JLabel picLabel, labelname;
	FileInputStream fis;
    InputStream is;
    File selectedFile ;
    ImageIcon icon = null ;
	
	Teacher(String empid) {
	   this.empid = empid;
		
   	   setSize(1200, 700);
   	
       setTitle("Teacher");
     //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
              
       picLabel = new JLabel("      No Picture Available  ");
       picLabel.setBounds(910, 50, 150, 135);
       picLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
       add(picLabel);
       
       JLabel lblname = new JLabel("Welcome,");
       lblname.setBounds(830, 20, 100, 30);
       lblname.setFont(new Font("serif", Font.BOLD, 20));
       add(lblname);
       
       labelname = new JLabel("...");
       labelname.setBounds(920, 20, 500, 30);
       labelname.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(labelname);
       
     // Background Image
       ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/third.jpg"));
       Image i2 = i1.getImage().getScaledInstance(1350, 660, Image.SCALE_DEFAULT);
       ImageIcon i3 = new ImageIcon(i2);
       JLabel image = new JLabel(i3);
       add(image);
     
    // Menu Bar
       JMenuBar mb = new JMenuBar();
                
       // Details
       JMenu homeMenu = new JMenu("Home");
       homeMenu.setForeground(Color.RED);
       mb.add(homeMenu);        
        
       JMenuItem home = new JMenuItem("Home");
       home.setBackground(Color.WHITE);
       home.addActionListener(this);
       homeMenu.add(home);
       
       JMenu profile = new JMenu("Profile");
       profile.setForeground(Color.BLUE);
       mb.add(profile);
       
       JMenuItem profileView = new JMenuItem("View Profile");
       profileView.setBackground(Color.WHITE);
       profileView.addActionListener(this);
       profile.add(profileView);
       
       JMenuItem profileUpdate = new JMenuItem("Update Profile");
       profileUpdate.setBackground(Color.WHITE);
       profileUpdate.addActionListener(this);
       profile.add(profileUpdate);
       
       // Leave Details
       JMenu leaveDetails = new JMenu("Leave");
       leaveDetails.setForeground(Color.RED);
       mb.add(leaveDetails);
       
       JMenuItem applyLeave = new JMenuItem("Apply Leave");
       applyLeave.setBackground(Color.WHITE);
       applyLeave.addActionListener(this);
       leaveDetails.add(applyLeave);
       
       JMenuItem appliedLeaveDetails = new JMenuItem("Applied Leave Details");
       appliedLeaveDetails.setBackground(Color.WHITE);
       appliedLeaveDetails.addActionListener(this);
       leaveDetails.add(appliedLeaveDetails);
       
       // Exams
       JMenu exam = new JMenu("Examination");
       exam.setForeground(Color.BLUE);
       mb.add(exam);
       
       JMenuItem examinationResults = new JMenuItem("Examination Results");
       examinationResults.setBackground(Color.WHITE);
       examinationResults.addActionListener(this);
       exam.add(examinationResults);
       
       JMenuItem addMarks = new JMenuItem("Add Student Marks");
       addMarks.setBackground(Color.WHITE);
       addMarks.addActionListener(this);
       exam.add(addMarks);
       
       JMenuItem updateMarks = new JMenuItem("Update Student Marks");
       updateMarks.setBackground(Color.WHITE);
       updateMarks.addActionListener(this);
       exam.add(updateMarks);
              
       // fee
       JMenu salary = new JMenu("Salary");
       salary.setForeground(Color.RED);
       mb.add(salary);
       
       JMenuItem salarySlip = new JMenuItem("Salary Slip");
       salarySlip.setBackground(Color.WHITE);
       salarySlip.addActionListener(this);
       salary.add(salarySlip);
       
       JMenuItem duesSalary = new JMenuItem("Salary Dues");
       duesSalary.setBackground(Color.WHITE);
       duesSalary.addActionListener(this);
       salary.add(duesSalary);
       
       JMenu studyMaterial = new JMenu("Study Material");
       studyMaterial.setForeground(Color.BLUE);
       mb.add(studyMaterial);
       
       JMenuItem recordedLecture = new JMenuItem("Add Recorded Lecture");
       recordedLecture.setBackground(Color.WHITE);
       recordedLecture.addActionListener(this);
       studyMaterial.add(recordedLecture);
       
       JMenuItem notes = new JMenuItem("Add Notes");
       notes.setBackground(Color.WHITE);
       notes.addActionListener(this);
       studyMaterial.add(notes);
       
       JMenu liveClassMenu = new JMenu("Live Classes");
       liveClassMenu.setForeground(Color.RED);
       mb.add(liveClassMenu); 
       
       JMenuItem liveClass = new JMenuItem("Schedule Live Class");
       liveClass.setBackground(Color.WHITE);
       liveClass.addActionListener(this);
       liveClassMenu.add(liveClass);
       
       JMenu attendence = new JMenu("Attendence");
       attendence.setForeground(Color.BLUE);
       mb.add(attendence); 
       
       JMenuItem attendenceView = new JMenuItem("View Attendence");
       attendenceView.setBackground(Color.WHITE);
       attendenceView.addActionListener(this);
       attendence.add(attendenceView);
       
       JMenu notice = new JMenu("Notice");
       notice.setForeground(Color.RED);
       mb.add(notice);        
       
       JMenuItem showNotice = new JMenuItem("View Notice");
       showNotice.setBackground(Color.WHITE);
       showNotice.addActionListener(this);
       notice.add(showNotice);
       
       // Utility
       JMenu utility = new JMenu("Utility");
       utility.setForeground(Color.BLUE);
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
       JMenu about = new JMenu("Logout");
       about.setForeground(Color.RED);
       mb.add(about);
       
       JMenuItem ab = new JMenuItem("Logout");
       ab.setBackground(Color.WHITE);
       ab.addActionListener(this);
       about.add(ab);
              
       setJMenuBar(mb);
       
       loadTeacherDetails();
       
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
       
        if (msg.equals("Calculator")) {
           try {
               Runtime.getRuntime().exec("calc.exe");
           } catch (Exception e) {
               
           }
       } else if (msg.equals("Notepad")) {
           try {
               Runtime.getRuntime().exec("notepad.exe");
           } catch (Exception e) {
               
           }
       }  else if (msg.equals("Home")) {
    	   JOptionPane.showMessageDialog(this, "You are Already on Homepage."); 
          // new Teacher(empid);
       }  else if (msg.equals("View Profile")) {
    	 //  JOptionPane.showMessageDialog(this, "Viewing Teacher Profile."); 
           new ViewTeacherProfile(empid);
       }  else if (msg.equals("Update Profile")) {
    	  // JOptionPane.showMessageDialog(this, "Updating Teacher Profile."); 
           new UpdateProfileTeacher(empid);
       }  else if (msg.equals("Apply Leave")) {
    	   JOptionPane.showMessageDialog(this, "Applying for Leave."); 
          // new ApplyLeaveTeacher(empid);
       }  else if (msg.equals("Applied Leave Details")) {
    	   JOptionPane.showMessageDialog(this, "Applied Leave Details : "); 
          // new AppliedLeaveTeacher(empid);
       } else if (msg.equals("Examination Results")) {
    	   JOptionPane.showMessageDialog(this, "Showing Examination Results."); 
          // new ExaminationResult(rollno);
       }else if (msg.equals("Add Student Marks")) {
    	   JOptionPane.showMessageDialog(this, "Adding Student Marks."); 
           // new AddMarks(empid);
        }
       else if (msg.equals("Update Student Marks")) {
    	   JOptionPane.showMessageDialog(this, "Updating Student Marks."); 
          // new UpdateMarks(empid);
       }
       else if (msg.equals("Logout")) {
           new Login();
           setVisible(false);
       } else if (msg.equals("Salary Slip")) {
    	   JOptionPane.showMessageDialog(this, "Showing Salary Slip."); 
          // new SalarySlip(empid);
       }
       else if (msg.equals("Salary Dues")) {
    	   JOptionPane.showMessageDialog(this, "Showing Salary Dues."); 
          // new SalaryDues(empid);
       }
       else if (msg.equals("Add Recorded Lecture")) {
    	   JOptionPane.showMessageDialog(this, "Adding Recorded Lectures."); 
          // new AddRecordrdLecture(empid);
       }
       else if (msg.equals("Add Notes")) {
    	   JOptionPane.showMessageDialog(this, "Adding Notes."); 
          // new AddNotes();
       }
       else if (msg.equals("Schedule Live Class")) {
    	   JOptionPane.showMessageDialog(this, " Scheduling Live Classes."); 
          // new ScheduleLiveClass(empid);
       }
       else if (msg.equals("View Attendence")) {
    	   JOptionPane.showMessageDialog(this, "Showing Teacher Attendence."); 
          // new ViewAttendenceTeacher(empid);
       }
       else if (msg.equals("View Notice")) {
    	   JOptionPane.showMessageDialog(this, "Showing Notice."); 
          // new Notice();
       }
       
   }

   
   private void loadTeacherDetails() {
	   
	   try {        // load student name and pic by default
	       	JdbcConnection cs = new JdbcConnection();
	           String querys = "select name, pic from teacher where empid= ? ";
	           PreparedStatement pst = cs.prepareStatement(querys);
	           pst.setString(1, empid);
	           
	           ResultSet rss = pst.executeQuery();
	           while(rss.next()) 
	           {
	               labelname.setText(rss.getString("name"));
	               
	               Blob b = rss.getBlob("pic");
	               if (b != null)
	               {
	            	   is = b.getBinaryStream();
		               BufferedImage pic = ImageIO.read(is); // Correct usage of ImageIO.read()
		               Image scaledPic = pic.getScaledInstance(140, 130, Image.SCALE_SMOOTH);
		               icon = new ImageIcon(scaledPic);
		               picLabel.setIcon(icon);  
	               }
	               else {
	            	   picLabel.setText("No Picture Available");
	               }
	                                      
	           }
	           
	        // Close resources
	            rss.close();
	            pst.close();
	            cs.close();
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	            JOptionPane.showMessageDialog(this, "Error loading Teacher details.");

	       }
	   
   }
   
   
    //  public static void main(String[] args) { new Teacher();  }
	
}
