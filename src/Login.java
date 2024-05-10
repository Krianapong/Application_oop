import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private DBConnect db;

    public Login() {
        super("Login");

        db = new DBConnect(); // Initialize DBConnect

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register"); // New button for registering

        // Set layout to GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding

        // Add components to the frame
        add(usernameLabel, gbc);
        gbc.gridy++;
        add(usernameField, gbc);
        gbc.gridy++;
        add(passwordLabel, gbc);
        gbc.gridy++;
        add(passwordField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Align buttons to center
        add(loginButton, gbc);
        gbc.gridy++;
        add(registerButton, gbc); // Add register button below login button

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user against database
                String userType = authenticateUser(username, password);
                if (userType != null) {
                    // Login successful, open corresponding page
                    openPage(userType);
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid username or password. Please try again.");
                }
            }
        });

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open register page
                Register registerPage = new Register();
                registerPage.setVisible(true);
                Login.this.dispose();
            }
        });

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window to fit the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Method to authenticate user against database
    private String authenticateUser(String username, String password) {
        String query = "SELECT type FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet resultSet = db.executeQuery(query);

        try {
            if (resultSet.next()) {
                // User found in the database, return the user type
                return resultSet.getString("type");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disconnect(); // Disconnect from the database
        }

        return null;
    }

    // Method to open the corresponding page based on user type
    private void openPage(String userType) {
        if (userType.equals("admin")) {
            // Open admin page
            AdminPage adminPage = new AdminPage();
            adminPage.setVisible(true);
        } else if (userType.equals("customer")) {
            // Open customer page
            CustomerPage customerPage = new CustomerPage();
            customerPage.setVisible(true);
        }
        // Close the login window
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Login();
            }
        });
    }
}
