package testpackage;

public class DynamicRollno {

//	1. Define Roll Number Counters
//	Add a static counter for each branch:
	private static int cseCounter = 1; // Counter for Computer Science
	private static int otherBranchCounter = 1; // Counter for other branches

//	2. Generate Roll Number Dynamically
//	Create a method to generate the rollno:
	
	private String generateRollNo(String branch) {
	    String prefix;
	    int counter;

	    if (branch.equalsIgnoreCase("Computer Science")) {
	        prefix = "2010";
	        counter = cseCounter;
	    } else {
	        prefix = "3010";
	        counter = otherBranchCounter;
	    }

	    // Format counter to 2 digits (e.g., "01", "02", etc.)
	    String rollNo = prefix + String.format("%02d", counter);

	    return rollNo;
	}

	
//	3. Increment Counter on Successful Add
//	After validating all fields and successfully adding the student, update the counter for the respective branch:
	
	private void updateRollNoCounter(String branch) {
	    if (branch.equalsIgnoreCase("Computer Science")) {
	        cseCounter++;
	    } else {
	        otherBranchCounter++;
	    }
	}

	
	
//	4. Update Validation and Add Logic
//	In the method where you handle student addition:
	
	private void validateAndAddStudent(String name, String fname, String phone, String aadhar, String email, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester) {
	    // Call validation method
	    validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

	    // Generate roll number based on branch
	    String rollNo = generateRollNo(branch);

	    // Simulate adding student to the database
	    boolean success = addStudentToDatabase(name, fname, phone, aadhar, email, rollNo, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

	    // Update counter only if addition was successful
	    if (success) {
	        updateRollNoCounter(branch);
	        JOptionPane.showMessageDialog(this, "Student added successfully! Roll No: " + rollNo, "Success", JOptionPane.INFORMATION_MESSAGE);
	    } else {
	        JOptionPane.showMessageDialog(this, "Failed to add student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	
//	5. Example Method for Adding to Database
//	You can create a stub method to simulate the database insertion:
//	
	
	private boolean addStudentToDatabase(String name, String fname, String phone, String aadhar, String email, String rollNo, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester) {
	    // Logic to add the student to the database
	    // Return true if successful, false otherwise
	    return true; // Simulating successful addition
	}

	
	
}








class second {
	
	
//	1. Database Schema
//	Add a table to track the roll number counters for each branch. For example:
	
	
	CREATE TABLE RollNumberCounters (
		    BranchName VARCHAR(50) PRIMARY KEY,
		    Counter INT NOT NULL
		);

		-- Initialize counters for branches
		INSERT INTO RollNumberCounters (BranchName, Counter) VALUES 
		('Computer Science', 1),
		('Mechanical', 1),
		('Electrical', 1); -- Add other branches as needed

	
	
//				2. Update Roll Number Generation
//				Modify the generateRollNo method to fetch the current counter from the database and increment it only after a successful student addition.
//
//				Fetch Roll Number Counter
//				Use a query to retrieve the counter:		
				
				
				private int getRollNoCounter(String branch) {
		    int counter = 0;
		    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
		         PreparedStatement pst = con.prepareStatement("SELECT Counter FROM RollNumberCounters WHERE BranchName = ?")) {
		        pst.setString(1, branch);
		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            counter = rs.getInt("Counter");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return counter;
		}

	
	
//		Update Roll Number Counter
//		Update the counter in the database:
		
		private void updateRollNoCounterInDatabase(String branch) {
		    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
		         PreparedStatement pst = con.prepareStatement("UPDATE RollNumberCounters SET Counter = Counter + 1 WHERE BranchName = ?")) {
		        pst.setString(1, branch);
		        pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		
//		Roll Number Generation Logic
//		Combine the counter with the prefix to generate the roll number:
		
		
		private String generateRollNo(String branch) {
		    String prefix = branch.equalsIgnoreCase("Computer Science") ? "2010" : "3010";
		    int counter = getRollNoCounter(branch);

		    // Format counter to 2 digits (e.g., "01", "02", etc.)
		    String rollNo = prefix + String.format("%02d", counter);
		    return rollNo;
		}

		
		
//		3. Integrate into Validation and Add Logic
//		Update the student addition logic to interact with the database:
	
	
		private void validateAndAddStudent(String name, String fname, String phone, String aadhar, String email, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester) {
		    validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

		    // Generate roll number dynamically
		    String rollNo = generateRollNo(branch);

		    // Add student to the database
		    boolean success = addStudentToDatabase(name, fname, phone, aadhar, email, rollNo, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

		    // Update roll number counter if addition is successful
		    if (success) {
		        updateRollNoCounterInDatabase(branch);
		        JOptionPane.showMessageDialog(this, "Student added successfully! Roll No: " + rollNo, "Success", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(this, "Failed to add student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		
		
//		4. Add Student to Database
//		Modify the addStudentToDatabase method to insert the student's data:
	
		
		private boolean addStudentToDatabase(String name, String fname, String phone, String aadhar, String email, String rollNo, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester) {
		    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
		         PreparedStatement pst = con.prepareStatement(
		             "INSERT INTO Students (Name, FName, Phone, Aadhar, Email, RollNo, X, XII, Gender, Address, DOB, Course, Branch, Qualification, ProfilePicture, Semester) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
		        pst.setString(1, name);
		        pst.setString(2, fname);
		        pst.setString(3, phone);
		        pst.setString(4, aadhar);
		        pst.setString(5, email);
		        pst.setString(6, rollNo);
		        pst.setString(7, x);
		        pst.setString(8, xii);
		        pst.setString(9, gender);
		        pst.setString(10, address);
		        pst.setString(11, dob);
		        pst.setString(12, course);
		        pst.setString(13, branch);
		        pst.setString(14, qualification);

		        // Convert the selected file to a byte array for storage (if needed)
		        byte[] profilePic = Files.readAllBytes(selectedFile.toPath());
		        pst.setBytes(15, profilePic);

		        pst.setString(16, semester);

		        int rowsInserted = pst.executeUpdate();
		        return rowsInserted > 0;
		    } catch (SQLException | IOException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

		
		
		
//		5. Database Table for Students
//		Ensure your Students table has the necessary fields:
		
	
		CREATE TABLE Students (
			    ID INT AUTO_INCREMENT PRIMARY KEY,
			    Name VARCHAR(100),
			    FName VARCHAR(100),
			    Phone VARCHAR(15),
			    Aadhar VARCHAR(12),
			    Email VARCHAR(100),
			    RollNo VARCHAR(10) UNIQUE,
			    X VARCHAR(10),
			    XII VARCHAR(10),
			    Gender VARCHAR(10),
			    Address TEXT,
			    DOB DATE,
			    Course VARCHAR(50),
			    Branch VARCHAR(50),
			    Qualification VARCHAR(50),
			    ProfilePicture BLOB,
			    Semester VARCHAR(10)
			);

		
		
		
		
		
		
		
}







