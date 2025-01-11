package collegeManagement;

public class JdbcQuery {
	
    public static String q1 = "create database ";
	
	public static String q2 = " create table ";
	
	public static String q3 = "select * from ";
	
	public static String q4 = "insert into ";
	
	public static String q5 = " values( ?, ?, ?, ?, ?) ";
	
	public static String q6 = "update ";
	
	public static String q7 = " set age = ? where id = ? ";
	
	public static String q8 = " delete from ";
	
	public static String q9 = " where id = ? ";
	
	public static String q10 = " show databases ";
	
	public static String q11 = " use ";
	
	public static String q12 = " show tables ";

}


/*
  
 package jdbcCLI;

public class JdbcQuery {
    public static final String q1 = "CREATE DATABASE ";
    public static final String q2 = "CREATE TABLE ";
    public static final String q3 = "SELECT * FROM ";
    public static final String q4 = "INSERT INTO ? VALUES (?, ?, ?, ?, ?)";
    public static final String q5 = "UPDATE ? SET age = ? WHERE id = ?";
    public static final String q6 = "DELETE FROM ? WHERE id = ?";
}
 
 //	public static String q3 = " ( id int(10), name varchar(30 ), age int(2), branch varchar(30), address varchar(50)) ";
 
 */


/*   correction using chatgpt 

Dynamic Table Names:

-> Directly appending the table name or database name into SQL queries is prone to SQL injection.
-> Fix: Use prepared statements with dynamic query building to safely handle table or database names.

public class JdbcQuery {
    public static final String q1 = "CREATE DATABASE IF NOT EXISTS ";
    public static final String q2 = "CREATE TABLE ";
    public static final String q3 = "SELECT * FROM ";
    public static final String q4 = "INSERT INTO table_name VALUES (?, ?, ?, ?, ?)";
    public static final String q5 = "UPDATE table_name SET age = ? WHERE id = ?";
    public static final String q6 = "DELETE FROM table_name WHERE id = ?";
}



*/