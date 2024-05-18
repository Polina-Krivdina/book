package Service;

import dao.BookDAO;
import model.Book;

import java.sql.SQLException;

public class BookService {
    private BookDAO bookDAO;

    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    public Book getBook(int id) throws SQLException {
        return bookDAO.getBook(id);
    }

    // Другие методы для бизнес-логики, связанные с книгами
}

