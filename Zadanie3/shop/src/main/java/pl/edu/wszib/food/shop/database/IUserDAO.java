package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
