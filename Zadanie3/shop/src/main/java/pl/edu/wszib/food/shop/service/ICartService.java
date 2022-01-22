package pl.edu.wszib.food.shop.service;

public interface ICartService {
    void addToCart(int productId);
    void deleteFromCart(int productId);
}
