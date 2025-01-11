package testpackage2;

//Import ClassA from package1
import testpackage.testClassA;

public class testClassB {

	public static void main(String[] args) {
	     // Create an instance of ClassA
	     testClassA obj = new testClassA();
	     
	     // Call the method from ClassA
	     obj.displayMessage();
	     System.out.println("hi Second commit");
	 }
}




