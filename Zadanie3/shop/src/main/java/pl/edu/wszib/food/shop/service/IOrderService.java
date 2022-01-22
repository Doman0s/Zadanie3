package pl.edu.wszib.food.shop.service;

import pl.edu.wszib.food.shop.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> getLoggedUserOrders();
    void confirmOrder();
}
