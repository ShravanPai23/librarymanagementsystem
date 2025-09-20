import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Replace with your actual Workbench connection details
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/library_db?serverTimezone=UTC";
    private static final String USER = "root";          // your username
    private static final String PASSWORD = "Smp2306#"; // your password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
