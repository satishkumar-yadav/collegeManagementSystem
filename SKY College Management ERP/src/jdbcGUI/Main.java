package jdbcGUI;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
	
	public Main()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("                Welcome!! To CRUD Operation");
		System.out.println("               Created by Tech Titans & Group \n ");
			
		
		System.out.println("           Enter Database Credential First :-");
		System.out.print(" Enter Username for Database :  ");
		String userName = sc.nextLine();
		System.out.print(" Enter Password for Database :  ");
		String passWord = sc.nextLine();
		System.out.print(" \n ");
		
		JdbcConnection jdbc = new JdbcConnection(userName, passWord);
		Connection con = jdbc.connect("");
				
		if(con != null)
		{
			System.out.println("  Connection Successful !! ");
			System.out.println("  You can now Perform Database Operation : \n");
			
			System.out.print("Enter your Database Name : ");
			String dbName = sc.nextLine();
			System.out.print("Enter Your Table Name : ");
		    String tableName = sc.nextLine();     
			
			System.out.print(" \n ");
			
			while(true)
			{
			 
			 System.out.println(" Please Enter your choice :- 1:CreateDatabase,  2:CreateTable,  3:Insert,  4:Retrieve,  5:Update,   6:Delete,  7:Manually, 8:Exit : ");	 
			 int choice = sc.nextInt();
			 sc.nextLine(); // Consume the Leftover newline   
			 
			 if(choice<1 || choice > 8)
			 {
				 System.out.println("Invalid Choice entered !! Exiciting now ");
				 break ;
			 }
			
			 switch(choice)
			 {  
			     case 1 :
		    	         System.out.println("\n Creating Database... ");	
		    	         System.out.println("Enter your Database Name : ");
		    			 dbName = sc.nextLine();         
		    	       //  Create.createDatabase(userName, passWord, dbName);
		    	         break;
		    	    
			     case 2 :
		    	        System.out.println("\n Creating Table ... ");	
		    		//	Create.createTable(userName, passWord, dbName, tableName);
		    	        break;
			 
			    case 3 :
			    	    System.out.println("\n Inserting Data ... ");	
			    	    System.out.println("Enter Id : ");
			    	    int id = sc.nextInt();
			    	    sc.nextLine();   // 
			    	    System.out.println("Enter Name : ");	
			    	    String name = sc.nextLine();
			    	    System.out.println("Enter Age : ");	
			    	    int age = sc.nextInt();
			    	    sc.nextLine(); 
			    	    System.out.println("Enter Branch : ");	
			    	    String branch = sc.nextLine();
			    	    System.out.println("Enter Address : ");	
			    	    String address = sc.nextLine();    	    
			    	 //   Insert.insertTable(userName, passWord, dbName, tableName, id, name, age, branch, address);
			    	    break;
			    	    
			    case 4 : 
			    	    System.out.println("\n Retrieving Information... ");    
		    	     //   Retrieve.readTable(userName, passWord, tableName, dbName);
		    	        break;
		    	    
			    case 5 : 
			    	    System.out.println("\n Updating Information... ");    
		    	        System.out.println("Enter Id : ");
			    	    id = sc.nextInt();
			    	    System.out.println("Enter New Age : ");
			    	    age = sc.nextInt();
		    	     //   Update.updateTable(userName, passWord, dbName, tableName, id, age);
		    	        break;
		    	    
			    case 6 : 
			    	    System.out.println("\n Deleting Information... ");    
		    	        System.out.println("Enter Id : ");
			    	    id = sc.nextInt();
		    	     //   Delete.deleteTable(userName, passWord, dbName, tableName, id);
		    	        break;
		    	        
			    case 7 : 
	   		            System.out.println("\n Enter Your Own Querry : ");
	   		            String query = sc.nextLine();
	   		            System.out.println("Querry entered by you : "+query);
	   		            break;        
		    	    
			    case 8 : 
		    		    System.out.println("\n Exiting !! ");
		    		    System.exit(0) ;
		    		    break;
			     }
			
			}	
			
		}
		else
		{
			System.out.println("  Connection Failed !! ");
			System.out.println("  Wrong Credential \n");
			System.out.println("  Enter Credential Again \n");
		//	goto (line14) ;
		}
		
		
		sc.close();
	}

	public static void main(String[] args) { new Main(); }

}

