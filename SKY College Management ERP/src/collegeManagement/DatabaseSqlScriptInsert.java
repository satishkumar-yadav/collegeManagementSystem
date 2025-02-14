package collegeManagement;

public class DatabaseSqlScriptInsert {

	 public static final String[] SQL_SCRIPT2 = {
	        
			 """	
			 INSERT INTO Courses (CourseCode, Course) VALUES
			 (10, 'B-Tech'),
			 (20, 'Diploma'),
			 (30, 'BBA'),
			 (40, 'BCA'),
			 (50, 'Bsc'),
			 (60, 'BA')
			 
	 		""" , 
	 		
			"""	 
			 INSERT INTO Branches (BranchCode, BranchName) VALUES
			 (01, 'Computer Science'),
			 (02, 'ECE'),
			 (03, 'EEE'),
			 (04, 'Mechanical'),
			 (05, 'Civil'),
			 (06, 'IT'),
			 (07, 'Science'),
			 (08, 'BBA'),
			 (09, 'Economics'),
			 (10, 'Psychology'),
			 (11, 'Political Science'),
			 (12, 'Philosophy'),
			 (13, 'History'),
			 (14, 'Literature')
			 
	 		"""  ,

	 		"""	
			 INSERT INTO Departments (departmentCode, department) VALUES
			 (11, 'Computer Science'),
			 (22, 'ECE'),
			 (33, 'EEE'),
			 (44, 'Mechanical'),
			 (55, 'Civil'),
			 (66, 'Account'),
			 (77, 'Examination'),
			 (88, 'Library'),
			 (99, 'Mathematics'),
			 (92, 'Physics'),
			 (82, 'Literature')
			 
	 		""" ,
	 		
             """	
			 INSERT INTO Roles (roleCode, role) VALUES
			 (01, 'HOD'),
			 (02, 'Sr.'),
			 (03, 'Asst.'),
			 (04, 'Jr.'),
			 (05, 'Assistant'),
			 (06, 'Lab Assistant'),
			 (07,'Other')
			 
	 		""" ,

			 """	
			 INSERT INTO RollNumberCounters (Year, Course, BranchName, Counter) VALUES 
			 (2024, 'B-Tech', 'Computer Science', 2), 
			 (2025, 'Diploma', 'Computer Science', 2) 
			 
	 		""" ,
	 		
             """	
			 INSERT INTO EmpIdCounters (Year, department, role, Counter) VALUES (2024, 'Computer Science', 'HOD', 2), 
			 (2025, 'ECE', 'Sr.', 2) 
			 
	 		"""  ,
	 		
			" INSERT INTO temp_RollNumberCounters (yoa, Course, BranchName, Counter) VALUES (2025, 'B-Tech', 'Computer Science', 1)" ,

			" INSERT INTO temp_EmpIdCounters (yoj, department, role, Counter) VALUES (2025, 'Computer Science', 'HOD', 1)" ,

			"""	
			 INSERT INTO teacher (empid, name, fname, phone, email, aadhar, dob, address, department, subject, qualification, classx, classxii, grad, pg, phd, isphd, gender,doj, designation) VALUES 
			 ('24110101', 'John Smith', 'Michael Smith', 9876543210, 'john.smith@college.edu', 123456789012, '1985-01-15', '123 Main St, City A', 'Computer Science', 'Java Programming', 'M.Tech', 85.0, 88.5, 78.0, 85.0, 'Math', 'Yes', 'Male', '2024-06-18','HOD'),
			 ('25220201', 'Alice Johnson', 'David Johnson', 9876543211, 'alice.johnson@college.edu', 987654321098, '1990-02-25', '456 Elm St, City B', 'ECE', 'Circuit Design', 'PhD', 89.0, 90.0, 82.0, 88.5, 'CS','No', 'Female', '2025-06-15','Sr. Professor')
            
			""" ,
			 
			"""	
			 INSERT INTO student (rollno, name, fname, phone, email, aadhar, dob, address, branch, course, qualification, classx, classxii, semester, gender,doa) VALUES 
			 ('24100101', 'Emma Watson', 'Daniel Watson', 9876543212, 'emma.watson@college.edu', 876543210987, '2000-05-15', '789 Oak St, City C', 'Computer Science', 'B.Tech', '12th', 90.0, 92.5, '3rd Semester', 'Female','2024-06-25'),
			 ('25200201', 'Liam Brown', 'James Brown', 9876543213, 'liam.brown@college.edu', 765432109876, '1999-11-20', '321 Pine St, City D', 'ECE', 'Diploma', '12th', 88.0, 85.5, '2nd Semester', 'Male', '2025-06-15')

			""" ,
			
			"""	
			 INSERT INTO faculty (empid, name, fname, phone, email, aadhar, dob, address, department, qualification, classx, classxii, grad, pg, gender, doj, role,designation) VALUES 
			 ('100001', 'John Smith', 'Michael Smith', 4870843210, 'john.smith@college.edu', 123456789012, '1985-01-15', '123 Main St, City A', 'Computer Science', 'M.Tech', 85.0, 88.5, 78.0, 85.0, 'Male', '2000-06-18', 'Admin','Administrator'),
			 ('190054', 'Alice Johnson', 'David Johnson', 2837543211, 'alice.johnson@college.edu', 223456789012, '1990-02-25', '456 Elm St, City B', 'Electronics', 'Graduation', 89.0, 90.0, 82.0, 88.5, 'Female', '2010-06-15', 'Accountant','Sr. Accountant'),
			 ('200053', 'John Smith', 'Michael Smith', 7876501210, 'john.smith@college.edu', 323456789012, '1985-01-15', '123 Main St, City A', 'Computer Science', 'M.Tech', 85.0, 88.5, 78.0, 85.0, 'Male', '2000-06-18', 'Librarian','Sr. Librarian'),
			 ('230054', 'Alice Johnson', 'David Johnson', 9876093211, 'alice.johnson@college.edu', 423456789012, '1990-02-25', '456 Elm St, City B', 'Electronics', 'Graduation', 89.0, 90.0, 82.0, 88.5, 'Female', '2010-06-15', 'LabIncharge','Lab Assistant'),
			 ('240053', 'John Smith', 'Michael Smith', 1876542810, 'john.smith@college.edu', 523456789012, '1985-01-15', '123 Main St, City A', 'Computer Science', 'M.Tech', 85.0, 88.5, 78.0, 85.0, 'Male', '2000-06-18', 'Database','Administrator'),
			 ('250054', 'Alice Johnson', 'David Johnson', 8805543211, 'alice.johnson@college.edu', 623456789012, '1990-02-25', '456 Elm St, City B', 'Electronics', 'Graduation', 89.0, 90.0, 82.0, 88.5, 'Female', '2010-06-15', 'Driver','Other'),
			 ('220043', 'John Smith', 'Michael Smith', 5876543200, 'john.smith@college.edu', 723456789012, '1985-01-15', '123 Main St, City A', 'Computer Science', 'M.Tech', 85.0, 88.5, 78.0, 85.0, 'Male', '2000-06-18', 'Guards','Other'),
			 ('220034', 'Alice Johnson', 'David Johnson', 6879043211, 'alice.johnson@college.edu', 823456789012, '1990-02-25', '456 Elm St, City B', 'Electronics', 'Graduation', 89.0, 90.0, 82.0, 88.5, 'Female', '2010-06-15', 'Other Workers','Other')
			 
			"""  ,
			
              """	
			  insert into login (role, username, password, sec_ques, sec_ans,id) values 
			  ('Admin', 'admin123', '123456', 'Favourite Food', 'Apple', (SELECT empid FROM faculty WHERE empid = '100001'))
			  
			"""  ,
			  
			"""	
			 INSERT INTO subject (rollno, semester, sub1, sub2, sub3, sub4, sub5, sub6, sub7, lab1, lab2, lab3) VALUES 
			 ('24100101', '3rd Semester', 'Maths', 'Physics', 'Programming', 'Data Structures', 'OS', 'Database', 'Networks', 'Java Lab', 'OS Lab', 'DB Lab'),
			 ('25200201', '2nd Semester', 'Maths', 'Electronics', 'Signals', 'Circuits', 'VLSI', 'Control Systems', 'DSP', 'Signal Lab', 'Circuit Lab', 'DSP Lab')
			  
			"""  ,

			 """	
			 INSERT INTO marks (rollno, semester, msub1, msub2, msub3, msub4, msub5, msub6, msub7, mlab1, mlab2, mlab3) VALUES 
			 ('24100101', '3rd Semester', 'A', 'B+', 'A', 'A-', 'B', 'A+', 'A', 'A', 'A-', 'A+'),
			 ('25200201', '2nd Semester', 'B+', 'A', 'A-', 'B+', 'A', 'B', 'A', 'A-', 'A', 'B+')
			 
			"""   ,

			 """	
			 INSERT INTO fee (rollno, name, fname, course, branch, semester, fee, fee_paid, feepaid_date, due) VALUES 
			 ('24100101', 'Emma Watson', 'Daniel Watson', 'B.Tech', 'Computer Science', '3rd Semester', 50000, 30000, '2024-01-05 10:30:00', 20000),
			 ('25200201', 'Liam Brown', 'James Brown', 'Diploma', 'ECE', '2nd Semester', 50000, 50000, '2024-01-06 12:00:00', 0)
			 
			"""  ,

			 """	
			 INSERT INTO salary (empid, name, fname, department, month, sal, sal_paid, salpaid_date, due) VALUES 
			 ('24110101', 'John Smith', 'Michael Smith', 'Computer Science', 'December', 80000, 60000, '2024-12-20 09:00:00', 20000),
			 ('25220201', 'Alice Johnson', 'David Johnson', 'ECE', 'December', 85000, 85000, '2024-12-22 14:00:00', 0)
			 
			"""  ,

			"""	
			 INSERT INTO attendance_faculty (empid, date, day, first_half, second_half) VALUES ('24110101', '2024-12-23', 'Monday', 'Present', 'Present'),
			 ('25220201', '2024-12-23', 'Monday', 'Present', 'Absent')
			 
			"""  ,

			 """	
			 INSERT INTO attendance_student (rollno, date, day, first_half, second_half) VALUES 
			 ('24100101', '2024-12-23', 'Monday', 'Present', 'Present'),
			 ('25200201', '2024-12-23', 'Monday', 'Absent', 'Present')
			 
			""" ,
			  
			"  INSERT INTO studentleave (rollno, date, duration) VALUES ('24100101', '2024-12-23', 'Full Day'), ('25200201', '2025-12-23', 'Half Day')",

			" INSERT INTO teacherleave (empid, date, duration) VALUES ('24110101', '2024-12-23', 'Half Day'), ('25220201', '2025-12-23', 'Full Day')"
	            
	    };
	
}
