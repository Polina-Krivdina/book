package dao;

import model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class OrderDAO {
    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOrder(Order order) throws SQLException {
        String query = "INSERT INTO orders (id, user_id, book_id, order_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getId());
            stmt.setInt(2, order.getUserId());
            stmt.setInt(3, order.getBookId());
            stmt.setString(4, order.getOrderDate());
            stmt.executeUpdate();
        }
    }

    public Order getOrder(int id) throws SQLException {
        String query = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("book_id"), rs.getString("order_date"));
            }
            return null;
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("book_id"), rs.getString("order_date")));
            }
        }
        return orders;
    }

    // Методы для обновления и удаления заказа
}
