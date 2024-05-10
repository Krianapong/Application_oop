import java.sql.*;

public class DBConnect {
    public Connection conn = null;
    private Statement stm;
    private ResultSet rs;

    private String url = "jdbc:mysql://localhost:3306/project_test";
    private String user = "root";
    private String pass = "22419";

    public DBConnect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
            stm = conn.createStatement(); // Initialize stm here
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            stm = conn.createStatement();
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
