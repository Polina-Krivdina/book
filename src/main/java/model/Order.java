package model;

public class Order {
    private int id;
    private int userId;
    private int bookId;
    private String orderDate;

    public Order(int id, int userId, int bookId, String orderDate) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}