import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Service.BookService;
import dao.BookDAO;
import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.SQLException;

public class BookServiceTest {
    private BookDAO bookDAO;
    private BookService bookService;

    @BeforeEach
    public void setUp() {
        bookDAO = Mockito.mock(BookDAO.class);
        bookService = new BookService(bookDAO);
    }

    @Test
    public void testAddBook() throws SQLException {
        Book book = new Book(1, "Effective Java", "Joshua Bloch");
        bookService.addBook(book);
        verify(bookDAO, times(1)).addBook(book);
    }

    @Test
    public void testGetBook() throws SQLException {
        Book book = new Book(1, "Effective Java", "Joshua Bloch");
        when(bookDAO.getBook(1)).thenReturn(book);

        Book result = bookService.getBook(1);
        assertNotNull(result);
        assertEquals("Effective Java", result.getTitle());
        assertEquals("Joshua Bloch", result.getAuthor());
    }

    @Test
    public void testAddBookSQLException() throws SQLException {
        Book book = new Book(1, "Effective Java", "Joshua Bloch");
        doThrow(new SQLException("Database error")).when(bookDAO).addBook(book);

        Exception exception = assertThrows(SQLException.class, () -> {
            bookService.addBook(book);
        });

        String expectedMessage = "Database error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
