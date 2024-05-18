package Library;

import Service.BookService;
import Service.OrderService;
import Service.UserService;
import dao.BookDAO;
import dao.OrderDAO;
import dao.UserDAO;
import model.Book;
import model.Order;
import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class LibraryCLI {
    private static UserService userService;
    private static BookService bookService;
    private static OrderService orderService;

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:your_database_url", "username", "password");

            userService = new UserService(new UserDAO(connection));
            bookService = new BookService(new BookDAO(connection));
            orderService = new OrderService(new OrderDAO(connection));

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Введите команду:");
                String command = scanner.nextLine();
                if (command.equalsIgnoreCase("exit")) {
                    break;
                }
                handleCommand(command);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void handleCommand(String command) {
        String[] parts = command.split(" ");
        switch (parts[0].toLowerCase()) {
            case "adduser":
                addUser(parts);
                break;
            case "addbook":
                addBook(parts);
                break;
            case "createorder":
                createOrder(parts);
                break;
            case "getuser":
                getUser(parts);
                break;
            case "getbook":
                getBook(parts);
                break;
            case "getorder":
                getOrder(parts);
                break;
            default:
                System.out.println("Неизвестная команда.");
                break;
        }
    }

    private static void addUser(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            String name = parts[2];
            String email = parts[3];
            User user = new User(id, name, email);
            userService.registerUser(user);
            System.out.println("Пользователь добавлен.");
        } catch (Exception e) {
            System.out.println("Ошибка добавления пользователя: " + e.getMessage());
        }
    }

    private static void addBook(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            String title = parts[2];
            String author = parts[3];
            Book book = new Book(id, title, author);
            bookService.addBook(book);
            System.out.println("Книга добавлена.");
        } catch (Exception e) {
            System.out.println("Ошибка добавления книги: " + e.getMessage());
        }
    }

    private static void createOrder(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            int userId = Integer.parseInt(parts[2]);
            int bookId = Integer.parseInt(parts[3]);
            String orderDate = parts[4];
            Order order = new Order(id, userId, bookId, orderDate);
            orderService.createOrder(order);
            System.out.println("Заказ создан.");
        } catch (Exception e) {
            System.out.println("Ошибка создания заказа: " + e.getMessage());
        }
    }

    private static void getUser(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            User user = userService.getUser(id);
            if (user != null) {
                System.out.println("Пользователь: " + user.getName() + ", Email: " + user.getEmail());
            } else {
                System.out.println("Пользователь не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка получения пользователя: " + e.getMessage());
        }
    }

    private static void getBook(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            Book book = bookService.getBook(id);
            if (book != null) {
                System.out.println("Книга: " + book.getTitle() + ", Автор: " + book.getAuthor());
            } else {
                System.out.println("Книга не найдена.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка получения книги: " + e.getMessage());
        }
    }

    private static void getOrder(String[] parts) {
        try {
            int id = Integer.parseInt(parts[1]);
            Order order = orderService.getOrder(id);
            if (order != null) {
                System.out.println("Заказ: User ID = " + order.getUserId() + ", Book ID = " + order.getBookId() + ", Date = " + order.getOrderDate());
            } else {
                System.out.println("Заказ не найден.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка получения заказа: " + e.getMessage());
        }
    }
}
