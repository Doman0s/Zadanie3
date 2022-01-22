package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
