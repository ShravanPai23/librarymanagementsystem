import java.sql.*;

public class BookDAO {

    public void addBook(String title, String author) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO books (title, author) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewBooks() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String sql = "SELECT * FROM books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println(rs.getInt("book_id") + " | " +
                                   rs.getString("title") + " | " +
                                   rs.getString("author") + " | " +
                                   (rs.getBoolean("available") ? "Available" : "Borrowed"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
