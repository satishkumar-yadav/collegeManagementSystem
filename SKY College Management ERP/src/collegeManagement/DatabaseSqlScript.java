package collegeManagement;

public class DatabaseSqlScript {

	 public static final String[] SQL_SCRIPT = {
	           
			" CREATE DATABASE IF NOT EXISTS collegemanagement",
			 
			" use collegemanagement",
			
		"""	
			 CREATE TABLE IF NOT EXISTS login (
			   role VARCHAR(20),
			   username VARCHAR(30) NOT NULL,
			   password VARCHAR(30),
			   sec_ques TEXT(100),
			   sec_ans VARCHAR(100),
			   id VARCHAR(10) not null UNIQUE
			   -- CONSTRAINT fk_student FOREIGN KEY (id) REFERENCES student(rollno) ,-- ON DELETE CASCADE,
			   -- CONSTRAINT fk_teacher FOREIGN KEY (id) REFERENCES teacher(empid) ,-- ON DELETE CASCADE,
			    -- CONSTRAINT fk_faculty FOREIGN KEY (id) REFERENCES faculty(empid) -- ON DELETE CASCADE
			   -- PRIMARY KEY (username)
			 )
			 """	,
        
			 """	
			 CREATE TABLE IF NOT EXISTS temp_login (
			   role VARCHAR(20),
			   username VARCHAR(30) NOT NULL,
			   password VARCHAR(30),
			   sec_ques TEXT(100),
			   sec_ans VARCHAR(100),
			   id VARCHAR(10) not null UNIQUE
			   -- CONSTRAINT fk_student FOREIGN KEY (id) REFERENCES student(rollno) ,-- ON DELETE CASCADE,
			   -- CONSTRAINT fk_teacher FOREIGN KEY (id) REFERENCES teacher(empid) ,-- ON DELETE CASCADE,
			    -- CONSTRAINT fk_faculty FOREIGN KEY (id) REFERENCES faculty(empid) -- ON DELETE CASCADE
			   -- PRIMARY KEY (username)
			 )
			 """ ,
			 
			 """	
			 CREATE TABLE IF NOT EXISTS Courses (
			     courseCode INT PRIMARY KEY,
			     course VARCHAR(50) UNIQUE NOT NULL
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS Branches (
			     BranchCode INT PRIMARY KEY,
			     BranchName VARCHAR(50) UNIQUE NOT NULL
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS Departments (
			     departmentCode INT PRIMARY KEY,
			     department VARCHAR(50) UNIQUE NOT NULL
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS Roles (
			     roleCode INT PRIMARY KEY,
			     role VARCHAR(50) UNIQUE NOT NULL
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS RollNumberCounters (
			     Year INT NOT NULL,
			     course VARCHAR(50) NOT NULL,
			     BranchName VARCHAR(50) NOT NULL,
			     Counter INT NOT NULL,
			     PRIMARY KEY (Year, course, BranchName),
			     FOREIGN KEY (course) REFERENCES Courses(course),
			     FOREIGN KEY (BranchName) REFERENCES Branches(BranchName)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS EmpIdCounters (
			     Year INT NOT NULL,
			     department VARCHAR(50) NOT NULL,
			     role VARCHAR(50) NOT NULL,
			     Counter INT NOT NULL,
			     PRIMARY KEY (Year, department, role)
			     -- FOREIGN KEY (department) REFERENCES Departments(department),
			    -- FOREIGN KEY (role) REFERENCES Roles(role)
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS teacher (
			   srno INT AUTO_INCREMENT,
			   empid VARCHAR(10) UNIQUE NOT NULL, -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   department VARCHAR(50) NOT NULL,
			   subject VARCHAR(50),
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT CHECK (classxii > 0 AND classxii < 100),
			   grad FLOAT CHECK (grad > 0 AND grad < 100),
			   pg FLOAT CHECK (pg >= 0 AND pg < 100),
			   phd VARCHAR(50),
			   gender VARCHAR(20) NOT NULL,
			   isphd VARCHAR(5) NOT NULL,
			   role VARCHAR(50) default 'Teacher',
			    doj DATE NOT NULL,
			     designation VARCHAR(50),
			    PRIMARY KEY (srno)
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS student (
			   ID INT AUTO_INCREMENT,
			   rollno VARCHAR(10) unique NOT NULL , -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   branch VARCHAR(50) NOT NULL,
			   course VARCHAR(50) NOT NULL,
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT ,
			   semester VARCHAR(30) NOT NULL,
			   gender VARCHAR(20) NOT NULL,
			   role VARCHAR(50) default 'Student',
			   doa DATE NOT NULL,
			   PRIMARY KEY (id)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS faculty (
			   srno INT AUTO_INCREMENT,
			   empid VARCHAR(10) UNIQUE NOT NULL, -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   department VARCHAR(50) NOT NULL,
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT CHECK (classxii > 0 AND classxii < 100),
			   grad FLOAT CHECK (grad > 0 AND grad < 100),
			   pg FLOAT CHECK (pg >= 0 AND pg < 100),
			   gender VARCHAR(20) NOT NULL,
			   role VARCHAR(50),
			    doj DATE NOT NULL,
			    designation VARCHAR(50),
			    PRIMARY KEY (srno)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS temp_teacher (
			   srno INT AUTO_INCREMENT,
			   empid VARCHAR(10) UNIQUE NOT NULL, -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   department VARCHAR(50) NOT NULL,
			   subject VARCHAR(50),
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT CHECK (classxii > 0 AND classxii < 100),
			   grad FLOAT CHECK (grad > 0 AND grad < 100),
			   pg FLOAT CHECK (pg >= 0 AND pg < 100),
			   phd VARCHAR(50),
			   gender VARCHAR(20) NOT NULL,
			   isphd VARCHAR(5) NOT NULL,
			   role VARCHAR(50) default 'Teacher',
			    doj DATE NOT NULL,
			     designation VARCHAR(50),
			    PRIMARY KEY (srno)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS temp_student (
			   ID INT AUTO_INCREMENT,
			   rollno VARCHAR(10) unique NOT NULL , -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   branch VARCHAR(50) NOT NULL,
			   course VARCHAR(50) NOT NULL,
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT ,
			   semester VARCHAR(30) NOT NULL,
			   gender VARCHAR(20) NOT NULL,
			   role VARCHAR(50) default 'Student',
			   doa DATE NOT NULL,
			   PRIMARY KEY (id)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS temp_faculty (
			   srno INT AUTO_INCREMENT,
			   empid VARCHAR(10) UNIQUE NOT NULL, -- AUTO_INCREMENT,
			   name VARCHAR(80) NOT NULL,
			   fname VARCHAR(80) NOT NULL,
			   phone VARCHAR(13),
			   email VARCHAR(80),
			   aadhar VARCHAR(12) UNIQUE NOT NULL,
			   dob DATE NOT NULL,
			   address text,
			   pic LONGBLOB,
			   department VARCHAR(50) NOT NULL,
			   qualification VARCHAR(50) NOT NULL,
			   classx FLOAT CHECK (classx > 0 AND classx < 100),
			   classxii FLOAT CHECK (classxii > 0 AND classxii < 100),
			   grad FLOAT CHECK (grad > 0 AND grad < 100),
			   pg FLOAT CHECK (pg >= 0 AND pg < 100),
			   gender VARCHAR(20) NOT NULL,
			   role VARCHAR(50),
			    doj DATE NOT NULL,
			     designation VARCHAR(50),
			    PRIMARY KEY (srno)
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS temp_RollNumberCounters (
			     yoa INT NOT NULL,
			     course VARCHAR(50) NOT NULL,
			     BranchName VARCHAR(50) NOT NULL,
			     Counter INT NOT NULL
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS temp_EmpIdCounters (
			     yoj INT NOT NULL,
			     department VARCHAR(50) NOT NULL,
			     role VARCHAR(50) NOT NULL,
			     Counter INT NOT NULL
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS subject (
			   rollno VARCHAR(10),
			   semester VARCHAR(30),
			   sub1 VARCHAR(60),
			   sub2 VARCHAR(60),
			   sub3 VARCHAR(60),
			   sub4 VARCHAR(60),
			   sub5 VARCHAR(60),
			   sub6 VARCHAR(60),
			   sub7 VARCHAR(60),
			   lab1 VARCHAR(60),
			   lab2 VARCHAR(60),
			   lab3 VARCHAR(60)
			  -- FOREIGN KEY (rollno) REFERENCES student(rollno) ON DELETE CASCADE
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS marks (
			   rollno VARCHAR(10),
			   semester VARCHAR(30),
			   msub1 VARCHAR(60),
			   msub2 VARCHAR(60),
			   msub3 VARCHAR(60),
			   msub4 VARCHAR(60),
			   msub5 VARCHAR(60),
			   msub6 VARCHAR(60),
			   msub7 VARCHAR(60),
			   mlab1 VARCHAR(60),
			   mlab2 VARCHAR(60),
			   mlab3 VARCHAR(60)
			   -- FOREIGN KEY (rollno) REFERENCES student(rollno) ON DELETE CASCADE
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS fee (
			   rollno VARCHAR(10),
			   name VARCHAR(50),
			   fname VARCHAR(50),
			   course VARCHAR(50),
			   branch VARCHAR(50),
			   semester VARCHAR(30),
			   fee INT,
			   fee_paid INT,
			   feepaid_date DATETIME,
			   due INT
			  -- FOREIGN KEY (rollno) REFERENCES student(rollno) ON DELETE CASCADE
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS salary (
			   empid VARCHAR(10),
			   name VARCHAR(50),
			   fname VARCHAR(50),
			   department VARCHAR(50),
			   month VARCHAR(50),
			   sal INT,
			   sal_paid INT,
			   salpaid_date DATETIME,
			   due INT
			   -- FOREIGN KEY (empid) REFERENCES teacher(empid) ON DELETE CASCADE
			 )
			 
			 """ ,

			"""	
			 CREATE TABLE IF NOT EXISTS attendance_faculty (
			   empid VARCHAR(10),
			   date DATE,
			   day VARCHAR(20),
			   first_half VARCHAR(20),
			   second_half VARCHAR(20)
			  -- FOREIGN KEY (empid) REFERENCES teacher(empid) ON DELETE CASCADE
			 )
			 
			 """ ,

			 """	
			 CREATE TABLE IF NOT EXISTS attendance_student (
			   rollno VARCHAR(10),
			   date DATE,
			   day VARCHAR(20),
			   first_half VARCHAR(20),
			   second_half VARCHAR(20)
			  -- FOREIGN KEY (rollno) REFERENCES student(rollno) ON DELETE CASCADE
			 )
			 
			 """ ,

			" create table IF NOT EXISTS studentleave(rollno varchar(20), date varchar(50), duration varchar(20))",

			" create table IF NOT EXISTS temp_studentleave(rollno varchar(20), date varchar(50), duration varchar(20))",

			" create table IF NOT EXISTS teacherleave(empid varchar(20), date varchar(50), duration varchar(20))",

			" create table IF NOT EXISTS temp_teacherleave(empid varchar(20), date varchar(50), duration varchar(20))"

	            
	    };
	 
}
