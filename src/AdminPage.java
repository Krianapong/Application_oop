import javax.swing.*;

public class AdminPage extends JFrame {
    public AdminPage() {
        setTitle("Admin Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to Admin Page");
        panel.add(titleLabel);

        add(panel);

        setLocationRelativeTo(null); // Center the frame on the screen
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
