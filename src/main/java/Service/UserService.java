package Service;

import dao.UserDAO;
import model.User;
import java.sql.*;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) throws SQLException {
        userDAO.addUser(user);
    }

    public User getUser(int id) throws SQLException {
        return userDAO.getUser(id);
    }

    // Другие методы для бизнес-логики, связанные с пользователем
}
