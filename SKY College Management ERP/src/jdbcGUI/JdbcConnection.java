package jdbcGUI;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnection 
{
	String userid = "";
	String passWord = "";
	String dbName = "";
	Connection con = null ;
	
	public JdbcConnection(String userName, String passWord)       // parametrised constructor
	{
		if(userName.isBlank() && passWord.isBlank())
		{
			 userid = "root";
			 this.passWord = "12345678";
		}
		else
		{
			userid = userName;
			this.passWord = passWord;
		}
		
	}
	
	public JdbcConnection() {   }                        // default constructor
		 		
  public Connection  connect(String dbName)                  
   {
	  try
	  {
		 String path = "com.mysql.cj.jdbc.Driver"; 
		 Class.forName(path);
		 
		 String url = dbName.isEmpty() ? "jdbc:mysql://localhost:3306/" : "jdbc:mysql://localhost:3306/" + dbName;   // ternary operator
		
		 con = DriverManager.getConnection(url, userid, passWord);    // con = DriverManager.getConnection(url + dbName, userid, password);    

	  }
	  catch(Exception e)
	  {
//		  System.out.println(e.getMessage());
//		  System.out.print(" \n ");
	  }
	  return con;	
   }
     
}

 
