package testpackage2;

// without import, using testpackage method

public class testClassC {

	public static void main(String[] args) {
    	testpackage.testClassA.displayMessage2(); // accessible only if displayMessage() in classA is static , without creating obj of testClassA
    }
}





/*

A.Compile and Run the Program
  If your folder structure looks like this:

src
├── package1
│   └── ClassA.java
├── package2
    └── ClassB.java
    
    
1.Navigate to the src directory in the terminal.

2.Compile all .java files:
   javac package1/ClassA.java package2/ClassB.java

3.Run the main class:
    java package2.ClassB

B.Important Points
  1.Access Modifiers:
    a. Ensure the method in ClassA is public so it can be accessed from another package.
    b. If the method is protected, it can only be accessed in a subclass (via inheritance) in another package.
    
  2.Class Import: Use the import statement to make the class available in another package. Without import, you'll need to use the 
    fully qualified class name (package1.ClassA), which can be verbose.

  3.Package Declaration: Ensure each class has the correct package declaration at the top of the file (e.g., package package1;).

  4.Folder Structure: Java's package structure should match the directory structure.

  5.Static Methods: If the method in ClassA is static, you don't need to create an instance of the class. You can call it directly using 
    the class name:    


*/