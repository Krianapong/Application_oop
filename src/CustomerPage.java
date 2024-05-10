import javax.swing.*;

public class CustomerPage extends JFrame {
    public CustomerPage() {
        setTitle("Customer Page");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and add components
        JPanel panel = new JPanel();
        JLabel titleLabel = new JLabel("Welcome to Customer Page");
        panel.add(titleLabel);

        add(panel);

        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerPage customerPage = new CustomerPage();
                customerPage.setVisible(true);
            }
        });
    }
}
