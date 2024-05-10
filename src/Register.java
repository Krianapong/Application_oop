import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Register extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private DBConnect db;

    public Register() {
        super("Register");

        db = new DBConnect(); // Initialize DBConnect

        // Create components
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel phoneLabel = new JLabel("Phone:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back"); // Back button

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
        add(emailLabel, gbc);
        gbc.gridy++;
        add(emailField, gbc);
        gbc.gridy++;
        add(phoneLabel, gbc);
        gbc.gridy++;
        add(phoneField, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Align buttons to center
        add(registerButton, gbc);
        gbc.gridy++;
        add(backButton, gbc); // Add back button below register button

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String email = emailField.getText();
                String phone = phoneField.getText();

                // Register user in the database
                if (registerUser(username, password, email, phone)) {
                    JOptionPane.showMessageDialog(Register.this, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(Register.this, "Registration failed. Please try again.");
                }
            }
        });

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.setVisible(true);
                Register.this.dispose();
            }
        });

        // Set frame properties
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximize window to fit the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    // Method to register user in the database
    private boolean registerUser(String username, String password, String email, String phone) {
        try {
            String query = "INSERT INTO user (username, password, email, phone, type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = db.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, phone);
            preparedStatement.setString(5, "customer");

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.disconnect(); // Disconnect from the database
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Register();
            }
        });
    }
}