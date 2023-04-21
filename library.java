// Import required libraries for JDBC connection
import java.sql.*;

public class library {
    private Connection conn;

    // Initialize the connection
    public library() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/issue", "root", "password");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Add a new book to the "books" table
    public void addBook(String title, String author, String isbn, int numCopiesAvailable) {
        try {
            String query = "INSERT INTO books (title, author, isbn, num_copies_available, num_copies_borrowed) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.setInt(4, numCopiesAvailable);
            stmt.setInt(5, 0);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Update the details of a book in the "books" table
    public void updateBook(String isbn, int numCopiesAvailable, int numCopiesBorrowed) {
        try {
            String query = "UPDATE books SET num_copies_available = ?, num_copies_borrowed = ? WHERE isbn = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, numCopiesAvailable);
            stmt.setInt(2, numCopiesBorrowed);
            stmt.setString(3, isbn);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // View the list of books available in the library
    public ResultSet viewBooks() {
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM books WHERE num_copies_available > 0";
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }
    // View the list of books borrowed by a student
    public ResultSet viewBorrowedBooks(String rollNumber) {
        ResultSet rs = null;
        try {
            String query = "SELECT books.title, books.author, books.isbn FROM books INNER JOIN borrowings ON books.isbn = borrowings.isbn WHERE borrowings.roll_number = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, rollNumber);
            rs = stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    }

    // Borrow a book from the library
    public void borrowBook(String rollNumber, String isbn) {
        try {
            String query = "INSERT INTO borrowings (roll_number, isbn) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, rollNumber);
            stmt.setString(2, isbn);
            stmt.executeUpdate();

            // Update the number of copies available and the number of copies borrowed for the book
            query = "UPDATE books SET num_copies_available = num_copies_available - 1, num_copies_borrowed = num_copies_borrowed + 1 WHERE isbn = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Return a book to the library
    public void returnBook(String rollNumber, String isbn) {
        try {
            String query = "DELETE FROM borrowings WHERE roll_number = ? AND isbn = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, rollNumber);
            stmt.setString(2, isbn);
            stmt.executeUpdate();

            // Update the number of copies available and the number of copies borrowed for the book
            query = "UPDATE books SET num_copies_available = num_copies_available + 1, num_copies_borrowed = num_copies_borrowed - 1 WHERE isbn = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, isbn);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}


