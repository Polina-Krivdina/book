package Service;

import dao.OrderDAO;
import model.Order;

import java.sql.SQLException;

public class OrderService {
    private OrderDAO orderDAO;

    public OrderService(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void createOrder(Order order) throws SQLException {
        orderDAO.addOrder(order);
    }

    public Order getOrder(int id) throws SQLException {
        return orderDAO.getOrder(id);
    }

    // Другие методы для бизнес-логики, связанные с заказами
}
