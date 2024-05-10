import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.*;

public class Show_User extends JFrame {
    private DBConnect db;

    public Show_User() {
        setTitle("User List");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        db = new DBConnect(); // Initialize DBConnect

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create table to display user data
        JTable table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Add main panel to the frame
        add(mainPanel);

        // Set frame properties
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Fetch user data from the database and populate the table
        populateTable(table);
    }

    // Method to fetch user data from the database and populate the table
    // Method to fetch user data from the database and populate the table
    private void populateTable(JTable table) {
        ResultSet resultSet = null;
        try {
            // SQL query to select user data
            String query = "SELECT `user`.`iduser`, `user`.`username`, `user`.`password`, `user`.`email`, `user`.`phone` FROM `project_test`.`user`";

            // Execute query and get the result set
            resultSet = db.executeQuery(query);

            if (resultSet != null) {
                // Create table model with column names
                DefaultTableModel model = new DefaultTableModel();
                model.setColumnIdentifiers(new String[]{"ID", "Username", "Password", "Email", "Phone"});
                table.setModel(model);

                // Populate the table with data from the result set
                while (resultSet.next()) {
                    int id = resultSet.getInt("iduser");
                    String username = resultSet.getString("username");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone");

                    // Add row to the table model
                    model.addRow(new Object[]{id, username, password, email, phone});
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            // Close ResultSet and DBConnect
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            db.disconnect(); // Disconnect from the database
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Show_User showUser = new Show_User();
                showUser.setVisible(true);
            }
        });
    }
}
