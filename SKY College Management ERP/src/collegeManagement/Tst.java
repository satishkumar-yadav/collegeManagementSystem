package collegeManagement;

public class Tst {

}



/*
  
  
  // Add an ItemListener to jcbrole
jcbrole.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String selectedRole = (String) jcbrole.getSelectedItem();
        
        // Enable jcbrole2 if "Faculty" is selected, otherwise disable it
        if ("Faculty".equals(selectedRole)) {
            jcbrole2.setEnabled(true);
        } else {
            jcbrole2.setEnabled(false);
            jcbrole2.setSelectedIndex(0); // Reset the subrole to "Select"
        }
    }
});

// Updated blogin logic
if (ae.getSource() == blogin) {
    String userName = tfusername.getText().trim();
    String passWord = tfpassword.getText().trim();
    String Role = (String) jcbrole.getSelectedItem();
    
    // If "Faculty" is selected, get the role from jcbrole2
    if ("Faculty".equals(Role)) {
        Role = (String) jcbrole2.getSelectedItem();
        if ("Select".equals(Role)) {
            JOptionPane.showMessageDialog(this, "Please select a sub-role for Faculty.");
            return; // Stop further execution if no sub-role is selected
        }
    }

    String querylogin = "SELECT * FROM login WHERE role = ? AND username = ? AND password = ?";
    try {
        JdbcConnection con = new JdbcConnection();
        PreparedStatement pstmt = con.con.prepareStatement(querylogin);
        pstmt.setString(1, Role);
        pstmt.setString(2, userName);
        pstmt.setString(3, passWord);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            JOptionPane.showMessageDialog(this, "Login Successful!");
            setVisible(false);

            switch (Role) {
                case "Admin":
                    new Admin();
                    break;
                case "Accountant":
                    new Accountant();
                    break;
                case "Teacher":
                    new Teacher();
                    break;
                case "Student":
                    new Student();
                    break;
                case "Librarian":
                    new Librarian();
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Unknown Role");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Login Failed! Wrong Credentials.");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


Key Changes Explained
1. ItemListener for jcbrole
This listener activates or deactivates jcbrole2 based on the selection in jcbrole:

jcbrole.addItemListener(e -> {
    if (e.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
        String selectedRole = (String) jcbrole.getSelectedItem();
        if ("Faculty".equals(selectedRole)) {
            jcbrole2.setEnabled(true); // Enable sub-role dropdown for Faculty
        } else {
            jcbrole2.setEnabled(false); // Disable sub-role dropdown for other roles
            jcbrole2.setSelectedIndex(0); // Reset sub-role to "Select"
        }
    }
});


2. Validation for Sub-role Selection
In the blogin logic, if "Faculty" is selected, the role is fetched from jcbrole2. If no sub-role is selected ("Select"), the program prompts the user to select a sub-role:

if ("Faculty".equals(Role)) {
    Role = (String) jcbrole2.getSelectedItem();
    if ("Select".equals(Role)) {
        JOptionPane.showMessageDialog(this, "Please select a sub-role for Faculty.");
        return; // Stop further execution if no sub-role is selected
    }
}


3. Disable jcbrole2 for Non-Faculty Roles
When the user selects any role other than "Faculty," the sub-role dropdown (jcbrole2) is disabled and reset to "Select":

if (!"Faculty".equals(selectedRole)) {
    jcbrole2.setEnabled(false);
    jcbrole2.setSelectedIndex(0); // Reset to default value
}


if (!"Faculty".equals(selectedRole)) {
    jcbrole2.setEnabled(false);
    jcbrole2.setSelectedIndex(0); // Reset to default value
}


How This Works
For "Faculty" Role:
Activates jcbrole2 and requires the user to select a specific sub-role (e.g., "Teacher," "Librarian").
Uses the value from jcbrole2 as the role for login validation.
For Other Roles:
Disables jcbrole2 and directly uses the selected value from jcbrole as the role.

Final Workflow
User selects "Faculty" in jcbrole:
jcbrole2 becomes active, allowing them to select a sub-role.
Sub-role value (e.g., "Teacher") is used as the role in login logic.
User selects any other role (e.g., "Admin"):
jcbrole2 is disabled, and the role value is taken directly from jcbrole.
Let me know if this solves the issue or if further adjustments are needed!
  

  
 */






