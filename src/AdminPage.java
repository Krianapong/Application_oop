import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.*;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create sidebar panel
        JPanel sidebarPanel = new JPanel();
        sidebarPanel.setBackground(new Color(54, 63, 77)); // Set background color
        sidebarPanel.setLayout(new GridBagLayout());

        // Create sidebar components
        JButton dashboardButton = createSidebarButton("Dashboard", "icons/dashboard.png");
        JButton usersButton = createSidebarButton("Users", "icons/users.png");
        JButton productsButton = createSidebarButton("Products", "icons/products.png");
        JButton settingsButton = createSidebarButton("Settings", "icons/settings.png");
        JButton logout = createSidebarButton("Logout", "icons/logout.png");

        // Add sidebar components to the sidebar panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 1; // Vertical fill
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Add padding
        sidebarPanel.add(dashboardButton, gbc);
        gbc.gridy++;
        sidebarPanel.add(usersButton, gbc);
        gbc.gridy++;
        sidebarPanel.add(productsButton, gbc);
        gbc.gridy++;
        sidebarPanel.add(settingsButton, gbc);
        gbc.gridy++;
        sidebarPanel.add(logout, gbc); // Add logout button to sidebar

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Welcome to Admin Page");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set font
        titleLabel.setHorizontalAlignment(JLabel.CENTER); // Center align text
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Add sidebar and main panel to the frame
        add(sidebarPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null); // Center the frame on the screen

        // Add action listener for the logout button
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open login page
                Login login = new Login();
                login.setVisible(true);
                AdminPage.this.dispose();
            }
        });
    }

    // Method to create a styled sidebar button with icon
    private JButton createSidebarButton(String text, String iconPath) {
        JButton button = new JButton(text);
        button.setBackground(new Color(54, 63, 77)); // Set background color
        button.setForeground(Color.WHITE); // Set text color
        button.setBorderPainted(false); // Remove border
        button.setFocusPainted(false); // Remove focus border
        button.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font
        button.setIcon(new ImageIcon(iconPath)); // Set icon
        button.setHorizontalTextPosition(SwingConstants.RIGHT); // Place text to the right of icon
        button.setAlignmentX(Component.LEFT_ALIGNMENT); // Align button to the left
        button.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Change cursor on hover
        button.setContentAreaFilled(false); // Make content area transparent
        button.setBorder(new EmptyBorder(10, 20, 10, 20)); // Add padding
        button.setPreferredSize(new Dimension(180, 50)); // Set button size
        button.setMaximumSize(new Dimension(180, 50)); // Set maximum button size
        button.setIconTextGap(15); // Set gap between icon and text
        button.setOpaque(true); // Enable painting background color

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 41, 50));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(54, 63, 77));
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminPage adminPage = new AdminPage();
                adminPage.setVisible(true);
            }
        });
    }
}
