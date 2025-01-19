package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FeeStructure extends JFrame {
    
    FeeStructure() {
        setSize(1000, 700);
        setLocation(250, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Fee Structure");
        heading.setBounds(50, 10, 400, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);
        
        JTable table = new JTable();
        
        try {
        	JdbcConnection c1 = new JdbcConnection();
            ResultSet rs = c1.st.executeQuery("select * from fee");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // To disable auto resizing of columns
     // Adjust the column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Example: adjust width of the first column
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 60, 1000, 700);
        add(jsp);
        
        setVisible(true);
        
    }
    
    public static void main(String[] args) {
        new FeeStructure();
    }
}



/*

Closing Database Resources:
It's a good practice to close database resources (ResultSet, Statement, etc.) after use to avoid resource leaks. In your code, it's better to use try-with-resources or manually close the resources in a finally block.

 Table in FeeStructure:
Instead of directly calling DbUtils.resultSetToTableModel(rs) for the fee structure, you could enhance the table by adding a scroll bar, adjusting column widths, and adding headers dynamically.



*/