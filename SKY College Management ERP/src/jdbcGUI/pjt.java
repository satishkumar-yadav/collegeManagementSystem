package jdbcGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class pjt extends JFrame implements ActionListener {

    pjt() {
        setSize(900, 700);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/main.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(880, 690, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        add(image);
        
        JMenuBar mb = new JMenuBar();
        
        // New Information
        JMenu newInformation = new JMenu("Create");
        newInformation.setForeground(Color.BLUE);
        mb.add(newInformation);
        
        JMenuItem facultyInfo = new JMenuItem("Create DataBase");
        facultyInfo.setBackground(Color.WHITE);
        facultyInfo.addActionListener(this);
        newInformation.add(facultyInfo);
        
        JMenuItem studentInfo = new JMenuItem("Create Table");
        studentInfo.setBackground(Color.WHITE);
        studentInfo.addActionListener(this);
        newInformation.add(studentInfo);
        
        // Details
        JMenu details = new JMenu("Select");
        details.setForeground(Color.RED);
        mb.add(details);
        
        JMenuItem facultydetails = new JMenuItem("View Database");
        facultydetails.setBackground(Color.WHITE);
        facultydetails.addActionListener(this);
        details.add(facultydetails);
        
        JMenuItem studentdetails = new JMenuItem("View Table");
        studentdetails.setBackground(Color.WHITE);
        studentdetails.addActionListener(this);
        details.add(studentdetails);
        
        // Leave
        JMenu leave = new JMenu("Insert");
        leave.setForeground(Color.BLUE);
        mb.add(leave);
        
        JMenuItem facultyleave = new JMenuItem("Select Table");
        facultyleave.setBackground(Color.WHITE);
        facultyleave.addActionListener(this);
        leave.add(facultyleave);
        
        JMenuItem studentleave = new JMenuItem("Insert Item");
        studentleave.setBackground(Color.WHITE);
        studentleave.addActionListener(this);
        leave.add(studentleave);
        
        // Leave Details
        JMenu leaveDetails = new JMenu("Retrieve");
        leaveDetails.setForeground(Color.RED);
        mb.add(leaveDetails);
        
        JMenuItem facultyleavedetails = new JMenuItem("Select Table");
        facultyleavedetails.setBackground(Color.WHITE);
        facultyleavedetails.addActionListener(this);
        leaveDetails.add(facultyleavedetails);
        
        JMenuItem studentleavedetails = new JMenuItem("Show Details");
        studentleavedetails.setBackground(Color.WHITE);
        studentleavedetails.addActionListener(this);
        leaveDetails.add(studentleavedetails);
        
        // Exams
        JMenu exam = new JMenu("Update");
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
        JMenu updateInfo = new JMenu("Delete");
        updateInfo.setForeground(Color.RED);
        mb.add(updateInfo);
        
        JMenuItem updatefacultyinfo = new JMenuItem("Update Faculty Details");
        updatefacultyinfo.setBackground(Color.WHITE);
        updatefacultyinfo.addActionListener(this);
        updateInfo.add(updatefacultyinfo);
        
        JMenuItem updatestudentinfo = new JMenuItem("Update Student Details");
        updatestudentinfo.setBackground(Color.WHITE);
        updatestudentinfo.addActionListener(this);
        updateInfo.add(updatestudentinfo);
        
               
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
        JMenu exit = new JMenu("Exit");
        exit.setForeground(Color.RED);
        mb.add(exit);
        
        JMenuItem ex = new JMenuItem("Exit");
        ex.setBackground(Color.WHITE);
        ex.addActionListener(this);
        exit.add(ex);
        
        setJMenuBar(mb);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
   //     String msg = ae.getActionCommand();
        
//        if (msg.equals("Exit")) {
//            setVisible(false);
//        } else if (msg.equals("Calculator")) {
//            try {
//                Runtime.getRuntime().exec("calc.exe");
//            } catch (Exception e) {
//                
//            }
//        } else if (msg.equals("Notepad")) {
//            try {
//                Runtime.getRuntime().exec("notepad.exe");
//            } catch (Exception e) {
//                
//            }
//        } else if (msg.equals("New Faculty Information")) {
//            new AddTeacher();
//        } else if (msg.equals("New Student Information")) {
//            new AddStudent();
//        } else if (msg.equals("View Faculty Details")) {
//            new TeacherDetails();
//        } else if (msg.equals("View Student Details")) {
//            new StudentDetails();
//        } else if (msg.equals("Faculty Leave")) {
//            new TeacherLeave();
//        } else if (msg.equals("Student Leave")) {
//            new StudentLeave();
//        } else if (msg.equals("Faculty Leave Details")) {
//            new TeacherLeaveDetails();
//        } else if (msg.equals("Student Leave Details")) {
//            new StudentLeaveDetails();
//        } else if (msg.equals("Update Faculty Details")) {
//            new UpdateTeacher();
//        } else if (msg.equals("Update Student Details")) {
//            new UpdateStudent();
//        } else if (msg.equals("Enter Marks")) {
//            new EnterMarks();
//        } else if (msg.equals("Examination Results")) {
//            new ExaminationDetails();
//        } else if (msg.equals("Fee Structure")) {
//            new FeeStructure();
//        } else if (msg.equals("About")) {
//            new About();
//        } else if (msg.equals("Student Fee Form")) {
//            new StudentFeeForm();
//        }
    }

    public static void main(String[] args) {
        new pjt();
    }
}