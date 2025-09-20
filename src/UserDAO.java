import java.sql.*;

public class UserDAO {

    // Add new user
    public void addUser(String name, String email) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("User added successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Email already exists!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all users
    public void viewUsers() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM users";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("user_id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
