package pl.edu.wszib.food.shop.session;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.wszib.food.shop.model.Cart;
import pl.edu.wszib.food.shop.model.User;

@Component
@SessionScope
public class Session {
    final private Cart cart = new Cart();
    private User user = null;

    public boolean isLogged() {
        return this.user != null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart getCart() {
        return cart;
    }
}
