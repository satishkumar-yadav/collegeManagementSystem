package collegeManagement;

import java.sql.*;

import javax.swing.JOptionPane;

public class JdbcConnection2 
{
	private final String path = "com.mysql.cj.jdbc.Driver"; 
	
	//  private final String userid = "root";
	private String userid = "root";
	private String passWord = "12345678";
    private String dbName = "collegemanagement";
//	private String dbName = "";
	
	private String url = "jdbc:mysql://localhost:3306/" + dbName; 
	public Connection con =null;
	public Statement st;
		
	
	 public void databaseCredential(String username, String password) {
		   JOptionPane.showMessageDialog(null, " UserName : < "+username+"  >, PassWord : < "+password+" >");
		   
		   if ( ( username.isBlank() && password.isBlank() ) || (username.isBlank() && password.equalsIgnoreCase("enter password") ) )
		   {
			   userid = "root";
	           passWord = "12345678";
	           
	           dbName = "";
	           JOptionPane.showMessageDialog(null, " From Blank, UserName : < "+userid+"  >, PassWord : < "+passWord+" > , DataBase : < "+dbName+" >, URL : < "+url+" >");
	       }
		   else if (  username.isBlank() && !password.isBlank() && !password.equalsIgnoreCase("enter password")  )
		   {
			   userid = "root";
	           passWord = password;
	           
	           dbName = "";
	           JOptionPane.showMessageDialog(null, " From Username Blank, UserName: < "+userid+"  >, PassWord : < "+passWord+" > , DataBase : < "+dbName+" >, URL : < "+url+" >");
	       }
		   else if (  !username.isBlank() && password.isBlank()  )
		   {
			   userid = username;
	           passWord = "12345678";
	           
	           dbName = "";
	           JOptionPane.showMessageDialog(null, " From password Blank, UserName: < "+userid+"  >, PassWord : < "+passWord+" > , DataBase : < "+dbName+" >, URL : < "+url+" >");
	       }
	       else 
	       {
	    	   userid = username;
	           passWord = password;
	           dbName= "";
	           JOptionPane.showMessageDialog(null, "From Not Blank,  UserName : < "+userid+"  >, PassWord : < "+passWord+" > , DataBase : < "+dbName+" >, URL : < "+url+" >");
	       }
		}
	
	
   public JdbcConnection2 ()                  
   {
	  try
	  {
		 // JOptionPane.showMessageDialog(null, "From JDBC >> UserName : < "+userid+"  >, PassWord : < "+passWord+" > , DataBase : < "+dbName+" >, URL : < "+url+" >"); 
		  
		 Class.forName(path);
		 con = DriverManager.getConnection(url,userid,passWord);    
		// JOptionPane.showMessageDialog(null, "Connection : > "+con);
		 st = con.createStatement();
		
	  }
	  catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  System.out.println("Error in database connection: " + e.getMessage());
//		  System.out.print(" \n ");
	  }
   }
   
  
   
   public Connection getConnection() throws SQLException
   {
	 //  return con;
	   if (con != null  && !con.isClosed() ) {
		   return con;
       }
       throw new SQLException("Connection is not established.");
   }
   
   
   public Statement createStatement() throws SQLException {
       if (con != null) {
          // return con.createStatement();
           return st;
       }
       throw new SQLException("Connection is not established.");
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
  
   
 //  public static void main(String[] args) {   new JdbcConnection2();  }
}