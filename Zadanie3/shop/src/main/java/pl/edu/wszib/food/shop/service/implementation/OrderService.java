package pl.edu.wszib.food.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.food.shop.database.IOrderDAO;
import pl.edu.wszib.food.shop.database.IProductDAO;
import pl.edu.wszib.food.shop.model.Order;
import pl.edu.wszib.food.shop.model.OrderPosition;
import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.service.IOrderService;
import pl.edu.wszib.food.shop.session.Session;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Resource
    Session session;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IProductDAO productDAO;

    @Override
    public List<Order> getLoggedUserOrders() {
        return this.orderDAO.getOrdersByUserId(this.session.getUser().getId());
    }

    @Override
    public void confirmOrder() {
        Order order = new Order(this.session.getUser(), new HashSet<>(this.session.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for(OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Product> product = this.productDAO.getProductById(orderPosition.getProduct().getId());
            if(product.isPresent()) {
                product.get().setQuantity(product.get().getQuantity() - orderPosition.getQuantity());
                this.productDAO.updateProduct(product.get());
            }
        }
        this.session.getCart().clearOrders();
    }
}
