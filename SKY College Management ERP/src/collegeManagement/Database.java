package collegeManagement;

import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;

public class Database {

    // SQL scripts
    private static final String[] SQL_SCRIPT = DatabaseSqlScript.SQL_SCRIPT;
    private static final String[] SQL_SCRIPT2 = DatabaseSqlScriptInsert.SQL_SCRIPT2;

    // Method to create the database schema with progress bar
    public static void createDatabaseSchema() {
        // Create a modal loading dialog
        JDialog loadingDialog = new JDialog();
        loadingDialog.setTitle("Please Wait...");
        loadingDialog.setModal(true);
        loadingDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        loadingDialog.setSize(350, 150);
        loadingDialog.setLocationRelativeTo(null);

        // Create progress bar
        JProgressBar progressBar = new JProgressBar(0, SQL_SCRIPT.length + SQL_SCRIPT2.length);
        progressBar.setStringPainted(true);
        JLabel label = new JLabel("Creating Database Schema... Please wait.", SwingConstants.CENTER);

        // Add components to dialog
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(progressBar);
        loadingDialog.add(panel);
        
        // Use SwingWorker to execute SQL scripts in the background
        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    JdbcConnection jdc = new JdbcConnection();
                    Connection con = jdc.getConnection();
                    Statement stmt = con.createStatement();

                    int progress = 0;

                    // Execute first set of SQL scripts
                    for (String sql : SQL_SCRIPT) {
                        stmt.execute(sql.trim());
                        publish(++progress);
                    }

                    // Execute second set of SQL scripts
                    for (String sql2 : SQL_SCRIPT2) {
                        stmt.execute(sql2.trim());
                        publish(++progress);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    SwingUtilities.invokeLater(() -> {
                        loadingDialog.dispose();
                        JOptionPane.showMessageDialog(null, "Failed to Create Database Schema: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    });
                }
                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                // Update progress bar
                progressBar.setValue(chunks.get(chunks.size() - 1));
            }

            @Override
            protected void done() {
                // Close loading dialog and show success message
                loadingDialog.dispose();
                JOptionPane.showMessageDialog(null, "Database Schema Created Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        };

        // Start worker and show the loading dialog
        worker.execute();
        loadingDialog.setVisible(true);
    }
}

