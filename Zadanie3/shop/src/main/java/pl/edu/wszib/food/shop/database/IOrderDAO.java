package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.Order;
import pl.edu.wszib.food.shop.model.Product;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
