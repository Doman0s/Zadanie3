package pl.edu.wszib.food.shop.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<OrderPosition> orderPositions = new ArrayList<>();
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public String getCalculatedSum() {
        double result = 0.0;
        for(OrderPosition orderPosition : this.orderPositions) {
            result += orderPosition.getQuantity() * orderPosition.getProduct().getPrice();
        }
        return df.format(result);
    }

    public void clearOrders() {
        this.orderPositions = new ArrayList<>();
    }
}
