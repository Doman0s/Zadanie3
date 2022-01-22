package pl.edu.wszib.food.shop.database.implementation.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.food.shop.database.IProductDAO;
import pl.edu.wszib.food.shop.model.Product;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO implements IProductDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Product> getProducts() {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Product");
        List<Product> result = query.getResultList();
        session.close();
        return result;
    }

    @Override
    public Optional<Product> getProductById(int productId) {
        Session session = this.sessionFactory.openSession();
        Query<Product> query = session.createQuery("FROM pl.edu.wszib.food.shop.model.Product WHERE id = :id");
        query.setParameter("id", productId);
        try {
            Product product = query.getSingleResult();
            session.close();
            return Optional.of(product);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateProduct(Product product) {
        Session session = this.sessionFactory.openSession();
        Transaction tr = null;
        try {
            tr = session.beginTransaction();
            session.update(product);
            tr.commit();
        } catch (Exception e) {
            if(tr != null) {
                tr.rollback();
            }
        } finally {
            session.close();
        }
    }
}
