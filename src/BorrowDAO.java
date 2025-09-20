import java.sql.*;
import java.time.LocalDate;

public class BorrowDAO {

    // Borrow a book
    public void borrowBook(int userId, int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if book is available
            String checkSql = "SELECT available FROM books WHERE book_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                boolean available = rs.getBoolean("available");
                if (!available) {
                    System.out.println("Book is already borrowed!");
                    return;
                }
            } else {
                System.out.println("Book not found!");
                return;
            }

            // Update book availability
            String updateBook = "UPDATE books SET available = FALSE WHERE book_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateBook);
            updateStmt.setInt(1, bookId);
            updateStmt.executeUpdate();

            // Insert into borrow table
            String insertBorrow = "INSERT INTO borrow (user_id, book_id, borrow_date) VALUES (?, ?, ?)";
            PreparedStatement borrowStmt = conn.prepareStatement(insertBorrow);
            borrowStmt.setInt(1, userId);
            borrowStmt.setInt(2, bookId);
            borrowStmt.setDate(3, Date.valueOf(LocalDate.now()));
            borrowStmt.executeUpdate();

            System.out.println("Book borrowed successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Return a book
    public void returnBook(int userId, int bookId) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            // Check if this book is borrowed by this user
            String checkSql = "SELECT borrow_id FROM borrow WHERE user_id = ? AND book_id = ? AND return_date IS NULL";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int borrowId = rs.getInt("borrow_id");

                // Update borrow table
                String updateBorrow = "UPDATE borrow SET return_date = ? WHERE borrow_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateBorrow);
                updateStmt.setDate(1, Date.valueOf(LocalDate.now()));
                updateStmt.setInt(2, borrowId);
                updateStmt.executeUpdate();

                // Update books table
                String updateBook = "UPDATE books SET available = TRUE WHERE book_id = ?";
                PreparedStatement bookStmt = conn.prepareStatement(updateBook);
                bookStmt.setInt(1, bookId);
                bookStmt.executeUpdate();

                System.out.println("Book returned successfully!");
            } else {
                System.out.println("No borrowed record found for this user and book.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
