package collegeManagement;

import java.sql.*;

public class JdbcConnection 
{
	private final String path = "com.mysql.cj.jdbc.Driver"; 
	
	private final String userid = "root";
	private final String passWord = "12345678";
	private final String dbName = "collegemanagement";
	
	private final String url = "jdbc:mysql://localhost:3306/" + dbName; 
	private Connection con ;
	public Statement st;
		
   public JdbcConnection  ()                  
   {
	  try
	  {
		 Class.forName(path);
		 con = DriverManager.getConnection(url, userid, passWord);       
		 st = con.createStatement();
		// ps = con.prepareStatement();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  System.out.println("Error in database connection: " + e.getMessage());
//		  System.out.print(" \n ");
	  }
   }
   
   
// Returns the PreparedStatement
   public PreparedStatement prepareStatement(String query) throws SQLException {
       if (con != null) {
           return con.prepareStatement(query);
       }
       throw new SQLException("Connection is not established.");
   }
   
   
   // Closes the connection and resources
   public void close() {
	    try {
	        //if (st != null) st.close();
	        if (con != null) con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}


  
}