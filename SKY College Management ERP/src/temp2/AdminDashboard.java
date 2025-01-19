package temp2;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDashboard extends JFrame {
    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Course"}, 0);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 500, 200);
        add(scrollPane);

        JButton approveButton = new JButton("Approve");
        approveButton.setBounds(150, 300, 100, 40);
        add(approveButton);

        JButton rejectButton = new JButton("Reject");
        rejectButton.setBounds(350, 300, 100, 40);
        add(rejectButton);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345678");
            String sql = "SELECT * FROM temp_students";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("course")});
            }

            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        approveButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345678");
                    String copySql = "INSERT INTO students (name, age, course) SELECT name, age, course FROM temp_students WHERE id = ?";
                    PreparedStatement copyPs = conn.prepareStatement(copySql);
                    copyPs.setInt(1, id);
                    copyPs.executeUpdate();

                    String deleteSql = "DELETE FROM temp_students WHERE id = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setInt(1, id);
                    deletePs.executeUpdate();

                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Student Approved!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        rejectButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                int id = (int) model.getValueAt(selectedRow, 0);
                try {
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "12345678");
                    String deleteSql = "DELETE FROM temp_students WHERE id = ?";
                    PreparedStatement deletePs = conn.prepareStatement(deleteSql);
                    deletePs.setInt(1, id);
                    deletePs.executeUpdate();

                    model.removeRow(selectedRow);
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Student Rejected!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        setVisible(true);
    }
}

