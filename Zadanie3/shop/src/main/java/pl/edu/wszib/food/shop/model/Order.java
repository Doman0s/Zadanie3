package pl.edu.wszib.food.shop.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "torder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double price;
    private LocalDateTime date;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderPosition> orderPositions = new HashSet<>();

    public Order(int id, double price, LocalDateTime date, User user, Set<OrderPosition> orderPositions) {
        this.id = id;
        this.price = price;
        this.date = date;
        this.user = user;
        this.orderPositions = orderPositions;
    }

    public Order(User user, Set<OrderPosition> orderPositions) {
        this.user = user;
        this.orderPositions = orderPositions;
        date = LocalDateTime.now();
        this.price = 0;
        for(OrderPosition orderPosition : orderPositions) {
            this.price += orderPosition.getProduct().getPrice() * orderPosition.getQuantity();
        }
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }
}
