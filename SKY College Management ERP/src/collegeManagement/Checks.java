package collegeManagement;

import java.io.File;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Checks 
{

	private Validate validator = new Validate();
	
	public static boolean validateChecks(String name, String fname, String phone, String aadhar, String email, String x, String xii, String gender, String address, String qualification, File selectedFile,  String course, String branch , JTextField tfxii , String id , String doj )
	{
		 // Create an instance of Checks to access the validator instance without making it static
          Checks checksInstance = new Checks();
		
		
	    if (name.isBlank() || gender.isEmpty() || aadhar.isBlank() || address.isBlank() || fname.isBlank() || phone.isBlank() || "Select Course".equals(course) || "Select Branch".equals(branch) || "Select Qualification".equals(qualification) || " - - - - - - - ".equals(id) || x.isBlank() || selectedFile == null || doj.isEmpty() )
	    {
	        JOptionPane.showMessageDialog(null, "All * Marked Fields are Mandatory!, Including Profile Picture.");
	        return false ;
	    }

	    if (!Pattern.matches("[a-z A-Z.]{2,}", name)) {
	        JOptionPane.showMessageDialog(null, "Please Enter Valid Name. It Must be Alphabet Only !", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    if (!Pattern.matches("[a-z A-Z.]{2,}", fname)) {
	        JOptionPane.showMessageDialog(null, "Please Enter Valid Father's Name. It Must be Alphabet Only !", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    if (name.equalsIgnoreCase(fname)) {
	        JOptionPane.showMessageDialog(null, "Name and Father's Name Cannot be Same !", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    
//	    if (!Pattern.matches("\\d{10}", phone)) {
//	        JOptionPane.showMessageDialog(null, "Phone Number Must be 10 Digits and Only Numeric!", "Error", JOptionPane.ERROR_MESSAGE);
//	        return false ;
//	    }
//
//	    // Validate phone number for continuous digits or repeated digits
//	    if (checksInstance.validator.containsContinuousDigit(phone) ) {
//	        JOptionPane.showMessageDialog(null, "Phone Number Cannot Contain More than 3 Continuous Digits !", "Error", JOptionPane.ERROR_MESSAGE);
//	        return false ;
//	    }
//	    if (checksInstance.validator.containsRepeatedDigit(phone)) {
//	        JOptionPane.showMessageDialog(null, "Phone Number Cannot Contain More Than 3 Repeated Digits!", "Error", JOptionPane.ERROR_MESSAGE);
//	        return false ;
//	    }
	    
	    if (checksInstance.validator.validateMobileNumber(phone)) {
	      //  JOptionPane.showMessageDialog(null, "Phone Number Cannot Contain More Than 3 Repeated Digits!", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    
	 // Validate aadhar number for continuous digits or repeated digits
	    if (checksInstance.validator.containsContinuousDigit(aadhar) ) {
	        JOptionPane.showMessageDialog(null, "Aadhaar Number Cannot Contain More than 3 Continuous Digits !", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    if (checksInstance.validator.containsRepeatedDigit(aadhar)) {
	        JOptionPane.showMessageDialog(null, "Aadhaar Number Cannot Contain More Than 3 Repeated Digits!", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }

	    if (!Pattern.matches("\\d{12}", aadhar)) {
	        JOptionPane.showMessageDialog(null, "Aadhar Number Must be 12 Digits Numeric Only!", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
                                                                      // length after.  // earlier regex : [a-zA-Z0-9._%+-]{6,}+@[a-zA-Z0-9.-]{4,}+\\.[a-zA-Z]{3,}           
	    if (!Pattern.matches("[a-zA-Z0-9._%+-]{3,}@[a-zA-Z0-9.-]{4,}\\.[a-zA-Z]{3,}", email)) {   
	        JOptionPane.showMessageDialog(null, "Please Enter a Valid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }

	    try {
	        // Validate age is between 18 and 40
//	        int age = checksInstance.validator.calculatedAge(dob);
//	        if (age < 18 || age > 40) {
//	            JOptionPane.showMessageDialog(null, "Age Must Be Between 18 and 40 Years.", "Error", JOptionPane.ERROR_MESSAGE);
//	            return false ;
//	        }

	        double percentageX = Double.parseDouble(x);
	        if (percentageX < 35 || percentageX > 100) {
	            JOptionPane.showMessageDialog(null, "Class X Percentages Must be Between 35 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
	            return false ;
	        }
	        if (tfxii.isEnabled()) {
	            double percentageXII = Double.parseDouble(xii);
	            if (percentageXII < 35 || percentageXII > 100) {
	                JOptionPane.showMessageDialog(null, "Class XII Percentages Must be Between 35 and 100.", "Error", JOptionPane.ERROR_MESSAGE);
	                return false ;
	            }
	        }
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(null, "Class X and XII Percentages Must be Numeric.", "Error", JOptionPane.ERROR_MESSAGE);
	        return false ;
	    }
	    
//	    try {
//            // Validate age
////            if (!validator.validateChecks(dob, 18, 40)) {
////                JOptionPane.showMessageDialog(null, "Age must be between 18 and 40.");
////                return;
////            }
//
//            // Validate phone number
//            if (!validator.validateNumber(phone, false, false)) {
//                JOptionPane.showMessageDialog(null, "Phone number is invalid.");
//                return;
//            }
//
//            // Validate Aadhaar number
//            if (!validator.validateNumber(aadhar, true, false)) {
//                JOptionPane.showMessageDialog(null, "Aadhaar number is invalid.");
//                return;
//            }
//
//            
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//        }
	    
	    return true;
	    
	} // method validateChecks end 
	
	
}







/*
 
 
 The issue arises because you're attempting to use a non-static validator instance inside a static method. Non-static fields cannot 
 be directly accessed from a static context. To fix this issue without making the validator field static, you need to either:

1.Pass an instance of Checks to access validator.
 - pass an instance of the Checks class to the static validateChecks method as a parameter. This instance will allow access to the 
 non-static validator field.
 public class Checks 
 {

    private Validate validator = new Validate();

    // Pass an instance of `Checks` to the static method
    public static void validateChecks(
            Checks checksInstance, // Added `Checks` instance as a parameter
            String name, 
            String fname
            .....  )
            {
            
             int age = checksInstance.validator.calculateAge(dob); 
             if (age < 18 || age > 40) 
               {
                JOptionPane.showMessageDialog(null, "Age Must Be Between 18 and 40 Years.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
                }

            }
     
}     


2.Create an instance of Checks inside the validateChecks method to access validator.
 public class Checks 
 {

    private Validate validator = new Validate();

    public static void validateChecks(String name, String fnaame....) 
    {
        // Create an instance of Checks to access the validator instance
        Checks checksInstance = new Checks();
 
        int age = checksInstance.validator.calculateAge(dob); 
        if (age < 18 || age > 40) 
            {
                JOptionPane.showMessageDialog(null, "Age Must Be Between 18 and 40 Years.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

     }
     
}     


Changes Made (approach 2)
1.Created an instance of Checks inside the static method validateChecks:
------------------------------------------------------------------
Checks checksInstance = new Checks();
--------------------------------------------------------------------
This allows access to the non-static validator field.

2.Used checksInstance.validator to call methods:
- Changed all calls to validator to:
-------------------------------------------------------------
checksInstance.validator.containsContinuousDigits(phone)
----------------------------------------------------------------
This avoids directly accessing the non-static field from a static context.

3.Fixed method name typo:
- Corrected validator.calculatedAge to validator.calculateAge.

4.No changes were made to make validator static, as per your requirement.

Explanation
- Static Context: A static method cannot directly access non-static fields or methods. To use them, you need an instance of the class.
- Instance Creation: By creating an instance of Checks, you can access the validator instance and use its methods without making it 
  static.
 
 
 approach 1
 How It Works
1.Added Checks checksInstance as a parameter:
- This allows the caller to pass an instance of the Checks class.
- The static method can then use this instance to access the validator field.

2.Used checksInstance.validator to call methods:
- For example:
-------------------------------------------------------------------------------------------------------------------------------
if (checksInstance.validator.containsContinuousDigits(phone) || checksInstance.validator.containsRepeatedDigits(phone)) {
-------------------------------------------------------------------------------------------------------------------------------
This accesses the non-static validator instance from the Checks object passed as a parameter.

3.No changes to the validator field:
- It remains a non-static field.

 public class Main {
    public static void main(String[] args) {
        // Create an instance of Checks
        Checks checksInstance = new Checks();

        // Call validateChecks and pass the instance
        Checks.validateChecks(
            checksInstance, // Pass the instance
            "John", 
            ----------
            new JTextField("85")
                );
    }
}


Pros and Cons of This Approach
Pros
Keeps the validator field non-static, which ensures instance-based encapsulation and thread safety.
Simple to use by just passing an instance to the static method.
Cons
Requires passing an additional parameter (checksInstance) every time the method is called.


 
 */







