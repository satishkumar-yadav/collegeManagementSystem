package collegeManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JOptionPane;

public class Validate {

    // Method to validate if age is within the specified range
    public boolean validateChecks(LocalDate dob, int minAge, int maxAge) {      // earlier --  public boolean validateChecks(LocalDate dob, int minAge, int maxAge) {
        int age = calculateAge(dob);
        return age >= minAge && age <= maxAge;
    }

    // Overloaded method to validate based on year of birth
    public boolean validateChecks(int yearOfBirth, int minAge, int maxAge) {
        LocalDate dob = LocalDate.of(yearOfBirth, 1, 1);
        return validateChecks(dob, minAge, maxAge);
    }

    // Method to calculate age based on date of birth
    public int calculateAge(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears();
    }
    
 // Helper method to calculate age
    public int calculatedAge(String dob) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDate = dateFormat.parse(dob);
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--; // Adjust if birthday hasn't occurred yet this year
        }

        return age;
    }

    


        public boolean validateMobileNumber(String mobileNumber) {
            // Check if the input is a 10-digit number
            if (!mobileNumber.matches("\\d{10}")) {
               // System.out.println("Invalid number. A mobile number must be exactly 10 digits.");
                JOptionPane.showMessageDialog(null, "Phone Number Must be 10 Digits and Only Numeric!", "Error", JOptionPane.ERROR_MESSAGE);
              //  return false;
                return true;
            }

            // Check if the number starts with 6, 7, 8, or 9
            if (!mobileNumber.matches("^[6-9].*")) {
                JOptionPane.showMessageDialog(null, "Invalid number. A mobile number must start with 6, 7, 8, or 9!", "Error", JOptionPane.ERROR_MESSAGE);
               // return false;
                return true;
            }

            // Check if the number contains a reverse sequence of continuous digits
            String reverseContinuousPattern = "9876543210";
            if (mobileNumber.contains(reverseContinuousPattern)) {
                JOptionPane.showMessageDialog(null, "Invalid number. A mobile number cannot contain reverse continuous digits like 9876543210!", "Error", JOptionPane.ERROR_MESSAGE);
              //  return false;
                return true;
            }
            
            // Validate phone number for continuous digits or repeated digits
    	    if (containsContinuousDigit(mobileNumber) ) {
    	        JOptionPane.showMessageDialog(null, "Phone Number Cannot Contain More than 3 Continuous Digits !", "Error", JOptionPane.ERROR_MESSAGE);
    	        return true ;
    	    }
    	    if (containsRepeatedDigit(mobileNumber)) {
    	        JOptionPane.showMessageDialog(null, "Phone Number Cannot Contain More Than 3 Repeated Digits!", "Error", JOptionPane.ERROR_MESSAGE);
    	        return true ;
    	    }

            // If all checks pass
          //  System.out.println("Valid mobile number.");
           // return true;
            return false;
        }
    
    
    // Method to check if a number contains continuous digits
    public boolean containsContinuousDigits(String number) {
        for (int i = 0; i < number.length() - 1; i++) {
            if (Math.abs(number.charAt(i) - number.charAt(i + 1)) == 1) {
                return true;
            }
        }
        return false;
    }

    
 // Helper method to check for continuous digits in the phone number
    public boolean containsContinuousDigit(String number) {
        for (int i = 0; i < number.length() - 3; i++) {
            if (number.charAt(i) + 1 == number.charAt(i + 1) &&
            	number.charAt(i + 1) + 1 == number.charAt(i + 2) &&
            	number.charAt(i + 2) + 1 == number.charAt(i + 3)) {
                return true; // Found 4 continuous digits
            }
        }
        return false;
    }
    
    
    // Overloaded method for integer input
    public boolean containsContinuousDigits(long number) {
        return containsContinuousDigits(String.valueOf(number));
    }
    
    public boolean containsContinuousDigit(long number) {
        return containsContinuousDigit(String.valueOf(number));
    }
    

    // Method to check if a number contains repeated digits more than three times
    public boolean containsRepeatedDigits(String number) {
        int[] digitCount = new int[10];   // Array to count digits (0-9)     int[] count = new int[10];
        for (char c : number.toCharArray()) {
            digitCount[c - '0']++;            // int digit = Character.getNumericValue(c);  count[digit]++; // Increment count for the digit
            if (digitCount[c - '0'] > 3) {   // Check if any digit is repeated more than 3 times
                return true;
            }
        }
        return false;
    }
    
    // above code correction
    public boolean containRepeatedDigits(String input) {
        int[] count = new int[10]; // Array to count digits (0-9)

        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) { // Ensure the character is a digit
                int digit = Character.getNumericValue(c);
                count[digit]++; // Increment count for the digit
                if (count[digit] > 3) { // Check if any digit is repeated more than 3 times
                    return true;
                }
            }
        }
        return false;
    }

    
    
 // Helper method to check for repeated digits in the phone number  above method equivalent
    public boolean containsRepeatedDigit(String number) {
        for (int i = 0; i < number.length() - 3; i++) {
            if (number.charAt(i) == number.charAt(i + 1) &&
            	number.charAt(i + 1) == number.charAt(i + 2) &&
            	number.charAt(i + 2) == number.charAt(i + 3)) {
                return true; // Found 4 repeated digits
            }
        }
        return false;
    }
    

    // Overloaded method for integer input
    public boolean containsRepeatedDigits(long number) {
        return containsRepeatedDigits(String.valueOf(number));
    }
    
    public boolean containsRepeatedDigit(long number) {
        return containsRepeatedDigit(String.valueOf(number));
    }

    // Composite validation for Aadhaar/phone number
    public boolean validateNumber(String number, boolean allowContinuous, boolean allowRepeated) {
        if (!allowContinuous && containsContinuousDigits(number)) {
            return false;
        }
        if (!allowRepeated && containsRepeatedDigits(number)) {
            return false;
        }
        return true;
    }
    
    
 
    
    
    
    
    
}


/*
 
 Regex for Starting Digit Validation:
"^[6-9].*" ensures the number starts with 6, 7, 8, or 9.

Regex for Length Validation:
"\\d{10}" ensures the number has exactly 10 digits.

Reverse Continuous Digits Check:
The pattern "9876543210" is checked using String.contains()
 
 */



//3. Explanation of Overloading
//validateChecks:
//Overloaded to accept either a LocalDate object or a year (as an integer) to calculate age.

//containsContinuousDigits:
//Overloaded to accept either a String or a long for checking continuous digits.

//containsRepeatedDigits:
//Overloaded to accept either a String or a long for checking repeated digits.

//validateNumber:
//Composite method to validate a number for both continuous and repeated digits, configurable through parameters.


//4. Advantages
//Reusability: The validation methods can be reused in different contexts like students, teachers, and other entities.
//Flexibility: Overloading provides flexibility to handle different data types (e.g., String vs. long) and validation rules.
//Modularity: Clean separation of validation logic makes the code easier to maintain and extend.
//Scalability: Additional validations or use cases can be easily added without affecting existing code.




/*
 
 
 2. Usage of Validate Class
Below are some examples of how to use the Validate class in different contexts.

a) Validate Age for Students

import javax.swing.JOptionPane;
import java.time.LocalDate;

public class AddStudent {
    private Validate validator = new Validate();

    public void addStudent(String name, LocalDate dob, String phoneNumber, String aadhaarNumber) {
        try {
            // Validate age
            if (!validator.validateChecks(dob, 18, 40)) {
                JOptionPane.showMessageDialog(null, "Age must be between 18 and 40.");
                return;
            }

            // Validate phone number
            if (!validator.validateNumber(phoneNumber, false, false)) {
                JOptionPane.showMessageDialog(null, "Phone number is invalid.");
                return;
            }

            // Validate Aadhaar number
            if (!validator.validateNumber(aadhaarNumber, true, false)) {
                JOptionPane.showMessageDialog(null, "Aadhaar number is invalid.");
                return;
            }

            // Proceed with adding student logic
            JOptionPane.showMessageDialog(null, "Student added successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add student. Error: " + e.getMessage());
        }
    }
}



b) Validate Phone Numbers for Teachers


import javax.swing.JOptionPane;

public class AddTeacher {
    private Validate validator = new Validate();

    public void addTeacher(String name, String phoneNumber) {
        try {
            // Validate phone number
            if (!validator.validateNumber(phoneNumber, false, false)) {
                JOptionPane.showMessageDialog(null, "Phone number is invalid.");
                return;
            }

            // Proceed with adding teacher logic
            JOptionPane.showMessageDialog(null, "Teacher added successfully!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to add teacher. Error: " + e.getMessage());
        }
    }
}

 
 
 
 
 
 
 
 */






