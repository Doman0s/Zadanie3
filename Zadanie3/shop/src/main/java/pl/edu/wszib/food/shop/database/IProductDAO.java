package pl.edu.wszib.food.shop.database;

import pl.edu.wszib.food.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Product> getProducts();
    Optional<Product> getProductById(int productId);
    void updateProduct(Product product);
}
