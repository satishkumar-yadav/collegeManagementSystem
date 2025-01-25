package collegeManagement;

import java.sql.*;

import javax.swing.JOptionPane;

public class Generator {
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/collegemanagement";
//    private static final String DB_USERNAME = "root";
//    private static final String DB_PASSWORD = "12345678";

    // Method to fetch course code (overloaded for course name)
    public int[] getCode( String type, String Name1, String Name2 ) {
        String query1 = null;
        String query2 = null;
        if ("course".equalsIgnoreCase(type)) {
            query1 = "SELECT CourseCode FROM Courses WHERE Course = ? ";
            query2 = "SELECT BranchCode FROM Branches WHERE BranchName = ? ";
        } else if ("department".equalsIgnoreCase(type)) {
            query1 = "SELECT departmentCode FROM Departments WHERE department = ? ";
            query2 = "SELECT roleCode FROM Roles WHERE role = ? ";
        } 
        
        int[] code = new int[2];
    /*    try (Connection gccon = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
             PreparedStatement pst1 = gccon.prepareStatement(query1);
        	 PreparedStatement pst2 = gccon.prepareStatement(query2)	)   */
        try
        {
        	JdbcConnection gccon = new JdbcConnection();
        	PreparedStatement pst1 = gccon.prepareStatement(query1);
        	PreparedStatement pst2 = gccon.prepareStatement(query2);	
            pst1.setString(1, Name1);
            pst2.setString(1, Name2);
            ResultSet rs1 = pst1.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            if (rs1.next() && rs2.next() ) {
                code[0] = rs1.getInt(1); // Fetches the first column value
                code[1] = rs2.getInt(1);
            }
           // JOptionPane.showMessageDialog(null, "Table : "+type , "Success", JOptionPane.INFORMATION_MESSAGE);
           // JOptionPane.showMessageDialog(null, "Column Name1 : "+Name1+" , Name2 : "+Name2 , "Success", JOptionPane.INFORMATION_MESSAGE);
           // JOptionPane.showMessageDialog(null, "Query : "+query1 , "Success", JOptionPane.INFORMATION_MESSAGE);
           // JOptionPane.showMessageDialog(null, "RS1 : "+rs1.next()+" , RS2 Next : "+rs2.next() , "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return code ;
        
 }

    // Method to fetch roll number or employee ID counter
    public int getCounter( String type, String year, String courseOrDeptName, String branchOrRole ) {
        String query = null;
        if ("course".equalsIgnoreCase(type)) {
            query = "SELECT Counter FROM RollNumberCounters WHERE Year = ? AND Course = ? and BranchName = ? ";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "SELECT Counter FROM EmpIdCounters WHERE Year = ? AND department = ? and role = ? ";
        }
        int counter = 0;
        try
        {
        	JdbcConnection gccon = new JdbcConnection();
        	PreparedStatement pst = gccon.prepareStatement(query);
            pst.setString(1, year);
            pst.setString(2, courseOrDeptName);
            pst.setString(3, branchOrRole);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                counter = rs.getInt("Counter");
            }
            else {
                // If no counter exists, insert a new row with Counter = 1
                insertInitialCounter(type, year, courseOrDeptName, branchOrRole);
                counter = 1; // Set the initial counter value
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
    
    // Method to insert an initial counter
    private void insertInitialCounter(String type, String year, String courseOrDeptName, String branchOrRole) {
        String query = null;

        if ("course".equalsIgnoreCase(type)) {
            query = "INSERT INTO RollNumberCounters (Year, Course, BranchName, Counter) VALUES (?, ?, ?, 1)";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "INSERT INTO EmpIdCounters (Year, department, role, Counter) VALUES (?, ?, ?, 1)";
        }

        try {
            JdbcConnection gccon = new JdbcConnection();
            PreparedStatement pst = gccon.prepareStatement(query);

            pst.setString(1, year);
            pst.setString(2, courseOrDeptName);
            pst.setString(3, branchOrRole);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    // Method to update roll number or employee ID counter
    public void updateCounter(String Year, String courseOrDeptName, String branchOrRole, String type) {
    	int year = Integer.parseInt(Year);
        String query = null;
        if ("course".equalsIgnoreCase(type)) {
            query = "UPDATE RollNumberCounters SET Counter = Counter + 1 WHERE Year = ? AND Course = ? and BranchName = ? ";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "UPDATE EmpIdCounters SET Counter = Counter + 1 WHERE Year = ? AND department = ? and role = ? ";
        }
        try
        {
        	JdbcConnection con = new JdbcConnection();
        	PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, year);
            pst.setString(2, courseOrDeptName);
            pst.setString(3, branchOrRole);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to generate roll number or employee ID (overloaded for course/department)
    public String generateID(String admissionYear, String courseOrDeptName, String branchOrRole , String type) {
        int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));
        int[] code = getCode(type, courseOrDeptName, branchOrRole );
        int counter = getCounter(type, admissionYear, courseOrDeptName, branchOrRole);

      //  JOptionPane.showMessageDialog(null, "Year : "+year , "Success", JOptionPane.INFORMATION_MESSAGE);
      //  JOptionPane.showMessageDialog(null, "Course Code : "+code[0]+" , Branch Code : "+code[1] , "Success", JOptionPane.INFORMATION_MESSAGE);
      //  JOptionPane.showMessageDialog(null, " Counter : "+counter , "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Format: [Year][Code][Counter] -> Example: 20101201
        String id = String.format("%02d%02d%02d%02d", year, code[0], code[1], counter);
        return id;
    }  
   

    // -------------------------------------------------------------------------------------------------------------------------------
    
    public int getTempCounter( String type, String year, String courseOrDeptName, String branchOrRole ) {
        String query = null;
        if ("course".equalsIgnoreCase(type)) {
            query = "SELECT Counter FROM temp_RollNumberCounters WHERE yoa = ? AND Course = ? and BranchName = ? ";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "SELECT Counter FROM temp_EmpIdCounters WHERE yoj = ? AND department = ? and role = ? ";
        }

        int counter = 0;
        try
        {
        	JdbcConnection gtcon = new JdbcConnection();
        	PreparedStatement gpst = gtcon.prepareStatement(query);
        	
        	gpst.setString(1, year);
        	gpst.setString(2, courseOrDeptName);
        	gpst.setString(3, branchOrRole);
            ResultSet rs = gpst.executeQuery();
            if (rs.next()) {
                counter = rs.getInt("Counter");
            }
            else {
                // If no counter exists, insert a new row with Counter = 1
                insertInitialTempCounter(type, year, courseOrDeptName, branchOrRole);
                counter = 1; // Set the initial counter value
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return counter;
    }
    
    
    private void insertInitialTempCounter(String type, String year, String courseOrDeptName, String branchOrRole) {
        String query = null;

        if ("course".equalsIgnoreCase(type)) {
            query = "INSERT INTO temp_RollNumberCounters (yoa, course, BranchName, Counter) VALUES (?, ?, ?, 1)";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "INSERT INTO temp_EmpIdCounters (yoj, department, role, Counter) VALUES (?, ?, ?, 1)";
        }

        try {
            JdbcConnection gccon = new JdbcConnection();
            PreparedStatement pst = gccon.prepareStatement(query);

            pst.setString(1, year);
            pst.setString(2, courseOrDeptName);
            pst.setString(3, branchOrRole);

            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    

    // Method to update roll number or employee ID counter
    public void updateTempCounter(String Year, String courseOrDeptName, String branchOrRole, String type) {
    	int year = Integer.parseInt(Year);
        String query = null;
        if ("course".equalsIgnoreCase(type)) {
            query = "UPDATE temp_RollNumberCounters SET Counter = Counter + 1 WHERE yoa = ? AND Course = ? and BranchName = ? ";
        } else if ("department".equalsIgnoreCase(type)) {
            query = "UPDATE temp_EmpIdCounters SET Counter = Counter + 1 WHERE yoj = ? AND department = ? and role = ? ";
        }
        
        try
        {
        	JdbcConnection tcon = new JdbcConnection();
        	PreparedStatement tpst = tcon.prepareStatement(query);
        	
        	tpst.setInt(1, year);
        	tpst.setString(2, courseOrDeptName);
        	tpst.setString(3, branchOrRole);
        	tpst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to generate roll number or employee ID (overloaded for course/department)
    public String generateTempID(String admissionYear, String courseOrDeptName, String branchOrRole , String type) {
        int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));
        int counter = getTempCounter(type, admissionYear, courseOrDeptName, branchOrRole);
        int[] code = getCode(type, courseOrDeptName, branchOrRole );
        
      //  JOptionPane.showMessageDialog(null, "Year : "+year , "Success", JOptionPane.INFORMATION_MESSAGE);
      //  JOptionPane.showMessageDialog(null, "Course Code : "+code[0]+" , Branch Code : "+code[1] , "Success", JOptionPane.INFORMATION_MESSAGE);
      //  JOptionPane.showMessageDialog(null, " Counter : "+counter , "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // Format: [Year][Code][Counter] -> Example: 20101201
      //  String id = String.format("%02d%02d", year, counter);         // better for temp counter
        String id = String.format("%02d%02d%02d%02d", year, code[0], code[1], counter);

        // Update counter after generating the ID
      //  updateCounter(year, courseOrDeptName, type);

        return id;
    }
    
}














/*
 
 
 import javax.swing.JOptionPane;

public class AddStudent {
    private Generator generator = new Generator();

    public void addStudent(String name, String courseName, String branchName, String admissionYear) {
        try {
            String rollNo = generator.generateID(admissionYear, courseName, "course");
            // Add student logic here (e.g., insert into database)
            JOptionPane.showMessageDialog(null, "Student added successfully! Roll No: " + rollNo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add student. Error: " + e.getMessage());
        }
    }
}


import javax.swing.JOptionPane;

public class AddTeacher {
    private Generator generator = new Generator();

    public void addTeacher(String name, String departmentName, String joiningYear) {
        try {
            String empID = generator.generateID(joiningYear, departmentName, "department");
            // Add teacher logic here (e.g., insert into database)
            JOptionPane.showMessageDialog(null, "Teacher added successfully! Employee ID: " + empID);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add teacher. Error: " + e.getMessage());
        }
    }
}


4. Advantages of the Design
Code Reusability: The Generator class centralizes roll number and employee ID generation, reducing duplication.
Flexibility: Easily extendable to support additional identifier types (e.g., staff IDs, project codes).
Modularity: Clean separation of concerns between the logic for generating identifiers and the logic for managing students/teachers.
Maintainability: Changes to roll number or employee ID formats can be made in a single location.
 
 
 */



