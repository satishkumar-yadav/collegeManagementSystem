package testpackage;

public class DynamicRollNo2 {

	
//	1. Roll Number Format
//	The roll number is a 7-digit number:
//
//	Year: Last two digits of the admission year (20 for 2020).
//	Course Code: One digit, e.g., 1 for B-Tech.
//	Branch Code: Two digits, e.g., 01 for Computer Science.
//	Counter: Two digits, starting from 01.
//	Example:
//
//	For a student admitted in 2020, in B-Tech, in Computer Science:
//	Roll number: 2010101 (Year 20 + Course 1 + Branch 01 + Counter 01).
	
	
//	2. Database Schema
//	We need a table to manage the roll number counters, with additional fields for the year, course, and branch:
	
	CREATE TABLE RollNumberCounters (
		    Year INT NOT NULL,
		    CourseCode INT NOT NULL,
		    BranchCode INT NOT NULL,
		    Counter INT NOT NULL,
		    PRIMARY KEY (Year, CourseCode, BranchCode)
		);

		-- Initialize counters for each combination
		INSERT INTO RollNumberCounters (Year, CourseCode, BranchCode, Counter) VALUES
		(2020, 1, 1, 1), -- Computer Science (01) for B-Tech (1) in 2020
		(2020, 1, 2, 1), -- Mechanical (02) for B-Tech (1) in 2020
		(2020, 1, 3, 1); -- Electrical (03) for B-Tech (1) in 2020

				
//	3. Generate Roll Number Logic
//	Fetch Counter for a Combination
//	Retrieve the current counter from the database:
				
	
				private int getRollNoCounter(int year, int courseCode, int branchCode) {
		    int counter = 0;
		    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
		         PreparedStatement pst = con.prepareStatement(
		             "SELECT Counter FROM RollNumberCounters WHERE Year = ? AND CourseCode = ? AND BranchCode = ?")) {
		        pst.setInt(1, year);
		        pst.setInt(2, courseCode);
		        pst.setInt(3, branchCode);
		        ResultSet rs = pst.executeQuery();
		        if (rs.next()) {
		            counter = rs.getInt("Counter");
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return counter;
		}

		
		
//		Update Counter After Successful Addition
//		Increment the counter only after adding the student successfully:		
//				
		
		private void updateRollNoCounterInDatabase(int year, int courseCode, int branchCode) {
		    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
		         PreparedStatement pst = con.prepareStatement(
		             "UPDATE RollNumberCounters SET Counter = Counter + 1 WHERE Year = ? AND CourseCode = ? AND BranchCode = ?")) {
		        pst.setInt(1, year);
		        pst.setInt(2, courseCode);
		        pst.setInt(3, branchCode);
		        pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

		
//		Generate Roll Number
//		Construct the roll number dynamically:
		
		private String generateRollNo(String admissionYear, String course, String branch) {
		    // Extract the last two digits of the admission year
		    int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));

		    // Map course to a course code
		    int courseCode = course.equalsIgnoreCase("B-Tech") ? 1 : 2; // Example: 1 for B-Tech, 2 for M-Tech

		    // Map branch to a branch code
		    int branchCode;
		    switch (branch.toLowerCase()) {
		        case "computer science":
		            branchCode = 1;
		            break;
		        case "mechanical":
		            branchCode = 2;
		            break;
		        case "electrical":
		            branchCode = 3;
		            break;
		        default:
		            branchCode = 99; // Default for unknown branch
		    }

		    // Fetch the current counter
		    int counter = getRollNoCounter(year, courseCode, branchCode);

		    // Format the roll number
		    String rollNo = String.format("%02d%d%02d%02d", year, courseCode, branchCode, counter);

		    return rollNo;
		}

		
		
//		4. Integrate Into Student Addition
//		Update the student addition method to use the new roll number generation logic:
		
		private void validateAndAddStudent(String name, String fname, String phone, String aadhar, String email, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester, String admissionYear) {
		    validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

		    // Generate roll number dynamically
		    String rollNo = generateRollNo(admissionYear, course, branch);

		    // Add student to the database
		    boolean success = addStudentToDatabase(name, fname, phone, aadhar, email, rollNo, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

		    // Update roll number counter if addition is successful
		    if (success) {
		        int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));
		        int courseCode = course.equalsIgnoreCase("B-Tech") ? 1 : 2;
		        int branchCode = branch.equalsIgnoreCase("Computer Science") ? 1 : branch.equalsIgnoreCase("Mechanical") ? 2 : 3;

		        updateRollNoCounterInDatabase(year, courseCode, branchCode);
		        JOptionPane.showMessageDialog(this, "Student added successfully! Roll No: " + rollNo, "Success", JOptionPane.INFORMATION_MESSAGE);
		    } else {
		        JOptionPane.showMessageDialog(this, "Failed to add student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
		    }
		}

		
		
//		5. Database Table for Students
//		Ensure your Students table has the required fields:
		
		
		CREATE TABLE Students (
			    ID INT AUTO_INCREMENT PRIMARY KEY,
			    Name VARCHAR(100),
			    FName VARCHAR(100),
			    Phone VARCHAR(15),
			    Aadhar VARCHAR(12),
			    Email VARCHAR(100),
			    RollNo VARCHAR(7) UNIQUE,
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
	
	
//		6. Summary
//		Roll number dynamically generated based on admission year, course, and branch.
//		Counter updated only after successful student addition.
//		Easily extendable for more courses and branches.
	
}



//1. Database Schema
//a. Courses Table
//This table maps course names to their codes:

CREATE TABLE Courses (
	    CourseCode INT PRIMARY KEY,
	    CourseName VARCHAR(50) UNIQUE NOT NULL
	);

	-- Example entries
	INSERT INTO Courses (CourseCode, CourseName) VALUES
	(1, 'B-Tech'),
	(2, 'M-Tech');

	
//	b. Branches Table
//	This table maps branch names to their codes:
	
	CREATE TABLE Branches (
		    BranchCode INT PRIMARY KEY,
		    BranchName VARCHAR(50) UNIQUE NOT NULL
		);

		-- Example entries
		INSERT INTO Branches (BranchCode, BranchName) VALUES
		(1, 'Computer Science'),
		(2, 'Mechanical'),
		(3, 'Electrical');

		
//		c. RollNumberCounters Table
//		This table uses course names and branch names to track counters:
		
		CREATE TABLE RollNumberCounters (
			    Year INT NOT NULL,
			    CourseName VARCHAR(50) NOT NULL,
			    BranchName VARCHAR(50) NOT NULL,
			    Counter INT NOT NULL,
			    PRIMARY KEY (Year, CourseName, BranchName),
			    FOREIGN KEY (CourseName) REFERENCES Courses(CourseName),
			    FOREIGN KEY (BranchName) REFERENCES Branches(BranchName)
			);

			-- Initialize counters
			INSERT INTO RollNumberCounters (Year, CourseName, BranchName, Counter) VALUES
			(2020, 'B-Tech', 'Computer Science', 1),
			(2020, 'B-Tech', 'Mechanical', 1),
			(2020, 'B-Tech', 'Electrical', 1);

			
//			2. Modified Java Methods
//			a. Fetch Course and Branch Codes
//			Retrieve the course and branch codes dynamically:

			
			private int getCourseCode(String courseName) {
			    int courseCode = 0;
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
			         PreparedStatement pst = con.prepareStatement("SELECT CourseCode FROM Courses WHERE CourseName = ?")) {
			        pst.setString(1, courseName);
			        ResultSet rs = pst.executeQuery();
			        if (rs.next()) {
			            courseCode = rs.getInt("CourseCode");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return courseCode;
			}

			private int getBranchCode(String branchName) {
			    int branchCode = 0;
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
			         PreparedStatement pst = con.prepareStatement("SELECT BranchCode FROM Branches WHERE BranchName = ?")) {
			        pst.setString(1, branchName);
			        ResultSet rs = pst.executeQuery();
			        if (rs.next()) {
			            branchCode = rs.getInt("BranchCode");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return branchCode;
			}

			
//			b. Fetch Counter Based on Course and Branch Names
//			Update the query logic to use CourseName and BranchName:
			
			private int getRollNoCounter(int year, String courseName, String branchName) {
			    int counter = 0;
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
			         PreparedStatement pst = con.prepareStatement(
			             "SELECT Counter FROM RollNumberCounters WHERE Year = ? AND CourseName = ? AND BranchName = ?")) {
			        pst.setInt(1, year);
			        pst.setString(2, courseName);
			        pst.setString(3, branchName);
			        ResultSet rs = pst.executeQuery();
			        if (rs.next()) {
			            counter = rs.getInt("Counter");
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			    return counter;
			}

			
			
//			c. Update Counter
//			Use CourseName and BranchName when updating the counter:
			
			private void updateRollNoCounterInDatabase(int year, String courseName, String branchName) {
			    try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yourDatabase", "username", "password");
			         PreparedStatement pst = con.prepareStatement(
			             "UPDATE RollNumberCounters SET Counter = Counter + 1 WHERE Year = ? AND CourseName = ? AND BranchName = ?")) {
			        pst.setInt(1, year);
			        pst.setString(2, courseName);
			        pst.setString(3, branchName);
			        pst.executeUpdate();
			    } catch (SQLException e) {
			        e.printStackTrace();
			    }
			}

			
//			d. Generate Roll Number
//			Update the logic to use dynamic course and branch codes:
			
			private String generateRollNo(String admissionYear, String courseName, String branchName) {
			    // Extract the last two digits of the admission year
			    int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));

			    // Fetch course and branch codes dynamically
			    int courseCode = getCourseCode(courseName);
			    int branchCode = getBranchCode(branchName);

			    // Fetch the current counter
			    int counter = getRollNoCounter(year, courseName, branchName);

			    // Format the roll number
			    String rollNo = String.format("%02d%d%02d%02d", year, courseCode, branchCode, counter);

			    return rollNo;
			}

			
//			3. Integrate Into Student Addition
//			Update the student addition logic to dynamically generate and update roll numbers:
			
			private void validateAndAddStudent(String name, String fname, String phone, String aadhar, String email, String x, String xii, String gender, String address, String dob, String course, String branch, String qualification, File selectedFile, String semester, String admissionYear) {
			    validateChecks(name, fname, phone, aadhar, email, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

			    // Generate roll number dynamically
			    String rollNo = generateRollNo(admissionYear, course, branch);

			    // Add student to the database
			    boolean success = addStudentToDatabase(name, fname, phone, aadhar, email, rollNo, x, xii, gender, address, dob, course, branch, qualification, selectedFile, semester);

			    // Update roll number counter if addition is successful
			    if (success) {
			        int year = Integer.parseInt(admissionYear.substring(admissionYear.length() - 2));
			        updateRollNoCounterInDatabase(year, course, branch);
			        JOptionPane.showMessageDialog(this, "Student added successfully! Roll No: " + rollNo, "Success", JOptionPane.INFORMATION_MESSAGE);
			    } else {
			        JOptionPane.showMessageDialog(this, "Failed to add student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
			    }
			}

			
			
//			4. Advantages of the New Structure
//			Flexibility: Allows for easy addition of new courses and branches.
//			Readability: Uses human-readable names (CourseName, BranchName) instead of raw codes in RollNumberCounters.
//			Scalability: Supports dynamic mapping between names and codes using separate tables.




 