import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Service.UserService;
import dao.UserDAO;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.SQLException;
import java.util.List;

public class UserServiceTest {
    private UserDAO userDAO;
    private UserService userService;

    @BeforeEach
    public void setUp() {
        userDAO = Mockito.mock(UserDAO.class);
        userService = new UserService(userDAO);
    }

    @Test
    public void testRegisterUser() throws SQLException {
        User user = new User(1, "John Doe", "john.doe@example.com");
        userService.registerUser(user);
        verify(userDAO, times(1)).addUser(user);
    }

    @Test
    public void testGetUser() throws SQLException {
        User user = new User(1, "John Doe", "john.doe@example.com");
        when(userDAO.getUser(1)).thenReturn(user);

        User result = userService.getUser(1);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    public void testRegisterUserSQLException() throws SQLException {
        User user = new User(1, "John Doe", "john.doe@example.com");
        doThrow(new SQLException("Database error")).when(userDAO).addUser(user);

        Exception exception = assertThrows(SQLException.class, () -> {
            userService.registerUser(user);
        });

        String expectedMessage = "Database error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
