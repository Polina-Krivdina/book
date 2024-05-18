package dao;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private Connection connection;

    public BookDAO(Connection connection) {
        this.connection = connection;
    }

    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (id, title, author) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.executeUpdate();
        }
    }

    public Book getBook(int id) throws SQLException {
        String query = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author"));
            }
            return null;
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                books.add(new Book(rs.getInt("id"), rs.getString("title"), rs.getString("author")));
            }
        }
        return books;
    }

    // Методы для обновления и удаления книги
}

