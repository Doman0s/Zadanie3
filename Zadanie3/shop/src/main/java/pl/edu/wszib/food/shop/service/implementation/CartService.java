package pl.edu.wszib.food.shop.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.food.shop.database.IProductDAO;
import pl.edu.wszib.food.shop.model.OrderPosition;
import pl.edu.wszib.food.shop.model.Product;
import pl.edu.wszib.food.shop.service.ICartService;
import pl.edu.wszib.food.shop.session.Session;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    IProductDAO productDAO;

    @Resource
    Session session;

    public void addToCart(int productId) {
        Optional<Product> product = this.productDAO.getProductById(productId);

        if(product.isEmpty()) {
            return;
        }

        Product prod = product.get();
        if(prod.getQuantity() <= 0) {
            return;
        }

        for(OrderPosition orderPosition : this.session
                .getCart().getOrderPositions()) {
            if(orderPosition.getProduct().getId() == productId) {
                if(prod.getQuantity() > orderPosition.getQuantity()) {
                    orderPosition.IncreaseQuantity();
                }
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(0, prod, 1);
        this.session.getCart().getOrderPositions().add(orderPosition);
    }

    public void deleteFromCart(int productId) {
        for(OrderPosition orderPosition : this.session
                .getCart().getOrderPositions()) {
            if(orderPosition.getProduct().getId() == productId) {
                session.getCart().getOrderPositions().remove(orderPosition);
                return;
            }
        }
    }
}
