import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import Service.OrderService;
import dao.OrderDAO;
import model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.sql.SQLException;

public class OrderServiceTest {
    private OrderDAO orderDAO;
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderDAO = Mockito.mock(OrderDAO.class);
        orderService = new OrderService(orderDAO);
    }

    @Test
    public void testCreateOrder() throws SQLException {
        Order order = new Order(1, 1, 1, "2024-05-18");
        orderService.createOrder(order);
        verify(orderDAO, times(1)).addOrder(order);
    }

    @Test
    public void testGetOrder() throws SQLException {
        Order order = new Order(1, 1, 1, "2024-05-18");
        when(orderDAO.getOrder(1)).thenReturn(order);

        Order result = orderService.getOrder(1);
        assertNotNull(result);
        assertEquals(1, result.getUserId());
        assertEquals(1, result.getBookId());
        assertEquals("2024-05-18", result.getOrderDate());
    }

    @Test
    public void testCreateOrderSQLException() throws SQLException {
        Order order = new Order(1, 1, 1, "2024-05-18");
        doThrow(new SQLException("Database error")).when(orderDAO).addOrder(order);

        Exception exception = assertThrows(SQLException.class, () -> {
            orderService.createOrder(order);
        });

        String expectedMessage = "Database error";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}

