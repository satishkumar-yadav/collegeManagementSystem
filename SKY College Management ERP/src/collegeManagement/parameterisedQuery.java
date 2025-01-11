package collegeManagement;

import java.sql.*;

public class parameterisedQuery {

	    public static void main(String[] args) {
	        String username = "admin";  // Simulated user input
	        String password = "1234";  // Simulated user input

	        try {
	            // Establish connection to database
	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "root", "password");

	            // Parameterized query with placeholders
	            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	            PreparedStatement pstmt = conn.prepareStatement(query);

	            // Bind user inputs to placeholders
	            pstmt.setString(1, username); // Binds to the first ?
	            pstmt.setString(2, password); // Binds to the second ?

	            // Execute query
	            ResultSet rs = pstmt.executeQuery();

	            // Process result
	            if (rs.next()) {
	                System.out.println("Login successful!");
	            } else {
	                System.out.println("Invalid username or password.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	}




/*
SQL Injection Vulnerability Demonstration
The SQL query:
--------------------------------------------------------------------------------------------------
String query = "select * from student where rollno = '"+crollno.getSelectedItem()+"'";
--------------------------------------------------------------------------------------------------
is vulnerable to SQL injection because it concatenates user input (crollno.getSelectedItem()) directly into the query string. If a malicious user 
inputs a specially crafted value, they can manipulate the SQL query to execute unintended commands.

Example: How SQL Injection Works
Let’s assume the input field crollno allows the user to select or enter a roll number, and the following code is used to execute the query:
------------------------------------------------------------------------------------------------------------
ResultSet rs = c1.st.executeQuery("select * from student where rollno = '"+crollno.getSelectedItem()+"'");
-----------------------------------------------------------------------------------------------------------

A normal user might input:
101
The query executed will be:
------------------------------------------------
select * from student where rollno = '101';
--------------------------------------------------

However, a malicious user could input: 101' OR '1'='1
The query executed becomes:
------------------------------------------------------
select * from student where rollno = '101' OR '1'='1';
---------------------------------------------------------

Effect of the Injection
-The condition '1'='1' is always true, so this query will return all rows in the student table, ignoring the intended logic to fetch a single roll 
 number's details.
-If the database contains sensitive information, the attacker can retrieve all the data.

More Advanced SQL Injection
Dropping a Table:
If the application also allows more damaging SQL injection, such as multiple statements (if the database doesn't restrict it), an attacker could input: 101'; DROP TABLE student;--

The query executed becomes:
---------------------------------------------------------------------
select * from student where rollno = '101'; DROP TABLE student;--';
---------------------------------------------------------------------
-- indicates a comment in SQL, which ignores the rest of the line.
This query will first fetch the data for roll number 101 and then drop the student table, deleting all student data.


Mitigation Using Prepared Statements
Prepared Statements ensure that user input is treated as data and not executable SQL commands. Here's how you can rewrite the query securely:

Secure Code:
--------------------------------------------------------------
String query = "SELECT * FROM student WHERE rollno = ?";
PreparedStatement pstmt = c1.st.prepareStatement(query);
pstmt.setString(1, crollno.getSelectedItem());
ResultSet rs = pstmt.executeQuery();


Why Prepared Statements Work?
Prepared Statements:
1.Separate SQL code from data.
2.Escape special characters in user input, preventing it from altering the SQL syntax.
3.Treat user input as a literal value, even if it contains malicious code.

Demonstrating the Secure Query
If the user input is: 101' OR '1'='1

The secure query executes as:
-----------------------------------------------------------
SELECT * FROM student WHERE rollno = '101\' OR \'1\'=\'1';
------------------------------------------------------------
-The malicious input is escaped.
-The database treats the input as a string literal, not as part of the SQL logic.


What are Parameterized Queries?
Parameterized Queries (also called Prepared Statements) are a method of executing SQL queries by separating the SQL code from user input. This ensures 
that user input is treated strictly as data and not executable SQL commands, thereby preventing SQL Injection attacks.

In Parameterized Queries:
1.The SQL query is precompiled with placeholders (?).
2.User input is bound to these placeholders.
3.The database handles the input as a literal value, even if it contains malicious characters.

How Do Parameterized Queries Work?
When writing a Parameterized Query, you define the query structure with placeholders (?) where user input will go. For example:

SQL Query Without Parameters:
-------------------------------------------------------------------
SELECT * FROM users WHERE username = 'admin' AND password = '1234';
-------------------------------------------------------------------
Here, user inputs (username and password) are directly concatenated into the query string, making it vulnerable to SQL injection.

Parameterized Query Example:
-------------------------------------------------------------
SELECT * FROM users WHERE username = ? AND password = ?;
-------------------------------------------------------------
The ? are placeholders where the input will be safely inserted. This query is precompiled, and inputs are passed later using a prepared statement.


Java Example with Parameterized Queries
Let’s consider a login form where the user provides a username and password. Here's how to use Parameterized Queries in Java:

Vulnerable Code (Without Parameters):
--------------------------------------------------
String username = "admin";  // User input
String password = "1234";  // User input

// Direct concatenation (bad practice)
String query = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "';";

ResultSet rs = statement.executeQuery(query);


-* If a malicious user enters username = 'admin' and password = ' OR '1'='1', the query becomes:
------------------------------------------------------------------------------
SELECT * FROM users WHERE username = 'admin' AND password = '' OR '1'='1';
----------------------------------------------------------------------------
This bypasses the password check and grants unauthorized access.

Advantages of Parameterized Queries
1.Prevents SQL Injection:
-The database treats input as data, not executable code.
-Malicious input (e.g., ' OR '1'='1) will not alter the query structure.
2.Performance Optimization:
-SQL queries with placeholders are precompiled, which improves execution speed when reusing the same query with different inputs.
3.Code Readability:
-Separating SQL logic and user input makes code easier to understand and maintain.

Example of User Input and Query Execution
User Input:
username = admin
password = ' OR '1'='1
Safe Query Executed by Prepared Statement:
------------------------------------------------------------------------------------
SELECT * FROM users WHERE username = 'admin' AND password = '\' OR \'1\'=\'1';
----------------------------------------------------------------------------------
-The special characters in the malicious input are escaped.
-The query checks for a literal string value (' OR '1'='1) in the password column, which won’t match.


Comparison Table
Aspect	            Non-Parameterized Query	                                            Parameterized Query

Security	        Vulnerable to SQL Injection	                                        Secure from SQL Injection

Code Readability	Hard to distinguish SQL logic from input concatenation	            Clear separation of SQL and input

Performance	        Query is compiled for each execution	                            Precompiled once and reused

Ease of Use	        Easy to implement but unsafe	                                    Slightly more effort but safe



*/