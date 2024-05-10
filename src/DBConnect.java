import java.sql.*;

public class DBConnect {
    public Connection conn = null;
    public Statement stm;
    public ResultSet rs;

    public String url = "jdbc:mysql://localhost:3306/project_test";
    public String user = "root";
    public String pass = "22419";

    public DBConnect() {
        connect(); // Connect to the database
    }

    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement(); // Initialize stm
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            rs = stm.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void disconnect() {
        try {
            stm.close();
            conn.close();
            System.out.println("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}